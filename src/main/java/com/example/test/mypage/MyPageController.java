package com.example.test.mypage;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MyPageController", value = "/mypage/mypage")
@Log4j2
public class MyPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("mypage ...");

        // 현재 로그인 중인지 확인
//        HttpSession session = request.getSession();

//        if(session.isNew()) {
//            log.info("비 로그인 상태....");
//            response.sendRedirect("/login");
//            return;
//        }

//        if(session.getAttribute("loginInfo") == null) {
//            log.info("비 로그인 상태....");
//            response.sendRedirect("/login");
//            return;
//        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp");
        dispatcher.forward(request, response);

    }

}
