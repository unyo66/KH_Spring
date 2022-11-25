<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판페이지</title>
</head>
    <style>
        body {
            margin: 0;
        }
        ul {
            padding: 0;
            margin: 0;
            list-style: none;
        }
        a {
            text-decoration: none;
            color: inherit;
        }
        .contents_area {
            width: 1100px;
            margin: 0 auto;
            display: inherit;
            justify-content: inherit;
            align-items: inherit;
        }
        .header {
            width: 100%;
            height: 40px;
            background-color: pink;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .ul_menu {
            height: 40px;
            display: flex;
            align-items: center;
            gap: 20px;
        }
        .main {
            width: 100%;
            height: 400px;
            display: flex;
            justify-content: center;
            align-self: center;
        }
        .profile_img
        {
            width: 30px;
            height: 30px;
            border-radius: 20px;
        }
                .logged
        {
            position: absolute;
            top: 50px;
            right: 100px;
            animation-name: logged;
            animation-duration: 1s;
            opacity: 0;
        }
        @keyframes logged {
            0% {opacity: 0;}
            10% {opacity: 1;}
            40% {opacity: 1;}
            100% {opacity: 0;}
        }
    </style>
    <c:set var="logged_text" value="${sessionScope.id == null ? '을 해주세요' : ' 되었습니다' }" />
    <c:set var="tmp" value="${ sessionScope.id == null ? 'ogin' : 'ogout'}" />
</head>
<body>
    <div class="logged">로그인${logged_text }.</div>
    <div class="header">
        <div class="contents_area">
            <div class="logo">
                <a href="<c:url value='/' />">BITSTUDY 13</a>
            </div>
            <ul class="ul_menu">
                <li class="li_menu">
                    <a href="<c:url value='/' />">Home</a>
                </li>
                <li class="li_menu">
                    <a href="<c:url value='/board/list13' />">Board</a>
                </li>
                <li class="li_menu">
                    <a href="<c:url value='/login/l${tmp}13' />">L${tmp}</a>
                </li>
                <li class="li_menu">
                <c:choose>
                	<c:when test="${sessionScope.id == null }">
                    	<a href="<c:url value='/signup13' />">Sign up</a>
                    </c:when>
                    <c:otherwise>
                    	<a href="#" class="profile">
                        	<img class="profile_img" src="../img/집게사장.png" alt="">
                    	</a>
                    </c:otherwise>
                </c:choose>
                </li>
            </ul>
        </div>
    </div>

</body>
</html>