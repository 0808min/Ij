package com.example.test.mypage;

import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MyInfoController", value = "/mypage/myinfo")
@Log4j2
public class MyInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("mypage ...");

        // 현재 로그인 중인지 확인
//        HttpSession session = request.getSession();
//
//        if(session.isNew()) {
//            log.info("비 로그인 상태....");
//            response.sendRedirect("/login");
//            return;
//        }
//
//        if(session.getAttribute("loginInfo") == null) {
//            log.info("비 로그인 상태....");
//            response.sendRedirect("/login");
//            return;
//        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp");
        dispatcher.forward(request, response);

    }

}
