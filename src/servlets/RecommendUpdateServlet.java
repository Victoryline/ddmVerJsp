package servlets;

import dao.BoardDAO;
import dao.RecommendDAO;
import dto.BoardDTO;
import dto.RecommendDTO;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/RecommendUpdateServlet")
public class RecommendUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        RecommendDAO rDao = new RecommendDAO();

        String b_id = request.getParameter("b_id");
        String u_id = request.getParameter("u_id");
        String rec_gbn = request.getParameter("rec_gbn");
        try {

            if (u_id == null || rec_gbn == null) {
                int likeAmount = rDao.getRecommendationCountByBoardId(Integer.parseInt(b_id), "G");
                int dislikeAmount = rDao.getRecommendationCountByBoardId(Integer.parseInt(b_id), "B");
                out.println("{\"likeAmount\": " + likeAmount + ", \"dislikeAmount\": " + dislikeAmount + "}");
                System.out.println("{\"likeAmount\": " + likeAmount + ", \"dislikeAmount\": " + dislikeAmount + "}");

                return;
            }


            //갯수 추가
            rDao.addRecommendation(new RecommendDTO(u_id, Integer.parseInt(b_id), rec_gbn));

            //갯수 갱신
            int likeAmount = rDao.getRecommendationCountByBoardId(Integer.parseInt(b_id), "G");
            int dislikeAmount = rDao.getRecommendationCountByBoardId(Integer.parseInt(b_id), "B");
            out.println("{\"likeAmount\": " + likeAmount + ", \"dislikeAmount\": " + dislikeAmount + "}");
            System.out.println("{\"likeAmount\": " + likeAmount + ", \"dislikeAmount\": " + dislikeAmount + "}");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}