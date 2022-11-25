package com.bitstudy.app;
import java.io.FileReader;
import java.util.Properties;

class Car4 {}
class SportsCar4 extends Car4 {}
class Truck4 extends Car4 {}

public class Ex04_1_DI_Copy {
    public static void main(String[] args) throws Exception {
//        직접 객체 생성
//        Car car = new SportsCar();
//        System.out.println("car = " + car);

        Car4 car = getCar();
        System.out.println("car = " + car);

    }

    private static Car4 getCar() throws Exception {
//        Properties : 특정 파일을 읽어올 수 있는 load() 메서드를 가지고 있는 클래스
//        Map은 <Object, Object>
//        Properties는 <String, String>
//        p.load()는 파일에서 데이터 읽기, 불러오기 편함
//        config.txt 파일 생성 : 프로젝트 폴더(concept)우클릭 > new > file > 생성할 파일명
        Properties p = new Properties();
        p.load(new FileReader("config.txt")); //config 에서 클래스 정보 다 읽어옴
        Class clazz = Class.forName(p.getProperty("car4")); // key가 car인 엔트리의 값을 얻어옴, 클래스를 미리 몰라도 클래스를 실시간으로 로드할 수 있음
        return (Car4) clazz.newInstance();
    }
}