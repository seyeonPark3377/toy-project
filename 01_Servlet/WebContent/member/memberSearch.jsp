<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디/비밀번호 찾기</title>
    <link rel="stylesheet" href="css/base-style.css">
<link rel="stylesheet" href="css/memberSearch.css">
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
            <form action="SearchIDPW.do" method="post" class="search" name="formID" target="_blank">
                <div class="title">
                    <h2>아이디 찾기</h2>
                </div>
                <div class="feild">
                    <input type="text" placeholder=" 이름을 입력하세요." name="name">                    
                    <input type="text" placeholder=" 전화번호를 입력하세요." name="phoneNum">
                    <input type="hidden" name="formName" value="id">
                </div>
                <div class="reg-btnn">
                <input type="submit" name="search" value="ID검색">    
                </div>             
            </form>
            
            <form action="SearchIDPW.do" class="search" name="formPW" target="_blank">
                <div class="title">
                    <h2>비밀번호 찾기</h2>
                </div>
                <div class="feild">
                    <input type="text" placeholder=" 아이디를 입력하세요." name="id">           
                    <input type="text" placeholder=" 이름을 입력하세요." name="name">                    
                    <input type="text" placeholder=" 전화번호를 입력하세요." name="phoneNum">
                    <input type="hidden" name="formName" value="pw">
                </div>
                <div class="reg-btnn">
                <input type="submit" name="search" value="PW검색">     
                </div>           
            </form>
             
        <div class="reg-btnnn">
         <input type="button" value="로그인" onclick="window.location.href='memberLogin.jsp'">
         <input type="button" value="회원가입" onclick="window.location.href='memberJoin.jsp'">
        </div>
        </div>
        </div> 

    <jsp:include page="/main/footer.jsp" flush="true"/>
    <script src="js/script.js"></script>
</body>

</html>