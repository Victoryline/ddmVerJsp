<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>게시판 메인 페이지</title>
        <link rel="stylesheet" href="resources/index.css">
    </head>
    <body>
        <div class="page-wrapper">
            <!-- Main Container for Boards -->
            <div class="container">
                <!-- 공지사항 섹션 -->
                <div class="board">
                    <h2>공지사항</h2>
                    <ul>
                        <li>공지사항 게시판 - 게시글 1</li>
                        <li>공지사항 게시판 - 게시글 2</li>
                        <li>공지사항 게시판 - 게시글 3</li>
                        <li>공지사항 게시판 - 게시글 4</li>
                        <li>공지사항 게시판 - 게시글 5</li>
                        <li>공지사항 게시판 - 게시글 6</li>
                        <li>공지사항 게시판 - 게시글 7</li>
                        <li>공지사항 게시판 - 게시글 8</li>
                        <li>공지사항 게시판 - 게시글 9</li>
                        <li>공지사항 게시판 - 게시글 10</li>
                    </ul>
                </div>

                <!-- 자유게시판 섹션 -->
                <div class="board">
                    <h2>자유게시판</h2>
                    <ul>
                        <li>자유게시판 - 게시글 1</li>
                        <li>자유게시판 - 게시글 2</li>
                        <li>자유게시판 - 게시글 3</li>
                        <li>자유게시판 - 게시글 4</li>
                        <li>자유게시판 - 게시글 5</li>
                        <li>자유게시판 - 게시글 6</li>
                        <li>자유게시판 - 게시글 7</li>
                        <li>자유게시판 - 게시글 8</li>
                        <li>자유게시판 - 게시글 9</li>
                        <li>자유게시판 - 게시글 10</li>
                    </ul>
                </div>
            </div>

            <!-- Additional Section for Latest and Popular Content -->
            <div class="additional-content">
                <div class="content-card">
                    <h3>최신 댓글</h3>
                    <ul>
                        <li>댓글 1 - 게시글 제목</li>
                        <li>댓글 2 - 게시글 제목</li>
                        <li>댓글 3 - 게시글 제목</li>
                        <li>댓글 4 - 게시글 제목</li>
                        <li>댓글 5 - 게시글 제목</li>
                    </ul>
                </div>
                <div class="content-card">
                    <h3>인기 댓글</h3>
                    <ul>
                        <li>인기 댓글 1 - 게시글 제목</li>
                        <li>인기 댓글 2 - 게시글 제목</li>
                        <li>인기 댓글 3 - 게시글 제목</li>
                        <li>인기 댓글 4 - 게시글 제목</li>
                        <li>인기 댓글 5 - 게시글 제목</li>
                    </ul>
                </div>
                <div class="content-card">
                    <h3>인기 게시글</h3>
                    <ul>
                        <li>인기 게시글 1</li>
                        <li>인기 게시글 2</li>
                        <li>인기 게시글 3</li>
                        <li>인기 게시글 4</li>
                        <li>인기 게시글 5</li>
                    </ul>
                </div>
            </div>

            <%@ include file="layout/footer.jsp" %>
        </div>
    </body>
</html>
