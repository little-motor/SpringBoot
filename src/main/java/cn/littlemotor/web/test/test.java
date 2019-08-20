package cn.littlemotor.web.test;

import cn.littlemotor.web.controller.HomeController;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> test = new ArrayList<>();
        Annotation[] annotations = HomeController.class.getAnnotations();
        Set<Annotation> set = new HashSet<>();
        for (Annotation annotation : annotations){
            recursivelyAnnotaion(annotation,set);
        }
        System.out.println(set);
    }

    public static void recursivelyAnnotaion(Annotation annotation, Set<Annotation> set){
        if (set.add(annotation)){
            if (annotation.annotationType().getAnnotations().length > 0){
                for (Annotation annotation1 : annotation.annotationType().getAnnotations()){
                    recursivelyAnnotaion(annotation1,set);
                }

            }
            System.out.println(annotation.annotationType().getName());
        }

    }
}
