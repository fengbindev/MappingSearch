package mapping.plugin;

import com.intellij.util.xml.DomFileDescription;
import mapping.plugin.xml.ExtendAction;

public class ExtendActionDomFileDescription extends DomFileDescription{

    public ExtendActionDomFileDescription() {
        super(ExtendAction.class, "extendAction");
    }

}
