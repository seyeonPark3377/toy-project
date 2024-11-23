<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주소록 수정</title>
    <link rel="stylesheet" href="/SimpleProject/Address/css/address-update.css">
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
        
        <div class="content">
            <div class="content__list">
                <h1>연락처 수정</h1>
                <form action="/SimpleProject/address/updatewrite?addressId=${address.addressId}" method="post">
                    <div class="form-group">
                        <label for="contact">이름:</label>
                        <input type="text" id="contact" name="addressName" value="${address.addressName }">
                    </div>
                    <div class="form-group">
                        <label for="group">그룹:</label>
                        <select id="group" name="addressGroup">
                        	<c:forEach var="i" begin="0" end="${totalAddressGroups.size()-1 }" step="1">
                        		<c:choose>
                        			<c:when test="${totalAddressGroups[i].equals(address.addressGroup)}">
	                            		<option value="${totalAddressGroups[i] }" selected>${totalAddressGroups[i]}</option>
	                            	</c:when>
	                            	<c:otherwise>
	                            		<option value="${totalAddressGroups[i] }">${totalAddressGroups[i]}</option>
	                            	</c:otherwise>
                            	</c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="contact">연락처:</label>
                        <input type="text" id="contact" name="addressPhone" value="${address.addressPhone }">
                    </div>
                    <div class="form-group">
                        <label for="email">집전화:</label>
                        <input type="text" id="email" name="addressHome" value="${address.addressHome }">
                    </div>
                    <div class="form-group">
                        <label for="company">이메일:</label>
                        <input type="email" id="company" name="addressEmail" value="${address.addressEmail }">
                    </div>
                    <div class="form-group">
                        <label for="memo">메모:</label>
                        <textarea id="memo" name="addressMemo">${address.addressMemo }"</textarea>
                    </div>
                    <button type="submit" class="btn" value="저장">저장</button>
                    <button type="submit" class="btn" value="취소" onclick="location.href='/SimpleProject/address/read?addressId=${address.addressId}'">취소</button>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/main/footer.jsp" flush="true"/>
</body>
</html>
