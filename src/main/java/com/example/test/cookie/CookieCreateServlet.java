package com.example.test.cookie;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "CookieCreateServlet", value = "/cookie/create")
@Log4j2
public class CookieCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie cookie = new Cookie("userid", "cool");
        cookie.setMaxAge(60*10);
        response.addCookie(cookie);

        // 유니크한 식별 값
        UUID str = UUID.randomUUID();
        log.info("유니크한 식별 값 생셩 => " + str.toString());
        Cookie c = new Cookie("uuid", str.toString());
        c.setMaxAge(60*60*24*7);

        c.setHttpOnly(true); // Javascript에서 접속 불가.

        response.addCookie(c);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cookie/create.jsp");
        dispatcher.forward(request, response);


    }


}
