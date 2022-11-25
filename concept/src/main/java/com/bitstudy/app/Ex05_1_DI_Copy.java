package com.bitstudy.app;
import java.io.FileReader;
import java.util.Properties;

class Car5 {}
class SportsCar5 extends Car5 {}
class Truck5 extends Car5 {}
class Engine5 extends Car5 {}

/*
* new 생성자없이 주입받아서 객체 생성
* getCar()에서 car 말고 다른 클래스도 생성할 수 있게 만들고 getObject()로 바꾸기
* */

public class Ex05_1_DI_Copy {
    public static void main(String[] args) throws Exception {
//        직접 객체 생성
//        Car car = new SportsCar();
//        System.out.println("car = " + car);

        Car5 car = getObject("car5");
        System.out.println("car = " + car);

    }

    static Car5 getObject(String key) throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt")); //config 에서 클래스 정보 다 읽어옴
        Class clazz = Class.forName(p.getProperty(key)); // key가 car인 엔트리의 값을 얻어옴, 클래스를 미리 몰라도 클래스를 실시간으로 로드할 수 있음
        return (Car5) clazz.newInstance();
    }
}