package com.bitstudy.app;
/*
* 수동으로 객체 저장소 만들기
* AppContext(저장소) 이용
*
* */

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car6 {}
class SportsCar6 extends Car6 {}
class Truck6 extends Car6 {}
class Engine6 {}
class AppContext6 {
    Map map;

    AppContext6() {
        /* 1번 방법 : 하드코딩
        *  바꿔야 하는 경우 직접 코드를 바꿔야 함
        * */
//        map.put("car6", new SportsCar6());
//        map.put("truck6", new Truck6());
//        map.put("engine6", new Engine6());
        /*
        *  2번 방법 : Properties를 이용해 자동으로 읽어오게 함
        * */

        Properties p = new Properties();
        try {
            p.load(new FileReader("config.txt")); // p에 String으로 담기
            System.out.println(p);
            map = new HashMap(p); // map에 p를 object로 담기

            int i = 0;
            for (Object key : map.keySet()) {
                System.out.println("key" + i++ + " : " + key);
                Class clazz = Class.forName((String) map.get(key)); //클래스명이 key인 클래스 뽑아와
                map.put(key, clazz.newInstance()); //뽑아온 클래스 만들어서 map에 넣어
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getBean6(String key) {
        return map.get(key);
    }
}
public class Ex06_DI {
    public static void main(String[] args) {
        AppContext6 ac = new AppContext6();
        Car7 car = (Car7) ac.getBean6("car6");
        System.out.println("car6 = " + car);
    }
}
