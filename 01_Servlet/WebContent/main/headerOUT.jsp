<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/SimpleProject/member/js/member.js"></script>
</head>
<body>
	<header>
        <div class="logo">
            <a class="lg" href="/SimpleProject/main/main.jsp">Board</a>
        </div>
        <nav>
            <ul>
                <li class="dropdown">
                	<a href="/SimpleProject/board/list">Posts</a>
                	<ul class="dropdown-content">
                		<li><a href="/SimpleProject/post/board-write.jsp">게시글 작성</a>
                	</ul>
               	</li>
               	<li class="dropdown">
                	<a href="/SimpleProject/address/main">Address</a>
                	<ul class="dropdown-content">
                		<li><a href="/SimpleProject/address/write">연락처 등록</a>
                		<li><a href="/SimpleProject/address/list" >연락처 목록</a>
                	</ul>
               	</li>
               	<li class="dropdown">
                	<a href="/SimpleProject/member/memberMain.do" >My Page</a>
                	<ul class="dropdown-content">
                		<li><a href="/SimpleProject/board/list?&querySelect=content_author&orderSelect=add_date&queryWord=${sessionScope.userId}" >내가 쓴 글</a>
                		<li><a href="/SimpleProject/member/memberPWCheck.jsp">내 정보 수정</a>
                	</ul>
               	</li>
                <li><a href="/SimpleProject/member/memberOut.do">Sign Out</a></li>
            </ul>
        </nav>
    </header>

</body>
</html>