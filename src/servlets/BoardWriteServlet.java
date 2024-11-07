package servlets;

import dao.BoardDAO;
import dao.FileDAO;
import dto.BoardDTO;
import dto.FileDTO;
import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;

@MultipartConfig
@WebServlet("/boardWrite")
public class BoardWriteServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String u_id = session.getAttribute("id").toString();
        System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("content"));
        System.out.println(request.getParameter("cate_cd"));
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(request.getParameter("title"));
        boardDTO.setContent(request.getParameter("content"));
        boardDTO.setCateCd(Integer.parseInt(request.getParameter("cate_cd")));
        boardDTO.setUId(u_id);

        BoardDAO boardDAO = new BoardDAO();
        boolean result = boardDAO.addBoard(boardDTO);
        if (result) {

            String uploadPath = getServletContext().getRealPath("/resources/uploads");
            Part file = request.getPart("files");

            System.out.println(file);

            if (file != null && file.getSize() > 0) {
                String fileName = file.getSubmittedFileName();
                String filePath = uploadPath + "/" + fileName;

                // 파일 저장
                file.write(filePath);
                System.out.println("File uploaded to: " + filePath);

                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileName(file.getSubmittedFileName());
                fileDTO.setFilePath(uploadPath);
                fileDTO.setFileSize(file.getSize());
                FileDAO fileDAO = new FileDAO();
                result = fileDAO.insertFile(fileDTO);
            }


        }

        PrintWriter out = response.getWriter();
        out.println(result);
    }
}