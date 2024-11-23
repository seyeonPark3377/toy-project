<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인</title>
    <link rel="stylesheet" href="css/base-style.css">
    <link rel="stylesheet" href="css/memberLogin.css">
</head>
<body>
	<c:set var="check" value="${requestScope.check}" />
	<c:choose>
        <c:when test="${check == 0}">
            <script>
            	alert('비밀번호가 틀립니다.');
	            window.location.href='/SimpleProject/member/memberLogin.jsp';
            </script>
        </c:when>
        <c:when test="${check == -1}">
            <script>
	            alert('아이디를 확인 바랍니다.');
	            window.location.href='/SimpleProject/member/memberLogin.jsp';
            </script>
        </c:when>
	</c:choose>
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
				<h2>${sessionScope.userName}님안녕하세요.</h2>
				<div class="input-group">
				<!--<button type="button">로그인</button>
					<button type="button">로그인</button>
					<button type="button">로그인</button>  -->
				</div>

			</form>

		</div>    
     </div>
	<jsp:include page="/main/footer.jsp" flush="true"/>
</body>
</html>
