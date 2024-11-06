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
        <title>게시판 메인 페이지</title>
        <link rel="stylesheet" href="resources/index.css">
        <link rel="stylesheet" href="resources/boardWrite.css">
    </head>
    <%
        CategoryDAO cateDao = new CategoryDAO();
        List<CategoryDTO> cateList = cateDao.getAllCategories();
    %>
    <body>
        <div class="page-wrapper">
            <div class="board-write">
                <form>
                    <div class="board-title">
                        <h1>게시물 작성</h1>
                    </div>
                    <div class="board-items">
                        <select name="cate_cd">
                            <option value="">카테고리 선택</option>
                            <c:forEach var="cate" varStatus="status" items="<%= cateList%>">
                                <option value="${cate.cateCd}">${cate.name}</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="title">
                    </div>
                    <div class="board-items">
                        <textarea name="content"></textarea>
                    </div>
                    <div class="board-items">
                        <input type="submit" value="작성완료">
                        <input type="button" value="뒤로가기">
                    </div>
                </form>

            </div>
            <%@ include file="layout/footer.jsp" %>
        </div>
    </body>
</html>
