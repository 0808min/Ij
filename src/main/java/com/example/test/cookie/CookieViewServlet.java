package com.example.test.cookie;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CookieViewServlet", value = "/cookie/view")
@Log4j2
public class CookieViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        Cookie 확인
        Cookie[] cookies = request.getCookies();

        for (Cookie c : cookies) {
            log.info(c.getName() + " = " + c.getValue());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cookie/view.jsp");
        dispatcher.forward(request, response);

    }

}
