<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>메인 페이지</title>
    <link rel="stylesheet" href="/SimpleProject/main/css/base-style.css">
    <link rel="stylesheet" href="/SimpleProject/main/css/main.css">
    <script type="text/javascript" src="/SimpleProject/main/js/mainImages.js"></script>
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
    
    <main>
        <div class="prev-button"><img src="/SimpleProject/main/image/이전이전.png"></div>
        
        <div class="container">
            <div class="carousel">
                <div class="cell"><a href="#"><img src="/SimpleProject/main/image/개3.jpg" alt="게시판" /></a></div>
                <div class="cell"><a href="#"><img src="/SimpleProject/main/image/다른개.jpg" alt="마이페이지" /></a></div>
                <div class="cell"><a href="#"><img src="/SimpleProject/main/image/개.jpg" alt="login" /></a></div>
            </div>            
        </div>
        
        <div class="next-button"><img src="/SimpleProject/main/image/다음다음.png"></div>

    </main>
    
	<jsp:include page="/main/footer.jsp" flush="true"/>
</body>
</html>