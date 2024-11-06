package servlets;

import dao.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/checkDuplicateId")
public class CheckDuplicateId extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");

        String userId = request.getParameter("u_id");
        UserDAO userDAO = new UserDAO();
        
        boolean isDuplicate = userDAO.isUserIdDuplicate(userId);
        
        if (isDuplicate) {
            response.getWriter().write("duplicate");
        } else {
            response.getWriter().write("available");
        }
    }
}
