package com.bitstudy.app;

import com.google.common.reflect.ClassPath;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
* ######자동으로 객체 등록하기 (Component Scanning)######
* 외부파일(config.txt)는 객체 등록마다 파일을 수정해야 함 -> 협업이 불편 -> config에는 공통 객체 정보만 넣고
* 개별적인 부분은 각 클래스에 @Component 에너테이션을 넣어서 자동으로 map에 등록되게 함.
*
* ######동작원리######
* 패키지 전체의 모든 클래스를 읽어 set에 저장
* @Component가 붙은 클래스만 찾고 그 클래스의 객체를 생성해 map에 따로 저장
* (알아서 클래스의 첫번째 문자를 소문자로 변경해서 키로 저장)
* @Component 에너테이션을 사용하면 외부파일이 따로 없어도 됨.
*
* ######사용방법######
* maven이 제공하는 guava 라이브러리 사용 -> 자바 리플렉션을 쉽게 사용할 수 있게 해줌
* 1) mvn repository
*
* */

@Component class Car7 {}
@Component class SportsCar7 extends Car7 {}
@Component class Truck7 extends Car7 {}
@Component class Engine7 {}
class AppContext7 {
    Map map;
    AppContext7() throws Exception {
        map = new HashMap();

//       1. 메서드 만들기 : 컴포넌트 스캐닝하는 메서드기
        doComponentScan();
    }

    private void doComponentScan(){
//        1. 패키지 전체의 모든 클래스 읽어오기
//        2. 반복문으로 클래스 하나씩 읽어와서 @Component 붙어있는지 확인
//        3. 붙어있으면 객체 생성해서 Map에 저장
        try {
            ClassLoader classLoader = AppContext7.class.getClassLoader(); //AppContext의 클래스 정보 얻어오기
            ClassPath classPath = ClassPath.from(classLoader); // 경로 얻어내기

            //classPath에서 클래스 목록 뽑아내기
            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.bitstudy.app");

            for (ClassPath.ClassInfo classInfo : set) {
                Class clazz = classInfo.load();

                Component component = (Component) clazz.getAnnotation(Component.class);

                if (component != null) {
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());

                    map.put(id, clazz.getDeclaredConstructor().newInstance()); // clazz.newInstance() : Truck5 -> new Truck5();
    //            newInstance()는 이제 안씀 -> .getDeclaredConstructor().newInstance()로 사용
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Object getBean(String key) {
        return map.get(key);
    }
}

public class Ex07_DI {
    public static void main(String[] args) throws Exception {
        AppContext7 ac = new AppContext7();
        Car7 car = (Car7) ac.getBean("car7");
        System.out.println("car7 = " + car);
    }
}
