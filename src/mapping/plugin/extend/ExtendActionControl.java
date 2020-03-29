package mapping.plugin.extend;

import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.ui.DomCollectionControl;
import mapping.plugin.PluginDomFixedChildDescription;
import mapping.plugin.xml.ExtendAction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExtendActionControl extends DomCollectionControl<ExtendAction> {

    public ExtendActionControl(DomElement parentElement, String subTagName, boolean editable, ColumnInfo<ExtendAction, ?>... columnInfos) {
        super(parentElement, subTagName, editable, columnInfos);
    }

    public static class ExtendActionID extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendAction extendAction = (ExtendAction) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendAction.getId());
            return list;
        }
    }

    public static class ExtendActionClass extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendAction extendAction = (ExtendAction) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendAction.getClasses());
            return list;
        }
    }
    public static class ExtendActionDescription extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendAction extendAction = (ExtendAction) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendAction.getDescription());
            return list;
        }
    }
    public static class ExtendActionExtendPoint extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendAction extendAction = (ExtendAction) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendAction.getExtendPoint());
            return list;
        }
    }
}
