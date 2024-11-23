<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 정보 수정</title>
    <link rel="stylesheet" href="css/base-style.css">
<link rel="stylesheet" href="css/memberJoin.css">
</head>
<body>
	<c:choose>
		<c:when test="${requestScope.pwCheck == -1}">
			<script>
			    alert('비밀번호를 입력하세요.');
           	</script>
		</c:when>
		<c:when test="${requestScope.pwCheck == 0}">
			<script>
			    alert('비밀번호가 일치하지 않습니다.');
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

	    
       	<form action="memberPWCheck.do" method="post">
        <div class="content">
            <div class="content__list">
                <h1>회원 정보 수정</h1>
                <P> 비밀번호를 입력하세요.</P>
                <div class="reg-line">
                   <label>아이디 </label>
                    <input type="text" value="${sessionScope.userId}" disabled>
                </div>
                <div class="reg-line">
                    <label>비밀번호</label>
                    <input type="password" id="pw" name="pw">
                </div>
                <div class="reg-btnn">
                <input type="submit" class="btn" value="수정">
                <input type="button" class="btn" value="취소" onclick="window.location.href='/SimpleProject/member/memberMain.do';">
                </div>
            </div>
        </div>
        </form>
    </div>
    <jsp:include page="/main/footer.jsp" flush="true"/>

</body>
</html>
