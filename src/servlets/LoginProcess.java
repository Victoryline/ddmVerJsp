package servlets;

import dao.UserDAO;
import dto.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginProcess")
public class LoginProcess extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO(); // DAO 객체 생성
        UserDTO user = userDAO.authenticate(username, password); // 사용자 인증 및 User 객체 반환

        PrintWriter out = response.getWriter();

        if (user != null) {
            // 세션 생성 및 User 객체 저장
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // 세션에 전체 사용자 정보 저장

        if (isAuthenticated) {
            response.sendRedirect("/index"); // 로그인 성공 시 이동할 페이지
        } else {
            out.println("<script>alert('아이디 또는 비밀번호가 잘못되었습니다.'); history.back();</script>");
        }
    }
}
