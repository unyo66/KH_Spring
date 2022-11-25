package com.bitstudy.app.controller;

import com.bitstudy.app.dao.UserDao;
import com.bitstudy.app.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;


/**
 * 1. 폼 - 사용자가 form 페이지에서 정보를 삽입 > 회원가입 버튼 클릭 하면 해당 정보들을 싹 다 저장
 *    컨트롤러 - 폼에서 저장한 정보 불러오기
 * 2. 유효성 검사
 * 3. 통과 - DB에 정보 저장
 *    실패 - 오류메세지 띄우고, 다시 회원가입창 띄움
 *
 * */
@Controller
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    UserDao userDao;
    @InitBinder
    public void makeArr(WebDataBinder binder) {
        ConversionService conversionService = binder.getConversionService();
        System.out.println(conversionService);

//        binder.signupCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));
    }


    @GetMapping("/add")
    public String signup() {
        return "signupForm";
    }
    @PostMapping("/add")
    public String save(UserDto user, Model m) throws Exception {
        UserDto userInDB = userDao.selectUser(user.getId());
        if (userInDB == null || userInDB.equals("null")) {
            if (userDao.insertUser(user) != 0) {
                return "redirect:/";
            }
        }
        else {
            String msg = URLEncoder.encode("사용중인 아이디입니다.", "utf-8");
            return "redirect:/signup/add?msg="+msg;
        }
        return "index";
    }

    private boolean valChk(UserDto user) {
        if(user.getId().length() < 3) {
            return false;
        }
        return true;
    }

}

