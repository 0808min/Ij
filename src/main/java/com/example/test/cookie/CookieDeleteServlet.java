package com.example.test.cookie;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CookieDeleteServlet", value = "/cookie/delete")
public class CookieDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie cookie = new Cookie("userid", "");
        // 쿠키 삭제 메소드는 없다 -> 소멸 시간을 0으로 적용해서 소멸
        cookie.setMaxAge(0);

        response.addCookie(cookie);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cookie/delete.jsp");
        dispatcher.forward(request, response);


    }

}
