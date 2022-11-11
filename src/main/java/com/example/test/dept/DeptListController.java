package com.example.test.dept;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeptListController", value = "/dept/list")
public class DeptListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DeptService service = new DeptService();

        System.out.println("Dept List get ...");

        List<Dept> list = null;

        try {
            list = service.getList();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }

        request.setAttribute("list", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/dept/list.jsp");
        dispatcher.forward(request, response);


    }

}
