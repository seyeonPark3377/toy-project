<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/member.js"></script>
</head>
<body>
	<header>
        <div class="logo">
            <a class="lg" href="/">Board</a>
        </div>
        <nav>
            <ul>
                <li class="dropdown">
                	<a href="/board/list">Posts</a>
                	<ul class="dropdown-content">
                		<li><a href="/board/write">게시글 작성</a>
                	</ul>
               	</li>
               	<li class="dropdown">
                	<a href="/main/index.html">Address</a>
                	<ul class="dropdown-content">
                		<li><a href="/main/index.html">연락처 등록</a>
                		<li><a href="/main/index.html" >연락처 목록</a>
                	</ul>
               	</li>
               	<li class="dropdown">
                	<a href="/member/myPage" >My Page</a>
                	<ul class="dropdown-content">
                		<li><a href="/board/list?&querySelect=content_author&orderSelect=add_date&queryWord=${sessionScope.userId}" >내가 쓴 글</a>
                		<li><a href="/member/pwCheck">내 정보 수정</a>
                	</ul>
               	</li>
                <li><a href="/member/logout">Sign Out</a></li>
            </ul>
        </nav>
    </header>

</body>
</html>