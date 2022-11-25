<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>회원가입 결과</h1>
<hr />

<h1>id: ${ user.id }</h1>
<h1>pw: ${ user.pw }</h1>
<h1>name: ${ user.name }</h1>
<h1>email: ${ user.email }</h1>
<h1>sns: ${ user.sns }</h1>
<c:forEach var="item" items="${user.sns}" varStatus="idx">
    ${idx.index}sns :  ${item} <br />
</c:forEach> <br />


<h1>id=${param.id}</h1>
<h1>pw=${param.pw}</h1>
<h1>name=${param.name}</h1>
<h1>email=${param.email}</h1>
<h1>sns=${param.sns}</h1>
</body>
</html>







