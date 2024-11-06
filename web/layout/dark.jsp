<style>
    body {
        background-color: #ffffff;
        color: #000000;
        font-family: Arial, sans-serif;
        transition: background-color 0.3s, color 0.3s;
    }

    header, footer {
        background-color: #f1f1f1;
        padding: 10px;
        text-align: center;
    }

    button, input[type="text"], textarea {
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-top: 10px;
        background-color: #ffffff;
        color: #000000;
        transition: background-color 0.3s, color 0.3s, border-color 0.3s;
    }

    a {
        color: #0066cc;
        transition: color 0.3s;
    }

    /* 반전 색상 다크 모드 스타일 */
    body.dark-mode {
        background-color: #000000 !important;
        color: #ffffff;
    }

    body.dark-mode * {
        background-color: #1a1a1a;
        color: #ffffff;
    }

    body.dark-mode button,
    body.dark-mode input[type="text"],
    body.dark-mode textarea {
        background-color: #333333;
        color: #ffffff;
        border-color: #666666;
    }

    body.dark-mode a {
        color: #80b3ff;
    }

    /* 다크 모드 전환 버튼 */
    .dark-mode-toggle {
        width: 50px;
        height: 50px;
        position: absolute;
        font-size: 16px;
        cursor: pointer;
        border: none;
        border-radius: 5px;
        margin-top: 20px;
        background-image: url("/resources/img/light.png");
        background-size: cover;
    }

    body.dark-mode .dark-mode-toggle {
        width: 50px;
        height: 50px;
        position: absolute;
        font-size: 16px;
        cursor: pointer;
        border: none;
        border-radius: 5px;
        margin-top: 20px;
        background-image: url("/resources/img/dark.png");
        background-size: cover;
    }
</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<button class="dark-mode-toggle" onclick="toggleDarkMode()"></button>

<script>
    // 다크 모드 상태를 토글하고 저장하는 함수
    function toggleDarkMode() {
        document.body.classList.toggle('dark-mode');
        const isDarkMode = document.body.classList.contains('dark-mode');
        const darkModeBtn = document.querySelector("#dark-mode");

        localStorage.setItem('darkMode', isDarkMode ? 'enabled' : 'disabled');
    }

    // 페이지 로드 시 이전 다크 모드 상태 복원
    window.onload = function () {
        const savedMode = localStorage.getItem('darkMode');
        if (savedMode === 'enabled') {
            document.body.classList.add('dark-mode');
        }
    }
</script>