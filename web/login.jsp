<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" type="text/css" href="resource/login.css">
</head>
<body>
<div class="login-container">
    <h2>로그인</h2>
    <form action="loginProcess" method="post">
        <div class="form-group">
            <label for="username">아이디</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="login-button">로그인</button>
        <p class="signup-link">계정이 없으신가요? <a href="signup.jsp">회원가입</a></p>
    </form>
</div>
</body>
</html>
