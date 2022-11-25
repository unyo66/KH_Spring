package com.bitstudy.app.controller;

import com.bitstudy.app.dao.UserDao;
import com.bitstudy.app.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.sql.SQLException;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserDao userDao;
    @GetMapping("/logout")
    public String logout(HttpSession session) {
//////////////////////////세션 종료//////////////////
        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login1() {
        return "login";
    }
    @PostMapping("/login")
    public String login2(UserDto userDto, boolean rememberId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = userDto.getId();
        String pw = userDto.getPw();
        if (!loginCheck(id, pw)) {
            String msg = URLEncoder.encode("아이디나 비밀번호가 틀립니다.", "UTF-8");
            return "redirect:/login/login?msg=" + msg;
        }


//////////////////////////쿠키//////////////////
        Cookie cookie = new Cookie("id", userDto.getId());
        if (!rememberId) {
            cookie.setMaxAge(0);
        }
        response.addCookie(cookie);

//////////////////////////세션//////////////////
        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        if ((String) session.getAttribute("previous_page") == "board")//보드로 가려는 중이었다면 그 정보 삭제하고 보드로 보내주기
        {
            session.removeAttribute("previous_page");
            return "redirect:/board/list";
        }
        return "redirect:/";
    }

    private boolean loginCheck(String id, String pw) throws SQLException {
        if(id.length() < 4) {
            return false;
        }
        else {
            System.out.println("getId: " + id);

            UserDto userDto = userDao.selectUser(id);
            System.out.println(userDto);

            // 있으면
            if(userDto == null)
                return false;
            else                return pw.equals(userDto.getPw());
        }
    }
}
