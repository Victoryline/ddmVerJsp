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

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        UserDTO user = userDAO.authenticate(username, password);

        PrintWriter out = response.getWriter();

        if (user != null) {
            // 세션 생성 및 User 객체 저장
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("id", user.getUId());

            response.sendRedirect("/");
        } else {
            out.println("<script>alert('아이디 또는 비밀번호가 잘못되었습니다.'); history.back();</script>");
        }
    }
}