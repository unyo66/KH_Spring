package com.bitstudy.app.controller;

import com.bitstudy.app.dao.Ex05_BoardDao;
import com.bitstudy.app.domain.Ex01_BoardDto;
import com.bitstudy.app.service.Ex06_BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/*
* 게시판 리스트 화면 만들기
* */
@Controller
@RequestMapping("/board")
public class Ex07_BoardController {
    /*
    * 1) 사용자가 게시판페이지로 들어올때 현재페이지와 한페이지당 보여줄 게시글 개수를 컨트롤러에서 보내줘야 함
    *
    * */
    @Autowired
    Ex06_BoardService boardService;
    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
        //////////로그인 상태임?
        if (!loginChk(request)) {
            request.getSession().setAttribute("previous_page", "board");
            return "redirect:/login/login";
        }
        /////////첫 접속때는 변수에 null 들어있으니까 초기값으로 바꿔주기
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        int totalCount = boardService.getCount();
        return "boardList";
    }
    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m) {
        Ex01_BoardDto boardDto = boardService.read(bno);
        return "redirect:/list";
    }

    private boolean loginChk(HttpServletRequest request) {
        return request.getSession().getAttribute("id") != null;
    }
}
