package org.example;

import org.example.annotation.After;
import org.example.annotation.Before;
import org.example.annotation.Test;
import org.example.exception.AssertionException;
import org.reflections.Reflections;
import org.reflections.scanners.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Runner {

    private static int testPassedCount = 0;
    private static int testFailedCount = 0;

    public static void run(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        Set<Class<?>> subTypesOfObject = reflections.getSubTypesOf(Object.class);

        HashMap<String, Class<?>> classHashMap = selectRequiredClasses(subTypesOfObject);
        List<Object> listOfClassInstances = createClassInstances(classHashMap);
        executeMethodsInOrder(listOfClassInstances);
    }

    private static HashMap<String, Class<?>> selectRequiredClasses(Set<Class<?>> subTypesOfObject) {
        HashMap<String, Class<?>> classHashMap = new HashMap<>();
        for (Class<?> clazz : subTypesOfObject) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Before.class) || method.isAnnotationPresent(Test.class)
                        || method.isAnnotationPresent(After.class)) {

                    classHashMap.putIfAbsent(clazz.toGenericString(), clazz);
                }
            }
        }

        return classHashMap;
    }

    private static List<Object> createClassInstances(HashMap<String, Class<?>> classHashMap) {
        List<Object> listOfClassInstances = new ArrayList<>();
        classHashMap.values().forEach(value -> {
            try {
                Object instanceObject = value.newInstance();
                listOfClassInstances.add(instanceObject);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return listOfClassInstances;
    }

    private static void executeMethodsInOrder(List<Object> listOfClassInstances) {
        List<Method> listAnnotationBeforeMethods = new ArrayList<>();
        List<Method> listAnnotationTestMethods = new ArrayList<>();
        List<Method> listAnnotationAfterMethods = new ArrayList<>();

        listOfClassInstances.forEach(elem -> {
            Class<?> classElem = elem.getClass();
            Method[] methods = classElem.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Before.class)) {
                    listAnnotationBeforeMethods.add(method);
                } else if (method.isAnnotationPresent(Test.class)) {
                    listAnnotationTestMethods.add(method);
                } else if (method.isAnnotationPresent(After.class)) {
                    listAnnotationAfterMethods.add(method);
                }
            }

            /*
            Для каждого созданного объекта класса производим поочередный вызов накопленных методов в следующем порядке:
            1. С начала выполняем вызов всех методов, помеченных аннотацией "Before";
            2. Затем выполняем вызов всех методов, помеченных аннотацией "Test";
            3. В завершении выполняем вызов всех методов, помеченных аннотацией "After".
            */
            callMethodsAndClearTheList(listAnnotationBeforeMethods, elem);
            callMethodsAndClearTheList(listAnnotationTestMethods, elem);
            callMethodsAndClearTheList(listAnnotationAfterMethods, elem);
        });

        System.out.println("Tests passed: " + testPassedCount + ", Tests failed: " + testFailedCount);
    }

    private static void callMethodsAndClearTheList(List<Method> methodList, Object instanceObject) {
        for (Method method : methodList) {
            String methodName = method.getName();
            try {
                method.invoke(instanceObject);
                testPassedCount++;
                System.out.println("Test " + methodName + " passed");
            } catch (IllegalAccessException | InvocationTargetException e) {
                if (e.getCause() instanceof AssertionException) {
                    Throwable ex = e.getCause();
                    testFailedCount++;
                    System.out.println("Test " + methodName + " failed: " + ex.getMessage());
                }
            }
        }

        methodList.clear();
    }
}
