<%@ page import="dao.BoardDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="dto.BoardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp" %>
<html>
<head>
    <title>글제목 끌어오기</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="resources/index.css">
    <link rel="stylesheet" type="text/css" href="resources/post.css"/>
</head>
<body>
<div class="page-wrapper">
    <div class="main-container">

        <%
            String bid = request.getParameter("b_id");
            BoardDAO bDao = new BoardDAO();
            try {
                BoardDTO bDto = bDao.getBoard(Integer.parseInt(bid));
                pageContext.setAttribute("bDto", bDto);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        %>
        <div class="category quarter-margin-bottom">
            현재 카테고리: ${bDto.cateCd}
        </div>

        <div class="sub-header quarter-margin-bottom">
            <div class="title"><h2>${bDto.title}</h2></div>
            <div class="between-info">
                <div>유저 명: ${bDto.BId}</div>
                <div>${bDto.instDt}</div>
            </div>
            <hr>
        </div>

        <div class="main-content">
            ${bDto.content}
        </div>

        <div class="recommend-set between-info">
            <div class="recommend-button-set">
                <div class="like-amount">추천 횟수</div>
                <button class="like-button">추천</button>
            </div>
            <div class="recommend-button-set">
                <div class="dislike-amount">비추천 횟수</div>
                <button class="dislike-button">비추천</button>
            </div>
        </div>

        <hr style="margin-bottom: 10px; margin-top: 10px;">

        <div class="comment-set">
            <div class="comment-count">
                전체 댓글 수: <span>00</span> 개
            </div>

        </div>
    </div>
    <%@ include file="layout/footer.jsp" %>
</div>
<script>
    $(function(){
        $.ajax({
            url:'RecommendUpdateServlet',
            data: {b_id:${bDto.BId}},
            type: 'GET',
            dataType:'text',
            success:function(resData){
                EditLike(resData);
            }

        })
        $(".like-button").click(function(){
            let param = {
                u_id: "${sessionScope.user.UId}",
                b_id: "${bDto.BId}",
                rec_gbn: "G"
            }

            console.log(param);

            $.ajax({
                url:'RecommendUpdateServlet',
                data: param,
                type: 'GET',
                dataType:'text',
                success:function(resData){
                    EditLike(resData);
                }
            })
        })

        $(".dislike-button").click(function(){
            let param = {
                u_id: "${sessionScope.user.UId}",
                b_id: "${bDto.BId}",
                rec_gbn: "B"
            }

            console.log(param);

            $.ajax({
                url:'RecommendUpdateServlet',
                data: param,
                type: 'GET',
                dataType:'text',
                success:function(resData){
                    EditLike(resData);
                }
            })
        })
    })

    function EditLike(resData){
        let objResData = JSON.parse(resData);
        $('.like-amount').html(objResData.likeAmount);
        $('.dislike-amount').html(objResData.dislikeAmount);
    }

</script>
</body>
</html>
