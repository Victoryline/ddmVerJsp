<%@ page import="dao.BoardDAO" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-11-06
  Time: 오후 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글제목 끌어오기</title>
</head>
<body>
    현재 파라매터: ${requestScope.b_id}

    <%
        String bid = request.getParameter("b_id");
        BoardDAO bDao = new BoardDAO();
        try {
            bDao.getBoardById(Integer.parseInt(bid));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    %>

    <jsp:useBean id="bDto" class="dto.BoardDTO"/>
    <jsp:setProperty name="bDto" property="bId" value=""/>
    <jsp:setProperty name="bDto" property="title" value=""/>
    <jsp:setProperty name="bDto" property="content" value=""/>
    <jsp:setProperty name="bDto" property="uId" value=""/>
    <jsp:setProperty name="bDto" property="cateCd" value=""/>
    <jsp:setProperty name="bDto" property="instDt" value=""/>

    ${bDto.bId}<br>
    ${bDto.title}<br>
    ${bDto.content}<br>
    ${bDto.uId}<br>
    ${bDto.cateCd}<br>
    ${bDto.instDt}<br>
</body>
</html>
