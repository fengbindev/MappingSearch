package mapping.service.resolver;


import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.java.stubs.index.JavaAnnotationIndex;
import com.intellij.psi.search.GlobalSearchScope;
import mapping.annotations.PathMappingAnnotation;
import mapping.annotations.SpringControllerAnnotation;
import mapping.service.RestServiceItem;
import mapping.spring.RequestMappingAnnotationHelper;
import mapping.util.RequestPath;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpringResolver extends BaseServiceResolver {

    public SpringResolver(Module module) {
        myModule = module;
    }

    public SpringResolver(Project project) {
        myProject = project;

    }

    @Override
    public List<RestServiceItem> getRestServiceItemList(Project project, GlobalSearchScope globalSearchScope) {
        List<RestServiceItem> itemList = new ArrayList<>();
        SpringControllerAnnotation[] supportedAnnotations = SpringControllerAnnotation.values();
        for (PathMappingAnnotation controllerAnnotation : supportedAnnotations) {
            // 通过java注解索引，找到标注了 (Rest)Controller 注解的类，即 Controller 类
            Collection<PsiAnnotation> psiAnnotations = JavaAnnotationIndex.getInstance().get(controllerAnnotation.getShortName(), project, globalSearchScope);
            for (PsiAnnotation psiAnnotation : psiAnnotations) {
                PsiModifierList psiModifierList = (PsiModifierList) psiAnnotation.getParent();
                PsiElement psiElement = psiModifierList.getParent();

                PsiClass psiClass = (PsiClass) psiElement;
                List<RestServiceItem> serviceItemList = getServiceItemList(psiClass);
                itemList.addAll(serviceItemList);
            }

        }
        return itemList;
    }

    protected List<RestServiceItem> getServiceItemList(PsiClass psiClass) {

        PsiMethod[] psiMethods = psiClass.getMethods();
        if (psiMethods == null) {
            return new ArrayList<>();
        }

        List<RestServiceItem> itemList = new ArrayList<>();
        List<RequestPath> classRequestPaths = RequestMappingAnnotationHelper.getRequestPaths(psiClass);

        for (PsiMethod psiMethod : psiMethods) {
            RequestPath[] methodRequestPaths = RequestMappingAnnotationHelper.getRequestPaths(psiMethod);

            for (RequestPath classRequestPath : classRequestPaths) {
                for (RequestPath methodRequestPath : methodRequestPaths) {
                    String path = classRequestPath.getPath();
                    RestServiceItem item = createRestServiceItem(psiMethod, path, methodRequestPath);
                    itemList.add(item);
                }
            }

        }
        return itemList;
    }


}
