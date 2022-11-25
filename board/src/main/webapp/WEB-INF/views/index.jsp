<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>메인페이지</title>
    <link rel="stylesheet" href="<c:url value='/css/h_f.css'/> ">
    <link rel="stylesheet" href="<c:url value='/css/common.css'/> ">
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
                    <a href="<c:url value='/board/list' />">Board</a>
                </li>
                <li class="li_menu">
                    <a href="<c:url value='/login/l${tmp}' />">L${tmp}</a>
                </li>
                <li class="li_menu">
                <c:choose>
                	<c:when test="${sessionScope.id == null }">
                    	<a href="<c:url value='/signup/add' />">Sign up</a>
                    </c:when>
                    <c:otherwise>
                    	<a href="#" class="profile">
                        	<img class="profile_img" src="<c:url value='/img/thum.png'/>" alt="thumbnail">
                    	</a>
                    </c:otherwise>
                </c:choose>
                </li>
            </ul>
        </div>
    </div>

</body>
</html>