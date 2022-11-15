package com.example.test.login;

import com.example.test.login.domain.Member;
import com.example.test.login.service.MemberService;
import com.mysql.cj.xdevapi.Session;
import jdk.jpackage.internal.Log;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.UUID;

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

        try {
            Member member = MemberService.getInstance().login(userid, pw);

            if(member == null) {
                response.sendRedirect("/login?error=nf");
                return;
            }

            // rememberme 값이 on 이면 -> 회원 DB => uuid 업데이트
            if(rememberme != null && rememberme.equals("on")) {
                // cookie 저장, DB Update
                UUID uuid = UUID.randomUUID();
                Cookie c1 = new Cookie("uuid", uuid.toString());
                c1.setMaxAge(60 * 60 * 24 * 30);
                c1.setPath("/");
                response.addCookie(c1);

                // 로그인 한 사용자의 uuid를 업데이트 처리
                MemberService.getInstance().updateUUID(member.getIdx(), uuid.toString());
            }


            // 아이디와 비번이 일치하는 회원이 존재. --> 로그인 처리
            session.setAttribute("loginInfo", member.getUid());
            response.sendRedirect("/index.jsp");

        } catch (Exception e) {
//            throw new RuntimeException(e);
            response.sendRedirect("/login?error=e");

        }


//        id pw 문자열이 같으면 로그인처리 -> session속성에 회원의 정보를 저장

//        if(userid.equals(pw)) {
//            session.setAttribute("loginInfo", "로그인됨");
//            response.sendRedirect("/index.jsp");
//        } else {
//            response.sendRedirect("/login");
//        }
    }
}
