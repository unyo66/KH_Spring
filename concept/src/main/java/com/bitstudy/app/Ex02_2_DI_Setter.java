package com.bitstudy.app;
/*
* 예제 - 회원가입
*
* 1) Member 클래스 : 회원정보
* 2) MemberService 클래스 : join메서드
* */
public class Ex02_2_DI_Setter {
    public static void main(String[] args) {
        MemberService m = new MemberService();
        m.setMember(new Admin());
        m.print();
    }
}

class Admin extends Member2 {
    String id = "admin";
    String pw = "1234!";

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getPw() {
        return pw;
    }

    @Override
    public void setPw(String pw) {
        this.pw = pw;
    }
}
class Member2 {
    String id = "asdf";
    String pw = "1234";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}

class MemberService {
    private Member2 member;

    public void setMember(Member2 member) {
        this.member = member;
    }
    public void print() {
        System.out.println("id : " + member.getId());
        System.out.println("pw : " + member.getPw());
    }
}
