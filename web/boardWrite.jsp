<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.CategoryDTO" %>
<%@ page import="dao.CategoryDAO" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>게시글 작성</title>
        <link rel="stylesheet" href="resources/index.css">
        <link rel="stylesheet" href="resources/boardWrite.css">
    </head>

    <%
        if (session.getAttribute("id") == null) {

    %>
    <script>
        alert("로그인해야 이용 할 수 있습니다.");
        location.href = "login.jsp";
    </script>
    <%
        }
    %>
    <%

        int cate_cd = Integer.parseInt(request.getParameter("cate_cd"));
        CategoryDAO cateDao = new CategoryDAO();
        List<CategoryDTO> cateList = cateDao.getAllCategories();
    %>
    <body>
        <div class="page-wrapper">
            <div class="board-write">
                <div>
                    <div class="board-title">
                        <h1>게시물 작성</h1>
                    </div>
                    <div class="board-items">
                        <select name="cate_cd" class="required" data-title="카테고리">
                            <option value="">카테고리 선택</option>
                            <%
                                if (cateList != null) {
                                    for (CategoryDTO cate : cateList) {
                            %>
                            <option <%if (cate_cd == cate.getCateCd()) {%> selected <%}%> value="<%= cate.getCateCd() %>"><%= cate.getName() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                        <input type="text" name="title" class="required" data-title="제목">
                    </div>
                    <div class="board-items">
                        <textarea name="content" class="required" data-title="내용"></textarea>
                    </div>
                    <div class="board-items">
                        <input type="submit" value="작성완료" id="sub-btn">
                        <input type="button" value="뒤로가기" id="back-btn">
                    </div>
                </div>
            </div>
            <%@ include file="layout/footer.jsp" %>
        </div>
    </body>
</html>

<script type="text/javascript" src="resources/js/boardWrite.js"></script>
