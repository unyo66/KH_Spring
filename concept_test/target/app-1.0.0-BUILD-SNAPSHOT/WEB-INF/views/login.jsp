<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
body
        {
            margin: 0;
        }
        form
        {
            width: 400px;
            border: 1px solid #ffe178;
            border-radius: 5px;
            position:absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 40px 0 80px;
        }
        #id, #pw
        {
            width: 300px;
            height: 40px;
            border: 1px solid #ffe178;
            outline: none;
            border-radius: 5px;
            padding: 0 10px;
            box-sizing: border-box;
            margin-bottom: 10px;
        }
        button
        {
            width: 300px;
            height: 50px;
            border: none;
            bottom: 1px solid;
            background-color: #ffe178;
            color: #333;
            font-size: 16px;
            font-weight: 900;
            margin: 20px 0 30px 0;
        }
        a
        {
            text-decoration: none;
            color: inherit;
        }
        #msg
        {
            height: 30px;
            font-size: 14px;
            color: red;
            margin-bottom: 20px;
        }
        .login_footer
        {
            width: 300px;
            font-size: 13px;
            display: flex;
            justify-content: space-between;
        }
</style>
</head>
<body>
	<form action="<c:url value='/login/login' />" method="post">
        <h1>로그인</h1>
        <div id="msg" class="msg">${URLDecoder.decode(param.msg, "UTF-8")}</div>
        <input type="text" name="id" id="id" placeholder="아이디" value="${ cookie.id.value }" autofocus>
        <input type="text" name="pw" id="pw" placeholder="비밀번호">
        <button>로그인</button>
        <div class="login_footer">
        	<label for="rememberId">
        		<input type="checkbox" name="rememberId" id="rememberId" ${ empty cookie.id.value ? "" : "checked" }>아이디 기억하기
        	</label>
            <span>
            	<a href="#">비밀번호 찾기</a>
            	<a href="#">회원가입</a>
            </span>
            
        </div>
    </form>
</body>
</html>