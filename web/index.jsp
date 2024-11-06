<%@ page import="dto.BoardDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.BoardDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="dto.UserDTO" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>게시판 메인 페이지</title>
        <link rel="stylesheet" href="resources/index.css">
        <link rel="stylesheet" href="resources/chat.css">
    </head>
    <body>
        <div class="chat-section">
            <h2>익명 채팅</h2>
            <div id="chat-box" class="chat-box"></div>
            <div class="chat-input-wrapper">
                <input type="text" id="messageInput" placeholder="메시지를 입력하세요..." />
                <button onclick="sendMessage()">전송</button>
            </div>
        </div>
        <div class="container">
            <!-- 공지사항 섹션 -->
            <div class="board">
                <a href="board.jsp?cate_cd=1"><h2>공지사항</h2></a>
                <ul>
                    <c:forEach var="board" items="${requestScope.noticeList}">
                        <div class="ul-box">
                            <li>${board.title}</li>
                            <li><fmt:formatDate value="${board.instDt}" pattern="yyyy-MM-dd"></fmt:formatDate></li>
                        </div>
                    </c:forEach>
                </ul>
            </div>

            <!-- 자유게시판 섹션 -->
            <div class="board">
                <a href="board.jsp?cate_cd=2"><h2>자유게시판</h2></a>
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
    <script src="resources/js/index.js"></script>
    <script>
        let ws;
        let userName = "<%= session.getAttribute("user") != null ? ((UserDTO) session.getAttribute("user")).getName() : "익명" + Math.floor(Math.random() * 10000) %>";

        function connectWebSocket() {
            ws = new WebSocket("ws://localhost:8080/chat");

            ws.onopen = function () {
                console.log("소켓 연동 성공일세");
            };

            ws.onmessage = function (event) {
                const chatBox = document.getElementById("chat-box");
                const messageElement = document.createElement("p");

                if (!event.data.startsWith(userName)) {
                    messageElement.textContent = event.data;
                } else {
                    messageElement.textContent = event.data.replace(userName + ": ", "나: ");
                }

                chatBox.appendChild(messageElement);
                chatBox.scrollTop = chatBox.scrollHeight;
            };

            ws.onclose = () => {
                console.log("WebSocket 연결이 끊어졌습니다. 재연결 시도 중...");
                setTimeout(connectWebSocket, 3000);
            };
        }

        const sendMessage = () => {
            const messageInput = document.getElementById("messageInput");
            const message = messageInput.value;

            if (ws.readyState === WebSocket.OPEN) {
                if (message.trim() !== "") {
                    ws.send(userName + ": " + message);
                    messageInput.value = "";
                }
            } else {
                alert("WebSocket 연결 안되어 있음;;");
            }
        }

        document.getElementById("messageInput").addEventListener("keypress", function (event) {
            if (event.key === "Enter") {
                sendMessage();
            }
        });

        window.onload = connectWebSocket;
    </script>

</html>
