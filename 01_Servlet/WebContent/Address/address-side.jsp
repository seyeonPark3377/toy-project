<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주소록 상세</title>
    <link rel="stylesheet" href="/SimpleProject/Address/css/address-main.css">
</head>
<body>
           <div class="side">
            <div class="leftSide">
                <h2>주소록</h2>
                <form action="/SimpleProject/address/list" method="post">
	                <div class="group">
	                	<c:choose>
		                    <c:when test="${addressBookmark==1}">
		                    	<input type="checkbox" name="addressBookmark" value="1" checked/>즐겨찾기
		                    </c:when>
		                    <c:otherwise>
		                    	<input type="checkbox" name="addressBookmark" value="1" />즐겨찾기
		                    </c:otherwise>
	                    </c:choose>
	                </div>
	                <c:if test="${selectedAddressGroups.size()>0 }">
		                <c:forEach var="i" begin="0" end="${selectedAddressGroups.size()-1}">
			                <div class="group">
			                	<c:choose>
			                		
				                    <c:when test="${selectedAddressGroups[i].selected }">
				                    	<input type="checkbox" name="${totalAddressGroups[i] }" value="${totalAddressGroups[i] }" checked/>${totalAddressGroups[i] }
				                    </c:when>
				                    <c:otherwise>

				                    	<input type="checkbox" name="${totalAddressGroups[i] }" value="${totalAddressGroups[i] }" />${totalAddressGroups[i] }
				                    </c:otherwise>
			                    </c:choose>
			                </div>
			            </c:forEach>
		            </c:if>
	               
	                <div class="group">
                    	<input type="submit" class="btn" value="적용">
                	</div>
                </form>
  
            </div>
        </div>
</body>
</html>
