package mapping.plugin.xml;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.SubTag;

import java.util.List;

public interface Plugin extends com.intellij.util.xml.DomElement {
    GenericDomValue<String> getId();
    GenericDomValue<String> getName();
    @SubTag("class")
    GenericDomValue<PsiClass> getClasses();
    GenericDomValue<String> getAuthor();
    GenericDomValue<String> getProvider();
    GenericDomValue<String> getVersion();
    GenericDomValue<String> getDescription();
    @SubTag("extend-action")
    List<ExtendAction> getExtendActions();
    ExtendAction addExtendAction(int index);
    ExtendAction addExtendAction();
    @SubTag("extend-point")
    List<ExtendPoint> getExtendPoints();
    ExtendPoint addExtendPoint(int index);
    ExtendPoint addExtendPoint();
    @SubTag("extend-service")
    List<ExtendService> getExtendServices();
    ExtendService addExtendService(int index);
    ExtendService addExtendService();
    @SubTag("extend-item")
    List<ExtendItem> getExtendItems();
    ExtendItem addExtendItem(int index);
    ExtendItem addExtendItem();
}


