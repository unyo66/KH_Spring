package com.bitstudy.app;
//Member3 : 회원이 입력한 정보를 받는 클래스
//MbService : 회원정보를 받아 DB에 입력해주는 클래스
public class Ex03_2_DI_Constructor {
    public static void main(String[] args) {
        Member3 mb = new Member3();
        MemberDetail3 mbd = new MemberDetail3();

        MbService mbs = new MbService(mb);
        System.out.println(mbs);

        mbs = new MbService(mbd);
        System.out.println(mbs);
    }
}

class MemberDetail3 extends Member3 {
    String name = "홍길동";

    @Override
    public String toString() {
        return "MemberDetail3{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
class Member3 {
    String id = "asdf";
    String pw = "1234";

    @Override
    public String toString() {
        return "Member3{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}

class MbService {
    private  Member3 mb;

    public MbService(Member3 mb) {
        this.mb = mb;
        join();
    }

    public void join() {
        save();     //DB로 보내주는 메서드
    }

    private void save() {
        System.out.println("전송완료");
    }
}
