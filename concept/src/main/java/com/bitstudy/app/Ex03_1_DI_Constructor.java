package com.bitstudy.app;

public class Ex03_1_DI_Constructor {
    public static void main(String[] args) {
        B3 b3 = new B3();
        A3 a = new A3(b3);
        System.out.println(a);
    }
}

class B3 {
    String id = "asdf";

    @Override
    public String toString() {
        return "B3{" +
                "id='" + id + '\'' +
                '}';
    }
}

class A3 {
    private B3 b3;

    public A3(B3 b3) {
        this.b3 = b3;
    }

    @Override
    public String toString() {
        return "A3{" +
                "b3=" + b3 +
                '}';
    }
}