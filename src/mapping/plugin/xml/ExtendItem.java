package mapping.plugin.xml;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.SubTag;

public interface ExtendItem extends com.intellij.util.xml.DomElement{
    GenericDomValue<String> getId();
    @SubTag("class")
    GenericDomValue<PsiClass> getClasses();
    GenericDomValue<String> getDescription();
    GenericDomValue<String> getExtendService();
}
