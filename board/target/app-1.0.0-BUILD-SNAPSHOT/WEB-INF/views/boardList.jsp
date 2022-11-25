<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="logInOutLink" value="${ sessionScope.id==null ? '/login/login':'/login/logout' }" />
<c:set var="logInOutTxt" value="${ sessionScope.id==null ? 'login':'logout' }" />

<c:set var="userId" value="${ pageContext.request.getSession(false).getAttribute('id')==null ? '':pageContext.request.getSession(false).getAttribute('id') }" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 페이지</title>
    <link rel="stylesheet" href="<c:url value='/css/common.css'/> ">
    <link rel="stylesheet" href="<c:url value='/css/h_f.css'/> ">
    <link rel="stylesheet" href="<c:url value='/css/boardList.css'/> ">
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
<script>

    let msg = "${param.msg}" // 모델 사용할때. // get 방식일떈 param. 을 붙여야함
    //let msg = "${msg}" // RedirectAttributes 사용할떄 // get 방식일떈 param. 을 붙여야함
    // alert(1);
    // alert(msg);
    // alert(2);
    if(msg=="del_success") { alert("삭제되었습니다.") }

    // Ex10 글쓰기 파일에서 넘어오는거 (여기서 실패 메세지는 필요 없음. 어짜피 board.jsp 로 가버릴테니까)
    if(msg=="WRT_OK") { alert("글쓰기 성공")}
    // Ex10 에서 넘어오는거
    if(msg=="NO_LIST") { alert("해당 게시물이 존재하지 않습니다..")}
</script>


<div class="main">
    <div class="contents_area">
        <h2>게시판 리스트</h2>

        <div class="search_area" name="search_area">
            <select id="sel_filter" name="sel_filter">
                <option value="tw">제목+내용</option>
                <option value="t">제목만</option>
                <option value="w">작성자</option>
            </select>

            <form action="" id="search_box">
<%--            <div id="search_box">--%>
                <input type="text" id="txt_search" name="txt_search" placeholder="검색어를 입력하세요">
                <input type="button" value="검색" id="btn_search" name="btn_search">
<%--            </div>--%>
            </form>

            <div id="btn_write" onclick="location.href='<c:url value="/board/write"/>'">글쓰기</div>

        </div>

        <table>
            <thead>
                <tr>
                    <th class="no">번호</th>
                    <th class="title">제목</th>
                    <th class="writer">이름</th>
                    <th class="regdate">등록일</th>
                    <th class="viewcnt">조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="boardDto" items="${list}">
                    <tr class="boardBody">
                        <td class="no">${boardDto.bno}</td>
                        <td class="title"><a href="<c:url value="/board/read?bno=${boardDto.bno}&page=${ph.page}&pageSize=${ph.pageSize}"/>">${boardDto.title}</a></td>
                        <td class="writer">${boardDto.writer}</td>
                        <td class="regdate"><fmt:formatDate value="${boardDto.reg_date}" pattern="yyyy-MM-dd" type="date"/></td>
    <%--                    <c:choose>--%>
    <%--                        <c:when test="${boardDto.reg_date.time >= startOfToday}">--%>
    <%--                            <td class="regdate"><fmt:formatDate value="${boardDto.reg_date}" pattern="HH:mm" type="time"/></td>--%>
    <%--                        </c:when>--%>
    <%--                        <c:otherwise>--%>
    <%--                            <td class="regdate"><fmt:formatDate value="${boardDto.reg_date}" pattern="yyyy-MM-dd" type="date"/></td>--%>
    <%--                        </c:otherwise>--%>
    <%--                    </c:choose>--%>
                        <td class="viewcnt">${boardDto.view_cnt}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="pagination">
            <c:if test="${ph.showPrev}">
                <a href="<c:url value='/board/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}' />" class="beginPage">[이전]</a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a href="<c:url value='/board/list?page=${i}&pageSize=${ph.pageSize}' /> " class="page ${i==ph.page?"pageActive":""}"> ${i}</a>
            </c:forEach>
            <c:if test="${ph.showNext}">
                <a href="<c:url value='/board/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}' />" class="endPage">[다음]</a>
            </c:if>
        </div>
    </div>

    <%--    <div class="content_area">--%>
    <%--        <ul class="b_list">--%>
    <%--            <li><a href="#">게시글 1</a></li>--%>
    <%--            <li><a href="#">게시글 2</a></li>--%>
    <%--            <li><a href="#">게시글 3</a></li>--%>
    <%--            <li><a href="#">게시글 4</a></li>--%>
    <%--            <li><a href="#">게시글 5</a></li>--%>
    <%--        </ul>--%>
    <%--    </div>--%>

    <script>

        $('#btn_search').on("click", function(){
            // 1. 읽기 상태면 수정 상태로 변경
            let frm = $("#frm");

            alert($('#sel_filter').val());

            <%--if(isReadOnly == "readonly") {--%>
            <%--    $('input[name="title"]').attr('readonly',false);--%>
            <%--    $('textarea[name="content"]').attr('readonly',false);--%>
            <%--    $('#btn_modify').text("등록");--%>
            <%--    $('h2').text("게시물 수정");--%>
            <%--    return; // 버튼이 '수정' 상태일때는 글자만 바꾸고 readonly 였던것들 풀어주면 됨.--%>
            <%--    // 아래 코드들은 '수정' 버튼이 '등록' 버튼으로 바뀐 상태에서 '등록' 눌렀을때 동작해야함--%>
            <%--}--%>

            <%--// 2. 수정 상태면, 수정된 내용 서버로 전송(업데이트)--%>
            <%--frm.attr('action', "<c:url value='/board/modify'/>");--%>
            <%--frm.attr('method', 'post');--%>
            <%--frm.submit();--%>
        })

    </script>
</div>
</body>
</html>