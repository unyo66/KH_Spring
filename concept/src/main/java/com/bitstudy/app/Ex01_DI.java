package com.bitstudy.app;
//일체형
//일체형의 경우 클래스들이나 객체들 간의 결합력이 강하고, 조립형은 결합력이 낮다.
//실무에서는 유지보수를 위해 조립형을 선호.
public class Ex01_DI {
}

class B { }

class A {
    private B b;
    public A() {//생성자
        b = new B();
    }

}
