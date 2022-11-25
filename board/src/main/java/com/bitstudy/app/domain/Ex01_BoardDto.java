package com.bitstudy.app.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Ex01_BoardDto {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private int view_count;
    private int comment_count;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reg_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date up_date;

    public Ex01_BoardDto(){};
    // update할때 사용
    public Ex01_BoardDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Ex01_BoardDto{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", view_count=" + view_count +
                ", comment_count=" + comment_count +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public Date getRegist_date() {
        return reg_date;
    }

    public void setRegist_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }
}
