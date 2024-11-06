package servlets;

import dao.UserDAO;
import dto.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/signupProcess")
public class SignupProcess extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String userId = request.getParameter("u_id"); // 사용자 ID
        String name = request.getParameter("name");   // 사용자 이름
        String password = request.getParameter("pw"); // 비밀번호
        String confirmPassword = request.getParameter("confirm_pw"); // 비밀번호 확인
        String email = request.getParameter("email"); // 이메일

        PrintWriter out = response.getWriter();

        // 비밀번호 확인
        if (!password.equals(confirmPassword)) {
            out.println("<script>alert('비밀번호가 일치하지 않습니다.'); history.back();</script>");
        } else {
            UserDAO userDAO = new UserDAO();
            UserDTO user = new UserDTO(userId, name, email, password);

            // 회원 정보 저장 시 성공 여부 확인
            boolean isUserAdded = false;
            isUserAdded = userDAO.addUser(user);

            if (isUserAdded) {
                response.sendRedirect("login.jsp"); // 회원가입 성공 시 로그인 페이지로 이동
            } else {
                out.println("<script>alert('회원가입에 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
            }
        }
    }
}
