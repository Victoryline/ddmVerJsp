<%@ page import="dto.BoardDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.BoardDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Arrays" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>게시판 메인 페이지</title>
        <link rel="stylesheet" href="resources/index.css">
    </head>
    <body>
        <div class="page-wrapper">

            <div class="container">
                <!-- 공지사항 섹션 -->
                <div class="board">
                    <h2>공지사항</h2>
                    <ul>
                        <c:forEach var="board" items="${requestScope.boardList}">
                            <div class="ul-box">
                                <li>${board.title}</li>
                                <li><fmt:formatDate value="${board.instDt}" pattern="yyyy-MM-dd"></fmt:formatDate></li>
                            </div>
                        </c:forEach>
                    </ul>
                </div>

                <!-- 자유게시판 섹션 -->
                <div class="board">
                    <h2>자유게시판</h2>
                    <ul>
                        <c:forEach var="board" items="${requestScope.boardList}">
                            <div class="ul-box">
                                <li>${board.title}</li>
                                <li><fmt:formatDate value="${board.instDt}" pattern="yyyy-MM-dd"></fmt:formatDate></li>
                            </div>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <!-- 최신댓글 -->
            <div class="additional-content">
                <div class="content-card">
                    <h3>최신 댓글</h3>
                    <ul>
                        <c:forEach var="comment" items="${requestScope.recentComments}">
                            <div class="ul-box">
                                <li>${comment.content}</li>
                                <li>${comment.created.substring(5, 10)}</li>
                            </div>
                        </c:forEach>
                    </ul>
                </div>
                <div class="content-card">
                    <h3>인기 댓글</h3>
                    <ul>
                        <c:forEach var="comment" items="${requestScope.topComments}">
                            <div class="ul-box">
                                <li>${comment.content}</li>
                                <li>${comment.created.substring(5, 10)}</li>
                            </div>
                        </c:forEach>
                    </ul>
                </div>
                <div class="content-card">
                    <h3>인기 게시글</h3>
                    <ul>
                        <c:forEach var="board" items="${requestScope.topRecommendedBoards}">
                            <div class="ul-box">
                                <li>${board.title}</li>
                                <li style="text-align: center">${board.recommendCount}</li>
                                <li><fmt:formatDate value="${board.instDt}" pattern="MM-dd"></fmt:formatDate></li>
                            </div>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <%@ include file="layout/footer.jsp" %>
        </div>
    </body>
</html>
