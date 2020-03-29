package mapping.plugin.extend;

import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.ui.DomCollectionControl;
import mapping.plugin.PluginDomFixedChildDescription;
import mapping.plugin.xml.ExtendService;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExtendServiceControl extends DomCollectionControl<ExtendService> {

    public ExtendServiceControl(DomElement parentElement, String subTagName, boolean editable, ColumnInfo<ExtendService, ?>... columnInfos) {
        super(parentElement, subTagName, editable, columnInfos);
    }

    public static class ExtendServiceID extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendService extendService = (ExtendService) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendService.getId());
            return list;
        }
    }

    public static class ExtendServiceClass extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendService extendService = (ExtendService) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendService.getClasses());
            return list;
        }
    }
    public static class ExtendServiceDescription extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendService extendService = (ExtendService) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendService.getDescription());
            return list;
        }
    }
    public static class ExtendServiceItemClass extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendService extendService = (ExtendService) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendService.getItemClass());
            return list;
        }
    }
}
