package plugin;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomManager;
import com.intellij.util.xml.ui.DomFileEditor;
import plugin.xml.Plugin;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

public class PluginFileEditorProvider implements FileEditorProvider, DumbAware {
    private static final Logger LOG = Logger.getInstance(PluginFileEditorProvider.class);

    public boolean accept(@NotNull final Project project, @NotNull final VirtualFile file) {
        return isPluginFile(project, file);
    }

    private boolean isPluginFile(@NotNull final Project project, @NotNull final VirtualFile file) {
        final String path = file.getPath();
        if (path.endsWith(".xml") && path.contains("/plugins/")){
            return true;
        }
        return false;
    }

    @NotNull
    public FileEditor createEditor(@NotNull final Project project, @NotNull final VirtualFile file) {
        PsiFile file1 = PsiManager.getInstance(project).findFile(file);
        DomManager manager = DomManager.getDomManager(project);
        DomFileElement<Plugin> fileElement = manager.getFileElement((XmlFile) file1, Plugin.class);
        if (fileElement == null){
            return  null;
        }
        Plugin rootElement = fileElement.getRootElement();
        return new DomFileEditor<>(rootElement, "Plugin View Edit", new PluginComponent(project, rootElement));
    }


    public void disposeEditor(@NotNull final FileEditor editor) {
        Disposer.dispose(editor);
    }

    @NotNull
    public FileEditorState readState(@NotNull final Element element, @NotNull final Project project,
                                     @NotNull final VirtualFile file) {
        return new FileEditorState() {
            @Override
            public boolean canBeMergedWith(FileEditorState otherState, FileEditorStateLevel level) {
                return false;
            }
        };
    }

    public void writeState(@NotNull final FileEditorState state, @NotNull final Project project,
                           @NotNull final Element element) {
    }

    @NotNull
    public String getEditorTypeId() {
        return "plugin.PluginFileEditorProvider";
    }

    @NotNull
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.PLACE_AFTER_DEFAULT_EDITOR;
    }
}
