package servlets;

import dao.BoardDAO;
import dto.BoardDTO;
import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;

@WebServlet("/boardWrite")
public class BoardWriteServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(request.getParameter("title"));
        boardDTO.setContent(request.getParameter("content"));
        boardDTO.setCateCd(Integer.parseInt(request.getParameter("cate_cd")));

        BoardDAO boardDAO = new BoardDAO();
        boolean result = boardDAO.addBoard(boardDTO);

        PrintWriter out = response.getWriter();
        out.println(result);
    }
}