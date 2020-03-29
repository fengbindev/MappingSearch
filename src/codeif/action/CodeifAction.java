package codeif.action;

import codeif.popup.CodeifListPopup;
import codeif.service.VariableService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import mapping.spring.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class CodeifAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 拿到选中的字符
        final Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        final Editor mEditor = e.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            return;
        }
        SelectionModel model = mEditor.getSelectionModel();
        final String selectedText = Objects.requireNonNull(model.getSelectedText()).trim();
        if (StringUtils.isEmpty(selectedText)) {
            return;
        }
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Switching Env") {

            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                progressIndicator.setText("Search Variable for CODELF");
                progressIndicator.setIndeterminate(true);
                progressIndicator.setFraction(0.8);
                List<String> variableList = VariableService.getValues(selectedText);
                progressIndicator.setFraction(0.10);
                CodeifListPopup listPopup = new CodeifListPopup("变量名", variableList, e);
                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JBPopupFactory.getInstance().createListPopup(listPopup).showInBestPositionFor(mEditor);
                    }
                });

            }
        });


    }
}
