package mapping.action;

import com.intellij.ide.actions.GotoActionBase;
import com.intellij.ide.util.gotoByName.ChooseByNameFilter;
import com.intellij.ide.util.gotoByName.ChooseByNameItemProvider;
import com.intellij.ide.util.gotoByName.ChooseByNameModel;
import com.intellij.ide.util.gotoByName.ChooseByNamePopup;
import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import mapping.service.*;
import mapping.spring.StringUtils;
import mapping.util.HttpMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.util.Arrays;
import java.util.List;


public class GotoRequestMappingAction extends GotoActionBase implements DumbAware {
    public GotoRequestMappingAction() {
    }

    @Override
    protected void gotoActionPerformed(AnActionEvent e) {
        //进入导航
        Project project = e.getProject();
        if (project == null) return;

        ChooseByNameContributor[] chooseByNameContributors = {
                new GotoRequestMappingContributor(e.getData(DataKeys.MODULE))
        };

        final GotoRequestMappingModel model = new GotoRequestMappingModel(project, chooseByNameContributors);

        /**
         * 动作回调
         */
        GotoActionCallback<HttpMethod> callback = new GotoActionCallback<HttpMethod>() {
            @Override
            protected ChooseByNameFilter<HttpMethod> createFilter(@NotNull ChooseByNamePopup popup) {
                return new GotoRequestMappingFilter(popup, model, project);
            }

            @Override
            public void elementChosen(ChooseByNamePopup chooseByNamePopup, Object element) {
                if (element instanceof RestServiceItem) {
                    RestServiceItem navigationItem = (RestServiceItem) element;
                    if (navigationItem.canNavigate()) {
                        navigationItem.navigate(true);
                    }
                }
            }
        };
        GotoRequestMappingProvider provider = new GotoRequestMappingProvider(getPsiContext(e));
        // 展示弹窗
        showNavigationPopup(e, model, callback, "Request Mapping Url matching pattern", true, false, (ChooseByNameItemProvider)provider);

    }

    @Override
    protected <T> void showNavigationPopup(AnActionEvent e,
                                           ChooseByNameModel model,
                                           final GotoActionCallback<T> callback,
                                           @Nullable final String findUsagesTitle,
                                           boolean useSelectionFromEditor,
                                           final boolean allowMultipleSelection,
                                           final ChooseByNameItemProvider itemProvider) {
        final Project project = e.getData(CommonDataKeys.PROJECT);
        boolean mayRequestOpenInCurrentWindow = model.willOpenEditor() && FileEditorManagerEx.getInstanceEx(project).hasSplitOrUndockedWindows();
        Pair<String, Integer> start = getInitialText(useSelectionFromEditor, e);

        String copiedURL = tryFindCopiedURL();

        String predefinedText = (start.first == null || StringUtils.isEmpty(start.first)) ? copiedURL : start.first;

        showNavigationPopup(callback, findUsagesTitle,
                RestServiceChooseByNamePopup.createPopup(project, model, itemProvider, predefinedText,
                        mayRequestOpenInCurrentWindow,
                        start.second), allowMultipleSelection);
    }

    /**
     * 从剪切板里得到复制的url
     * @return
     */
    private String tryFindCopiedURL() {
        String contents = CopyPasteManager.getInstance().getContents(DataFlavor.stringFlavor);
        if (contents == null) {
            return null;
        }
        contents = contents.trim();
        if (contents.startsWith("http")) {
            if (contents.length() <= 120) {
                return contents;
            }else {
                return contents.substring(0, 120);
            }
        }
        return null;
    }

    /**
     * 过滤器
     */
    protected static class GotoRequestMappingFilter extends ChooseByNameFilter<HttpMethod> {
        GotoRequestMappingFilter(final ChooseByNamePopup popup, GotoRequestMappingModel model, final Project project) {
            super(popup, model, GotoRequestMappingConfiguration.getInstance(project), project);
        }

        /**
         * 设置过滤项
         * @return
         */
        @Override
        @NotNull
        protected List<HttpMethod> getAllFilterValues() {
            List<HttpMethod> elements = Arrays.asList(HttpMethod.values());
            return elements;
        }

        @Override
        protected String textForFilterValue(@NotNull HttpMethod value) {
            return value.name();
        }

        @Override
        protected Icon iconForFilterValue(@NotNull HttpMethod value) {
            return null;
        }
    }
}
