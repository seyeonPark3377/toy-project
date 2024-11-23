<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주소록 메인</title>
    <link rel="stylesheet" href="/SimpleProject/Address/css/address-list.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="/SimpleProject/address/main">주소록</a></li>
                <li><a href="/SimpleProject/member/memberMain.do">마이페이지</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        
    <jsp:include page="/Address/address-side.jsp" flush="true"/>

        <div id = "center">    
            <div id="searchInput">
                <input type="search" placeholder="   검색어를 입력하세요.">
                <input type="button" class="btn" value="검색">
                <input type="button" class="btn" value="+" onclick="location.href='/SimpleProject/address/writebefore'">
            </div>
            <div id="contents">
            	<c:forEach var="i" begin="0" end="${addresses.size()-1}" step="1">
                	<div class="content" onclick="location.href='/SimpleProject/address/read?addressid=${addresses[i].addressId}'">
                    	<p class="gName">${addresses[i].addressGroup }</p>
                    	<div class="detail">
	                        <br>
		                        <p>${addresses[i].addressName}</p>
		                        <p>${addresses[i].addressPhone}</p>
		                        <p>${addresses[i].addressHome}</p>
		                        <p>${addresses[i].addressEmail }</p>
	                        <br>
                    	</div>
                    	<input type="button" class="editbtn" >
                	</div>
                </c:forEach>
            
            </div>
        </div>
        <div class="side">
        </div>
    </div>
    <jsp:include page="/main/footer.jsp" flush="true"/>
    
</body>
</html>