package mapping.plugin;

import com.intellij.util.xml.DomFileDescription;
import mapping.plugin.xml.Plugin;

public class PluginDomFileDescription extends DomFileDescription{

    public PluginDomFileDescription() {
        super(Plugin.class, "plugin");
    }

}
