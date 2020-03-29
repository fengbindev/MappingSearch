package plugin.extend;

import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.ui.DomCollectionControl;
import plugin.PluginDomFixedChildDescription;
import plugin.xml.ExtendItem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExtendItemControl extends DomCollectionControl<ExtendItem> {

    public ExtendItemControl(DomElement parentElement, String subTagName, boolean editable, ColumnInfo<ExtendItem, ?>... columnInfos) {
        super(parentElement, subTagName, editable, columnInfos);
    }

    public static class ExtendItemID extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendItem extendItem = (ExtendItem) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendItem.getId());
            return list;
        }
    }

    public static class ExtendItemClass extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendItem extendItem = (ExtendItem) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendItem.getClasses());
            return list;
        }
    }
    public static class ExtendItemDescription extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendItem extendItem = (ExtendItem) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendItem.getDescription());
            return list;
        }
    }
    public static class ExtendItemExtendService extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendItem extendItem = (ExtendItem) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendItem.getExtendService());
            return list;
        }
    }
}
