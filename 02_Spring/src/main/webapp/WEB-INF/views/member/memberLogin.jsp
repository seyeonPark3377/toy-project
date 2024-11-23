<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주소록 로그인화면</title>
    <link rel="stylesheet" href="/css/base-style.css">
    <link rel="stylesheet" href="/css/memberLogin.css">
	<script type="text/javascript" src="/js/member.js"></script>
</head>
<body>
	<c:choose>
        <c:when test="${sessionScope.isLoggedIn == true}">
            <jsp:include page="/WEB-INF/views/main/headerOUT.jsp" flush="true"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="/WEB-INF/views/main/headerIN.jsp" flush="true"/>
        </c:otherwise>
    </c:choose>

    <div class="container">
      <c:choose>
        <c:when test="${sessionScope.isLoggedIn == true}">
            <jsp:include page="/WEB-INF/views/main/side.jsp" flush="true"/>
        </c:when>
       </c:choose>

	    
        <div class="content">
            <form action="/member/login" method="post" class="login-from">
                    <h2>로그인</h2>
                <div class="input-group">
                	<label>아이디</label>
                    <input type="text" placeholder=" 아이디를 입력하세요." name="user_id"> 
                    <label>비밀번호</label>                   
                    <input type="password" placeholder=" 비밀번호를 입력하세요." name="user_pw">
                </div>
                <button type="submit">로그인</button>
                </form>
                <div class="links">
               	 	<a href="/member/join">회원가입</a>
                	<a href="/member/search">아이디 / 비밀번호 찾기</a>
           		</div>
           		
           
        </div>    
     </div>
     
    <jsp:include page="/WEB-INF/views/main/footer.jsp" flush="true"/>
</body>
</html>