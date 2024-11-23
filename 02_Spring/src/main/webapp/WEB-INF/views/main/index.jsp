<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="com.javaEdu.myapp.hr.model.ResultContent" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>메인 페이지</title>
    <link rel="stylesheet" href="/css/base-style.css">
    <link rel="stylesheet" href="/css/main.css">
    <script type="text/javascript" src="/js/mainImages.js"></script>
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
    
    <main>
        <div class="prev-button"><img src="/images/이전이전.png"></div>
        
        <div class="container">
            <div class="carousel">
                <div class="cell"><a href="#"><img src="/images/개3.jpg" alt="게시판" /></a></div>
                <div class="cell"><a href="#"><img src="/images/다른개.jpg" alt="마이페이지" /></a></div>
                <div class="cell"><a href="#"><img src="/images/개.jpg" alt="login" /></a></div>
            </div>            
        </div>
        
        <div class="next-button"><img src="/images/다음다음.png"></div>

    </main>
    
	<jsp:include page="/WEB-INF/views/main/footer.jsp" flush="true"/>
</body>
</html>