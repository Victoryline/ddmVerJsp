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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="signup-container">
    <h2>회원가입</h2>
    <form id="signupForm" action="signupProcess" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="u_id">아이디</label>
            <input type="text" id="u_id" name="u_id" required>
            <button type="button" class="check-duplicate-button" onclick="checkDuplicateId()">중복 체크</button>
            <p id="id-check-result" style="color: red;"></p>
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

<script>
    function checkDuplicateId() {
        var userId = $("#u_id").val();

        if (userId === "") {
            $("#id-check-result").text("아이디를 입력하세요.");
            return;
        }

        $.ajax({
            url: "checkDuplicateId",
            method: "POST",
            data: { u_id: userId },
            success: function(response) {
                if (response === "available") {
                    $("#id-check-result").text("사용 가능한 아이디입니다.").css("color", "green");
                } else {
                    $("#id-check-result").text("이미 사용 중인 아이디입니다.").css("color", "red");
                }
            },
            error: function() {
                $("#id-check-result").text("아이디 확인 중 오류가 발생했습니다.");
            }
        });
    }

    function validateForm() {
        var userId = document.getElementById("u_id").value;
        var name = document.getElementById("name").value;
        var email = document.getElementById("email").value;
        var password = document.getElementById("pw").value;
        var confirmPassword = document.getElementById("confirm_pw").value;
        var idCheckResult = document.getElementById("id-check-result").innerText;

        // 아이디 길이 검사
        if (userId.length < 4 || userId.length > 12) {
            alert("아이디는 4자 이상, 12자 이하로 입력해주세요.");
            return false;
        }

        // 이름 유효성 검사
        if (name.trim() === "") {
            alert("이름을 입력해주세요.");
            return false;
        }

        // 이메일 유효성 검사
        var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailPattern.test(email)) {
            alert("유효한 이메일 주소를 입력해주세요.");
            return false;
        }

        // 비밀번호 길이 검사
        if (password.length < 6) {
            alert("비밀번호는 6자 이상이어야 합니다.");
            return false;
        }

        // 비밀번호 확인 일치 여부 검사
        if (password !== confirmPassword) {
            alert("비밀번호가 일치하지 않습니다.");
            return false;
        }

        // 아이디 중복 체크 검사
        if (idCheckResult === "이미 사용 중인 아이디입니다.") {
            alert("다른 아이디를 사용해주세요.");
            return false;
        }

        return true; // 모든 검사를 통과하면 폼 제출
    }
</script>
</body>
</html>
