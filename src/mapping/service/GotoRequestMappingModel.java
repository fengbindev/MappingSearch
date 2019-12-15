package mapping.service;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.ide.util.gotoByName.CustomMatcherModel;
import com.intellij.ide.util.gotoByName.FilteringGotoByModel;
import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.codeStyle.MinusculeMatcher;
import com.intellij.psi.codeStyle.NameUtil;
import mapping.spring.AntPathMatcher;
import mapping.util.HttpMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collection;

// 将筛选出来的item转model
public class GotoRequestMappingModel extends FilteringGotoByModel<HttpMethod> implements DumbAware, CustomMatcherModel {

    public GotoRequestMappingModel(@NotNull Project project, @NotNull ChooseByNameContributor[] contributors) {
        super(project, contributors);
    }

    // 过滤模块？ FilteringGotoByModel.acceptItem 调用，结合 重写 setFilterItems或 getFilterItems() 实现，可过滤模块 或者 method (如GotoClassModel2过滤language，重写 getFilterItems())
    @Nullable
    @Override
    protected HttpMethod filterValueFor(NavigationItem item) {
        if (item instanceof RestServiceItem) {
            return ((RestServiceItem) item).getMethod();
        }
        return null;
    }

    /* 可选项 */
    @Nullable
    @Override
    protected synchronized Collection<HttpMethod> getFilterItems() {
        return super.getFilterItems();
    }

    @Override
    public String getPromptText() {
        return "Enter mapping path :";
    }

    @Override
    public String getNotInMessage() {
        return "Mapping path not found";
    }

    @Override
    public String getNotFoundMessage() {
        return "Mapping path not found";
    }

    @Override
    public boolean loadInitialCheckBoxState() {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance(myProject);
        return propertiesComponent.isTrueValue("GoToRestService.OnlyCurrentModule");
    }

    /* 选择 item 跳转触发 */
    @Override
    public void saveInitialCheckBoxState(boolean state) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance(myProject);
        if (propertiesComponent.isTrueValue("GoToRestService.OnlyCurrentModule")) {
            propertiesComponent.setValue("GoToRestService.OnlyCurrentModule", Boolean.toString(state));
        }
    }

    @Nullable
    @Override
    public String getFullName(Object element) {
        return getElementName(element);
    }

    // 截取 Separators 后面pattern
    @NotNull
    @Override
    public String[] getSeparators() {
        return new String[]{"/", "?"};
    }


    /**
     * return null to hide checkbox panel
     */
    @Nullable
    @Override
    public String getCheckBoxName() {
        return "Only This Module";
    }


    @Override
    public boolean willOpenEditor() {
        return true;
    }

    //    CustomMatcherModel 接口，Allows to implement custom matcher for matching items from ChooseByName popup
    // todo: resolve PathVariable annotation
    @Override
    public boolean matches(@NotNull String popupItem, @NotNull String userPattern) {
        String pattern = userPattern;
        if (pattern.equals("/")) {
            return true;
        }
        // REST风格的参数  @RequestMapping(value="{departmentId}/employees/{employeeId}")  PathVariable
        // REST风格的参数（正则） @RequestMapping(value="/{textualPart:[a-z-]+}.{numericPart:[\\d]+}")  PathVariable

//        userPattern  输入的过滤文字
//        DefaultChooseByNameItemProvider.buildPatternMatcher
        MinusculeMatcher matcher = NameUtil.buildMatcher("*" + pattern, NameUtil.MatchingCaseSensitivity.NONE);
        boolean matches = matcher.matches(popupItem);
        if (!matches) {
            AntPathMatcher pathMatcher = new AntPathMatcher();
            matches = pathMatcher.match(popupItem, userPattern);
        }
        return matches;
    }

    @NotNull
    @Override
    public String removeModelSpecificMarkup(@NotNull String pattern) {
        return super.removeModelSpecificMarkup(pattern);
    }

    /* TODO :重写渲染*/
    @Override
    public ListCellRenderer getListCellRenderer() {
        return super.getListCellRenderer();
    }


}
