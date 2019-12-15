package mapping.service;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import mapping.util.ServiceHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

//自定义搜索
public class GotoRequestMappingContributor implements ChooseByNameContributor {
    Module myModule;

    // 查找所有的item
    private List<RestServiceItem> navItem;

    public GotoRequestMappingContributor(Module myModule) {
        this.myModule = myModule;
    }

    //所有该类型的文件列表
    @NotNull
    @Override
    public String[] getNames(Project project, boolean onlyThisModuleChecked) {
        String[] names = null;
        List<RestServiceItem> itemList = new ArrayList<>();
        // 查找 project 中所有符合 rest url 类型文件，包含 request 接口
        if (onlyThisModuleChecked && myModule != null) {
            itemList = ServiceHelper.buildRestServiceItemListUsingResolver(myModule);
        } else {
            itemList = ServiceHelper.buildRestServiceItemListUsingResolver(project);
        }

        navItem = itemList;

        if (itemList != null) names = new String[itemList.size()];

        for (int i = 0; i < itemList.size(); i++) {
            RestServiceItem requestMappingNavigationItem = itemList.get(i);
            names[i] = requestMappingNavigationItem.getName();
        }

        return names;
    }

    //Returns the list of navigation items matching the specified name. 匹配，对比
    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean onlyThisModuleChecked) {
//        AntPathMatcher
        NavigationItem[] navigationItems = navItem.stream().filter(item -> item.getName().equals(name)).toArray(NavigationItem[]::new);
        return navigationItems;

    }
}
