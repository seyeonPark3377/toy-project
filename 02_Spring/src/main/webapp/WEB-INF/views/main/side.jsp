<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="side-bar">
	        <h2>사이드 메뉴</h2>
	         <ul>
	            <li><a href="/board/list">게시판</a>
	                <ul class="side-bar-list">
	                    <li><a href="/board/write">게시글 작성</a></li>
	                </ul>
	            </li>
	            <li><a href="#">주소록</a>
	                <ul class="side-bar-list">
	                    <li><a href="#" >연락처 등록</a></li>
	                	<li><a href="#">연락처 목록</a></li>
	                </ul>
	            </li>
	            <li><a href="/member/myPage">마이페이지</a>
	                <ul class="side-bar-list">
	                    <li><a href="/board/list?&querySelect=content_author&orderSelect=add_date&queryWord=${sessionScope.userId}" >내가 쓴 글</a></li>
	                	<li><a href="/member/pwCheck">내 정보 수정</a></li>
	                </ul>
	            </li>
	        </ul>
	    </div>

</body>
</html>