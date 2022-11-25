<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
body
{
    margin: 0;
}
form
{
    border: 3px solid #222;
    width: 400px;
    padding: 20px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    border-radius: 5px;
}
h1
{
    margin: 0;
}
.itemTitle
{
    height: 30px;
    margin-top: 20px;
}
.txt
{
    width: 100%;
    height: 25px;
    outline: none;
}
.msg
{
    height: 20px;
    text-align: center;
    font-size: 12px;
    color: red;
    position: absolute;
    top: 15%;
    right: 20px;
}
button
{
    width: 100%;
    height: 40px;
    border: none;
    margin-top: 10px;
    font-size: 16px;
}
</style>
</head>
<body>
<script>
	function frmChk2(frm) {
		if(frm.pw.value.length < 3){
			setMsg("비번은 3글자 이상이어야 하오!!", frm.pw);
			return false;
		}
		return true;
	}
	function setMsg(msg, el) {
		document.getElementById("msg").innerText = msg;
		el.select();
	}




</script>
	<form action="<c:url value='/signup/add'/>" method="post" onsubmit="frmChk2(this)">
		<h1>회원가입</h1>
		<div id="msg" class="msg">${URLDecoder.decode(param.msg, "UTF-8")}</div>
		<div class="itemTitle">아이디</div>
		<input type="text" class="txt" name="id" id="id">
		<div class="itemTitle">비밀번호</div>
		<input type="text" class="txt" name="pw" id="pw">
		<div class="itemTitle">이름</div>
		<input type="text" class="txt" name="name" id="name">
		<div class="itemTitle">이메일</div>
		<input type="text" class="txt" name="email" id="email">
		<div class="itemTitle">SNS</div>
		<div>
			<input type="checkbox" name="sns" value="fb">페이스북
			<input type="checkbox" name="sns" value="ig">인스타그램
			<input type="checkbox" name="sns" value="tw">트위터
		</div>
		<button>회원가입</button>
		<!-- submit이나 버튼이나 똑같음 -->
	</form>
	
	<script>
		const inputArr = document.getElementsByTagName("input");
		for (let i = 0; i < inputArr.length; i++) {
			inputArr[i].addEventListener("focus", function (){
				document.getElementById("msg").innerText = "";
			})
		}
	</script>
</body>
</html>