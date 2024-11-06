<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-11-06
  Time: 오전 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.CategoryDAO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Dynamic Navbar</title>
    <link rel="stylesheet" type="text/css" href="../resources/navbar.css">
</head>
<body>
<nav>
    <ul>
        <li><a href="/">홈</a></li>
        <c:forEach var="category" items="<%= new CategoryDAO().getAllCategories()%>">
            <li><a href="../board.jsp?cate_cd=${category.cateCd}">${category.name}</a></li>
        </c:forEach>
    </ul>
</nav>
</body>
</html>