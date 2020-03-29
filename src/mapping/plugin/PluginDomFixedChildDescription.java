package mapping.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiElement;
import com.intellij.util.xml.*;
import com.intellij.util.xml.reflect.DomFixedChildDescription;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

public abstract class PluginDomFixedChildDescription implements DomFixedChildDescription {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public JavaMethod getGetterMethod(int i) {
        return null;
    }

    @Nullable
    @Override
    public <T extends Annotation> T getAnnotation(int i, Class<? extends T> aClass) {
        return null;
    }

    @NotNull
    @Override
    public XmlName getXmlName() {
        return null;
    }

    @NotNull
    @Override
    public String getXmlElementName() {
        return null;
    }

    @NotNull
    @Override
    public String getCommonPresentableName(@NotNull DomNameStrategy domNameStrategy) {
        return null;
    }

    @NotNull
    @Override
    public String getCommonPresentableName(@NotNull DomElement domElement) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @NotNull
    @Override
    public List<? extends DomElement> getStableValues(@NotNull DomElement domElement) {
        return null;
    }

    @NotNull
    @Override
    public Type getType() {
        return null;
    }

    @NotNull
    @Override
    public DomNameStrategy getDomNameStrategy(@NotNull DomElement domElement) {
        return null;
    }

    @Override
    public <T> T getUserData(Key<T> key) {
        return null;
    }

    @Nullable
    @Override
    public ElementPresentationTemplate getPresentationTemplate() {
        return null;
    }

    @Nullable
    @Override
    public PsiElement getDeclaration(Project project) {
        return null;
    }

    @Nullable
    @Override
    public DomElement getDomDeclaration() {
        return null;
    }

    @Override
    public boolean isStubbed() {
        return false;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void navigate(boolean b) {

    }

    @Override
    public boolean canNavigate() {
        return false;
    }

    @Override
    public boolean canNavigateToSource() {
        return false;
    }

    @Nullable
    @Override
    public <T extends Annotation> T getAnnotation(Class<T> aClass) {
        return null;
    }
}
