package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signupProcess")
public class SignupProcess extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String email = request.getParameter("email");

        PrintWriter out = response.getWriter();

        if (!password.equals(confirmPassword)) {
            out.println("<script>alert('비밀번호가 일치하지 않습니다.'); history.back();</script>");
        } else {
            // 데이터베이스에 유저 정보를 저장하는 로직을 여기에 추가합니다.
            // 예: DB에 연결하고 username, password, email 정보를 저장

            response.sendRedirect("login.jsp"); // 회원가입 성공 시 로그인 페이지로 이동
        }
    }
}