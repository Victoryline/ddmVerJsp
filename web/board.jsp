<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-11-06
  Time: 오후 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <link rel="stylesheet" type="text/css" href="resources/board.css">
    <link rel="stylesheet" href="resources/index.css">
</head>
<body>
<div class="content-wrapper">
    <h1>게시글 목록</h1>

    <!-- 새 글 쓰기 버튼 -->
    <div class="top-controls">
        <button class="write-button" onclick="location.href='boardWrite.jsp?cate_cd=${param.cate_cd}'">새 글 쓰기</button>
    </div>

    <c:set var="cateCd" value="${param.cate_cd != null ? param.cate_cd : 0}" scope="page"/>
    <c:set var="currentPage" value="${param.page != null ? param.page : 1}" scope="page"/>
    <c:set var="pageSize" value="2" scope="page"/>
    <c:set var="offset" value="${(currentPage - 1) * pageSize}" scope="page"/>

    <c:if test="${cateCd > 0}">
        <jsp:useBean id="boardDAO" class="dao.BoardDAO" scope="page"/>
        <c:set var="boards" value="${boardDAO.getBoardsByCategory(cateCd, offset, pageSize)}"/>
        <c:set var="totalBoardCount" value="${boardDAO.getBoardCountByCategory(cateCd)}"/>
        <c:set var="totalPages" value="${(totalBoardCount / pageSize) + (totalBoardCount % pageSize == 0 ? 0 : 1)}"/>
        <c:choose>
            <c:when test="${empty boards}">
                <h3 style="text-align: center">해당 카테고리에 게시글이 없습니다.</h3>
            </c:when>
            <c:otherwise>
                <div class="table-container">
                    <table>
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="board" items="${boards}">
                            <tr>
                                <td>${board.BId}</td>
                                <td><a href="post.jsp?b_id=${board.BId}">${board.title}</a></td>
                                <td>${board.UId}</td>
                                <td>${board.instDt}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- 페이지 네비게이션 -->
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="board.jsp?cate_cd=${cateCd}&page=${currentPage - 1}">이전</a>
                    </c:if>

                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <c:choose>
                            <c:when test="${i == currentPage}">
                                <span>${i}</span>
                            </c:when>
                            <c:otherwise>
                                <a href="board.jsp?cate_cd=${cateCd}&page=${i}">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test='<%=Integer.parseInt(pageContext.getAttribute("currentPage").toString()) < Math.floor(Double.parseDouble(pageContext.getAttribute("totalPages").toString()))%>'>
                        <a href="board.jsp?cate_cd=${cateCd}&page=${currentPage + 1}">다음</a>
                    </c:if>
                </div>
            </c:otherwise>
        </c:choose>
    </c:if>
</div>

<%@ include file="layout/footer.jsp" %>
</body>
</html>
