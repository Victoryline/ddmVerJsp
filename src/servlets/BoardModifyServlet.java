package servlets;

import dao.BoardDAO;
import dto.BoardDTO;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/boardModify")
public class BoardModifyServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBId(Integer.parseInt(request.getParameter("b_id")));
        boardDTO.setUId(request.getParameter("u_id"));
        boardDTO.setTitle(request.getParameter("title"));
        boardDTO.setContent(request.getParameter("content"));
        boardDTO.setCateCd(Integer.parseInt(request.getParameter("cate_cd")));

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        if(session.getAttribute("id") != boardDTO.getUId()) {
            out.println(false);
            return;
        }

        BoardDAO boardDAO = new BoardDAO();
        boolean result = boardDAO.updateBoard(boardDTO);

        out.println(result);
    }
}