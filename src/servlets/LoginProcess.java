package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginProcess")
public class LoginProcess extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 데이터베이스에서 유저 정보를 조회하는 로직을 여기에 추가합니다.
        boolean isAuthenticated = false; // 예시로 인증 여부를 나타내는 변수

        PrintWriter out = response.getWriter();

        if ("admin".equals(username) && "1234".equals(password)) { // 예제 검증
            isAuthenticated = true;
        }

        if (isAuthenticated) {
            response.sendRedirect("welcome.jsp"); // 로그인 성공 시 이동할 페이지
        } else {
            out.println("<script>alert('아이디 또는 비밀번호가 잘못되었습니다.'); history.back();</script>");
        }
    }
}