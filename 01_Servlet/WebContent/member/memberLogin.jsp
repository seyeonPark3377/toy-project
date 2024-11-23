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
    <link rel="stylesheet" href="css/base-style.css">
    <link rel="stylesheet" href="css/memberLogin.css">
	<script type="text/javascript" src="js/member.js"></script>
</head>
<body>
	<c:choose>
        <c:when test="${sessionScope.isLoggedIn == true}">
            <jsp:include page="/main/headerOUT.jsp" flush="true"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="/main/headerIN.jsp" flush="true"/>
        </c:otherwise>
    </c:choose>

    <div class="container">
      <c:choose>
        <c:when test="${sessionScope.isLoggedIn == true}">
            <jsp:include page="/main/side.jsp" flush="true"/>
        </c:when>
       </c:choose>

	    
        <div class="content">
            <form action="memberLogin.do" method="post" class="login-from">
                    <h2>로그인</h2>
                <div class="input-group">
                	<label>아이디</label>
                    <input type="text" placeholder=" 아이디를 입력하세요." name="id"> 
                    <label>비밀번호</label>                   
                    <input type="password" placeholder=" 비밀번호를 입력하세요." name="pw">
                </div>
                <button type="submit">로그인</button>
                </form>
                <div class="links">
               	 	<a href="memberJoin.jsp">회원가입</a>
                	<a href="memberSearch.jsp">아이디 / 비밀번호 찾기</a>
           		</div>
           		<%--
                <input type="button" class="button" value="회원가입" onclick="window.location.href='http://localhost:8181/AddrProject/memberJoin.jsp';">  
                <input type="button" class="button" value="아이디/비밀번호 찾기" onclick="window.location.href='http://localhost:8181/AddrProject/memberSearch.jsp';">
                --%>
           
        </div>    
     </div>
     
    <jsp:include page="/main/footer.jsp" flush="true"/>
</body>
</html>