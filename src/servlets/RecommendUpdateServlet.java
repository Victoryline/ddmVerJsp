package servlets;

import dao.RecommendDAO;
import dto.RecommendDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.color.ICC_Profile;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("RecommendUpdateServlet")
public class RecommendUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        RecommendDAO rDao = new RecommendDAO();

        String b_id = request.getParameter("b_id");
        String u_id = request.getParameter("u_id");
        String rec_gbn = request.getParameter("rec_gbn");
        String inst_dt = request.getParameter("inst_dt");


        try {
            rDao.addRecommendation(new RecommendDTO(u_id,Integer.parseInt(b_id),rec_gbn,inst_dt));

            int likeAmount = rDao.getRecommendationCountByBoardId(Integer.parseInt(b_id),"G");
            int dislikeAmount = rDao.getRecommendationCountByBoardId(Integer.parseInt(b_id),"B");
            out.println("{\"likeAmount\": " + likeAmount + ", \"dislikeAmount\": " + dislikeAmount + "}");
            System.out.println("{\"likeAmount\": " + likeAmount + ", \"dislikeAmount\": " + dislikeAmount + "}");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}