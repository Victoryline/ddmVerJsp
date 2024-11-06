package servlets;

import dao.BoardDAO;
import dao.CommentDAO;
import dto.BoardDTO;
import dto.CommentDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("")
public class IndexServlet extends HttpServlet {
    private BoardDAO boardDAO;
    private CommentDAO commentDao;

    @Override
    public void init() {
        boardDAO = new BoardDAO();
        commentDao = new CommentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 공지사항 게시글 가져오기
            List<BoardDTO> noticeList = boardDAO.getNoticeBoards();
            request.setAttribute("noticeList", noticeList);

            // 자유게시판 전체 게시글 가져오기
            List<BoardDTO> boardList = boardDAO.getTenBoards();
            request.setAttribute("boardList", boardList);

            // 최신 댓글 가져오기
            List<CommentDTO> recentComments = commentDao.getRecentComments();
            request.setAttribute("recentComments", recentComments);

            // 인기 댓글 가져오기
            List<CommentDTO> topComments = commentDao.getTopComments();
            request.setAttribute("topComments", topComments);

            // 추천수 높은 인기 게시글 가져오기
            List<BoardDTO> topRecommendedBoards = boardDAO.getTopRecommendedBoards();
            request.setAttribute("topRecommendedBoards", topRecommendedBoards);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터를 불러오는 중 오류가 발생했습니다.");
        }
    }
}
