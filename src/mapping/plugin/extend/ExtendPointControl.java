package mapping.plugin.extend;

import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.ui.DomCollectionControl;
import mapping.plugin.PluginDomFixedChildDescription;
import mapping.plugin.xml.ExtendPoint;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExtendPointControl extends DomCollectionControl<ExtendPoint> {

    public ExtendPointControl(DomElement parentElement, String subTagName, boolean editable, ColumnInfo<ExtendPoint, ?>... columnInfos) {
        super(parentElement, subTagName, editable, columnInfos);
    }

    public static class ExtendPointID extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendPoint extendPoint = (ExtendPoint) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendPoint.getId());
            return list;
        }
    }

    public static class ExtendPointClass extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendPoint extendPoint = (ExtendPoint) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendPoint.getClasses());
            return list;
        }
    }
    public static class ExtendPointDescription extends PluginDomFixedChildDescription {
        @NotNull
        @Override
        public List<? extends DomElement> getValues(@NotNull DomElement domElement) {
            ExtendPoint extendPoint = (ExtendPoint) domElement;
            List<DomElement> list = new ArrayList<>();
            list.add(extendPoint.getDescription());
            return list;
        }
    }
}
