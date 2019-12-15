package mapping.service;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.module.Module;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import mapping.util.HttpMethod;
import mapping.util.ToolkitIcons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class RestServiceItem implements NavigationItem {
    private PsiMethod psiMethod; //元素
    private PsiElement psiElement; //元素
    private Module module;

    private String requestMethod; //请求方法 get/post...
    private HttpMethod method;  //请求方法 get/post...

    private String url; //url mapping;

    private Navigatable navigationElement;

    public RestServiceItem(PsiElement psiElement, String requestMethod, String urlPath) {
        this.psiElement = psiElement;
        if (psiElement instanceof PsiMethod) {
            this.psiMethod = (PsiMethod) psiElement;
        }
        this.requestMethod = requestMethod;
        if (requestMethod != null) {
            method = HttpMethod.getByRequestMethod(requestMethod);
        }

        this.url = urlPath;
        if (psiElement instanceof Navigatable) {
            navigationElement = (Navigatable) psiElement;
        }
    }

    @Nullable
    @Override
    public String getName() {
        return this.url;
    }

    @Nullable
    @Override
    public ItemPresentation getPresentation() {
        return new RestServiceItemPresentation();
    }

    @Override
    public void navigate(boolean requestFocus) {
        if (navigationElement != null) {
            navigationElement.navigate(requestFocus);
        }
    }

    @Override
    public boolean canNavigate() {
        return navigationElement.canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return true;
    }


    private class RestServiceItemPresentation implements ItemPresentation {
        @Nullable
        @Override
        public String getPresentableText() {
            return url;
        }

        //        对应的文件位置显示
        @Nullable
        @Override
        public String getLocationString() {
            String fileName = psiElement.getContainingFile().getName();

            String location = null;

            if (psiElement instanceof PsiMethod) {
                PsiMethod psiMethod = ((PsiMethod) psiElement);
                ;
                location = psiMethod.getContainingClass().getName().concat("#").concat(psiMethod.getName());
            }

            return "(" + location + ")";
        }

        @Nullable
        @Override
        public Icon getIcon(boolean unused) {
            return ToolkitIcons.METHOD.get(method);
        }
    }

    public Module getModule() {
        return module;
    }

    public PsiMethod getPsiMethod() {
        return psiMethod;
    }

    public void setPsiMethod(PsiMethod psiMethod) {
        this.psiMethod = psiMethod;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setModule(Module module) {
        this.module = module;
    }


    public PsiElement getPsiElement() {
        return psiElement;
    }
}
