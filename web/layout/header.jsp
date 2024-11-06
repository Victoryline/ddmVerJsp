<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    String userName = (String) session.getAttribute("name");
%>

<header style="background-color: #2a2a3c; color: #ffffff; padding: 20px 0; text-align: center; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3); position: relative;">
    <h1 style="font-size: 2em; font-weight: bold; color: #f4f4f9; margin: 0;">게시판 메인 페이지</h1>

    <!-- 로그인 상태에 따른 버튼 표시 -->
    <div style="position: absolute; top: 50%; right: 20px; transform: translateY(-50%); display: flex; align-items: center;">
        <%
            if (userName != null) {
        %>
        <!-- 로그인 상태 -->
        <span style="color: #a5a5ff; margin-right: 15px; font-weight: 500; font-size: 1.1em;">반갑습니다, <%= userName %>님</span>
        <a href="logout.jsp" style="color: #ff4d4d; background-color: #33334a; padding: 8px 15px; border-radius: 5px; text-decoration: none; font-weight: bold; transition: background 0.3s, color 0.3s;">
            로그아웃
        </a>
        <%
        } else {
        %>
        <!-- 로그아웃 상태 -->
        <a href="login.jsp" style="color: #4c4cff; background-color: #33334a; padding: 8px 15px; border-radius: 5px; text-decoration: none; font-weight: bold; transition: background 0.3s, color 0.3s;">
            로그인
        </a>
        <%
            }
        %>
    </div>
</header>

<%@ include file="nav.jsp" %>
