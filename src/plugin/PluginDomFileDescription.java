package plugin;

import com.intellij.util.xml.DomFileDescription;
import plugin.xml.Plugin;

public class PluginDomFileDescription extends DomFileDescription{

    public PluginDomFileDescription() {
        super(Plugin.class, "plugin");
    }

}
