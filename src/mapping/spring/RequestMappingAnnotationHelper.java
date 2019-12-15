package mapping.spring;


import com.intellij.psi.*;
import mapping.annotations.SpringRequestMethodAnnotation;
import mapping.util.PsiAnnotationHelper;
import mapping.util.RequestPath;
import mapping.util.RestSupportedAnnotationHelper;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequestMappingAnnotationHelper implements RestSupportedAnnotationHelper {

    /**
     * 得到类上的请求路径
     *
     * @param psiClass
     * @return
     */
    public static List<RequestPath> getRequestPaths(PsiClass psiClass) {
        PsiAnnotation[] annotations = psiClass.getModifierList().getAnnotations();
        if (annotations == null) return null;

        PsiAnnotation requestMappingAnnotation = null;
        List<RequestPath> list = new ArrayList<>();
        for (PsiAnnotation annotation : annotations) {
            for (SpringRequestMethodAnnotation mappingAnnotation : SpringRequestMethodAnnotation.values()) {
                if (annotation.getQualifiedName().equals(mappingAnnotation.getQualifiedName())) {
                    requestMappingAnnotation = annotation;
                }
            }
        }

        if (requestMappingAnnotation != null) {
            List<RequestPath> requestMappings = getRequestMappings(requestMappingAnnotation, "");
            if (requestMappings.size() > 0) {
                list.addAll(requestMappings);
            }
        } else {
            // TODO : 继承 RequestMapping
            PsiClass superClass = psiClass.getSuperClass();
            if (superClass != null && !superClass.getQualifiedName().equals("java.lang.Object")) {
                list = getRequestPaths(superClass);
            } else {
                list.add(new RequestPath("/", null));
            }

        }

        return list;
    }

    /**
     * @param annotation
     * @param defaultValue
     * @return
     */
    private static List<RequestPath> getRequestMappings(PsiAnnotation annotation, String defaultValue) {
        List<RequestPath> mappingList = new ArrayList<>();

        SpringRequestMethodAnnotation requestAnnotation = SpringRequestMethodAnnotation.getByQualifiedName(annotation.getQualifiedName());

        if (requestAnnotation == null) {
            return new ArrayList<>();
        }

        List<String> methodList;
        if (requestAnnotation.methodName() != null) {
            methodList = Arrays.asList(requestAnnotation.methodName());
        } else { // RequestMapping 如果没有指定具体method，不写的话，默认支持所有HTTP请求方法
            methodList = PsiAnnotationHelper.getAnnotationAttributeValues(annotation, "method");
        }

        List<String> pathList = PsiAnnotationHelper.getAnnotationAttributeValues(annotation, "value");
        if (pathList.size() == 0) {
            pathList = PsiAnnotationHelper.getAnnotationAttributeValues(annotation, "path");
        }
        // 处理没有设置 value 或 path 的 RequestMapping使用默认值
        if (pathList.size() == 0) {
            pathList.add(defaultValue);
        }


        if (methodList.size() > 0) {
            for (String method : methodList) {
                for (String path : pathList) {
                    mappingList.add(new RequestPath(path, method));
                }
            }
        } else {
            for (String path : pathList) {
                mappingList.add(new RequestPath(path, null));
            }
        }

        return mappingList;
    }

    /**
     * 得到方法上面的请求路径
     *
     * @param psiMethod
     * @return
     */
    public static RequestPath[] getRequestPaths(PsiMethod psiMethod) {
        PsiAnnotation[] annotations = psiMethod.getModifierList().getAnnotations();

        if (annotations == null) return null;
        List<RequestPath> list = new ArrayList<>();

        for (PsiAnnotation annotation : annotations) {
            for (SpringRequestMethodAnnotation mappingAnnotation : SpringRequestMethodAnnotation.values()) {
                if (mappingAnnotation.getQualifiedName().equals(annotation.getQualifiedName())) {
                    String defaultValue = "/";
                    List<RequestPath> requestMappings = getRequestMappings(annotation, defaultValue);
                    if (requestMappings.size() > 0) {
                        list.addAll(requestMappings);
                    }
                }
            }
        }

        return list.toArray(new RequestPath[list.size()]);
    }
}