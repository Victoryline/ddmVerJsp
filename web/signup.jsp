<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-11-06
  Time: 오전 11:57
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" href="resources/signup.css">
</head>
<body>
<div class="signup-container">
    <h2>회원가입</h2>
    <form action="signupProcess" method="post">
        <div class="form-group">
            <label for="u_id">아이디</label>
            <input type="text" id="u_id" name="u_id" required>
        </div>
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="pw">비밀번호</label>
            <input type="password" id="pw" name="pw" required>
        </div>
        <div class="form-group">
            <label for="confirm_pw">비밀번호 확인</label>
            <input type="password" id="confirm_pw" name="confirm_pw" required>
        </div>
        <button type="submit" class="signup-button">회원가입</button>
        <p class="login-link">이미 계정이 있으신가요? <a href="login.jsp">로그인</a></p>
    </form>
</div>
</body>
</html>
