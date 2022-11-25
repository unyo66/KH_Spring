package com.bitstudy.app;

public class Ex02_1_DI_Setter {
    public static void main(String[] args) {
        A2 a = new A2();
        a.setB2(new C2()); // setB2의 매개변수만 바꿔주면 매번 다른 클래스를 A2에 주입해 가져올수있음.
        System.out.println(a);
    }
}

class C2 extends B2 {
    int lv = 99;
    String id = "admin";

    @Override
    public String toString() {
        return "C2{" +
                "lv=" + lv +
                ", id='" + id + '\'' +
                '}';
    }
}
class B2 {
    String id = "asdf";
}
class A2 {
    private B2 b2;

    public void setB2(B2 b2) {
        this.b2 = b2;
    }

    @Override
    public String toString() {
        return "A2{" +
                "b2=" + b2 +
                '}';
    }
}