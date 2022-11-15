package com.example.test.login;

import jdk.jpackage.internal.Log;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
@Log4j2
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("Login Form page...");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login/loginform.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userid = request.getParameter("userid");
        String pw = request.getParameter("pw");
        String idremember = request.getParameter("idremember");
        String rememberme = request.getParameter("rememberme");

        log.info("userid : " + userid);
        log.info("pw : " + pw);
        log.info("idremember : " + idremember);
        log.info("rememberme : " + rememberme);

        // idremember 값이 null이 아니면 userid 를 쿠키에 저장
        if (idremember != null) {
            // 쿠키 생성
            Cookie c = new Cookie("reId", userid);
            c.setMaxAge(60 * 60 * 24 * 180); // 6개월
            response.addCookie(c);
        } else {
            Cookie c = new Cookie("reId", userid);
            c.setMaxAge(0);
            response.addCookie(c);
        }

        HttpSession session = request.getSession();

//        로그인 처리
//        사용자의 id pw 비교 -> db에 저장되어 있는 회원정보에서 id, pw 일치한지
//        id pw 문자열이 같으면 로그인처리 -> session속성에 회원의 정보를 저장

        if(userid.equals(pw)) {
            session.setAttribute("loginInfo", "로그인됨");
            response.sendRedirect("/index.jsp");
        } else {
            response.sendRedirect("/login");
        }
    }
}
