<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주소록 메인</title>
    <link rel="stylesheet" href="/SimpleProject/Address/css/address-main.css">
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
        <div class="side">
            <div class="leftSide">
                <h2>주소록</h2>
                <form action="/SimpleProject/address/main" method="post">
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
        <div class="content">
            <div class="content__top">
                <div class="content__box">
                    <div class="favorites">
                        <h3>즐겨찾기</h3>
                        <table>
                            <thead>
                                <tr>
                                    <th><input type="checkbox" /></th>
                                    <th>그룹</th>
                                    <th>이름</th>
                                    <th>전화번호</th>
                                    <th>이메일</th>
                                    <th>메모</th>
                                </tr>
                            </thead>
                                <c:choose>
		                        	<c:when test="${bookmarkedAddresses.size()==0 }">
			                        	<tr>
				                            <td rowspan="6">주소록 정보가 없습니다. T^T 주소록을 추가하세요 !</td>
			                            </tr>
		                        	</c:when>
		                        	<c:otherwise>
			                        	<c:forEach var="i" begin="0" end="${bookmarkedAddresses.size()-1}" step="1">
				                        	<tr>
					                            <td><input type="checkbox" /></td>
					                            <td>${bookmarkedAddresses[i].addressGroup}</td>
					                            <td>${bookmarkedAddresses[i].addressName}</td>
					                            <td>${bookmarkedAddresses[i].addressPhone}</td>
					                            <td>${bookmarkedAddresses[i].addressEmail}</td>
					                            <td>${bookmarkedAddresses[i].addressMemo}</td>
					                        </tr>
				                        </c:forEach>
		                        	</c:otherwise>
		                        	
		                        </c:choose>
                        </table>
                    </div>
                </div>
                <div class="content__box">
                    <h3>빠른 연락처 등록</h3>
                    <form id="quickAddForm" action="/SimpleProject/address/write">
                    	<input type="hidden" name="quickReg" value=${true }/>
                        <input type="text" placeholder="이름" class="input" name="addressName"/>
                        <input type="text" placeholder="전화번호" class="input" name="addressPhone" />
                        <select class="input" name="addressGroup">
                        	<c:forEach var="i" begin="0" end="${totalAddressGroups.size()-1}">
                        		<option value="${totalAddressGroups[i]}">${totalAddressGroups[i]}</option>
                        	</c:forEach>
                            
                        </select>
                        <div id="btst">
                            <input type="submit" class="btn" value="등록" >
                        </div>
                    </form>
                </div>
            </div>
            <div class="content__list">
            	<a href="/SimpleProject/address/list">
            		<h3>연락처 목록</h3>
            	</a>
                
                <table>
                    <thead>
                        <tr>
                            <th><input type="checkbox" /></th>
                            <th>그룹</th>
                            <th>이름</th>
                            <th>전화번호</th>
                            <th>이메일</th>
                            <th>메모</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                        	<c:when test="${addresses.size()==0 }">
	                        	<tr>
		                            <td rowspan="6">주소록 정보가 없습니다. T^T 주소록을 추가하세요 !</td>
	                            </tr>
                        	</c:when>
                        	<c:otherwise>
	                        	<c:forEach var="i" begin="0" end="${addresses.size()-1}" step="1">
		                        	<tr>
			                            <td><input type="checkbox" /></td>
			                            <td>${addresses[i].addressGroup}</td>
			                            <td>${addresses[i].addressName}</td>
			                            <td>${addresses[i].addressPhone}</td>
			                            <td>${addresses[i].addressEmail}</td>
			                            <td>${addresses[i].addressMemo}</td>
			                        </tr>
		                        </c:forEach>
                        	</c:otherwise>
                        	
                        </c:choose>
         
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <jsp:include page="/main/footer.jsp" flush="true"/>
</body>
</html>
