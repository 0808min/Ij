package com.example.test.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CookieSearchServlet", value = "/cookie/search")
public class CookieSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cookieName = request.getParameter("cname");

        String value = "";

        Cookie[] cookies = request.getCookies();
        for(int i=0; i<cookies.length; i++) {
            Cookie c = cookies[i];
            if (cookieName.equals(c.getName())) {
                value = c.getValue();
                break;
            }

        }

        request.setAttribute("result", value);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cookie/search.jsp");
        dispatcher.forward(request, response);


    }


}
