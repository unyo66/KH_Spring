package com.bitstudy.app;

import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
* @Autowired
* */

@Component class Car8 {
    @Autowired Engine8 engine8;
    @Autowired Door8 door8;
}

@Component class Engine8 {}
@Component class Door8 {}
class AppContext8 {
    Map map;
    AppContext8() throws Exception {
        map = new HashMap();
        doComponentScan(); //맵 생성 -> 저장
        doAutowired(); //맵 정보 불러오기
    }

    private void doAutowired() throws Exception {
        /*map에서 value 중 오토와이어가 붙은 것들을 가져옴*/
        for (Object bean : map.values()) {
            for (Field f : bean.getClass().getDeclaredFields()) {
                if (f.getAnnotation(Autowired.class) != null) {
                    f.set(bean, getBean(f.getType()));
                }
            }
        }
    }

    private void doComponentScan(){
        try {
            ClassLoader classLoader = AppContext7.class.getClassLoader(); //AppContext의 클래스 정보 얻어오기
            ClassPath classPath = ClassPath.from(classLoader);
            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.bitstudy.app");

            for (ClassPath.ClassInfo classInfo : set) {
                Class clazz = classInfo.load();

                Component component = (Component) clazz.getAnnotation(Component.class);

                if (component != null) {
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());

                    map.put(id, clazz.getDeclaredConstructor().newInstance());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Object getBean(Class clazz) {
        for (Object obj : map.values()) {
            if (clazz.isInstance(obj)) {
                return obj;
            }
        }
            return null;
    }
}

public class Ex08_DI {
    public static void main(String[] args) throws Exception {
        AppContext8 ac = new AppContext8();
//        Car8 car = (Car8) ac.getBean("car8");
//        System.out.println("car8 = " + car);
    }
}