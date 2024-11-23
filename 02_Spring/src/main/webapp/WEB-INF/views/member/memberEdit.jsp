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
<link rel="stylesheet" href="/css/base-style.css">
<link rel="stylesheet" href="/css/memberJoin.css">
<script type="text/javascript" src="/js/member.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${requestScope.checkUpdate == 1}">
			<script>
			    alert('수정되었습니다.');
			    window.location.href = '/member/myPage';
           	</script>
		</c:when>
		<%-- <c:when test="${requestScope.checkUpdate == 0}">
			<script>
			    alert('수정사항이 없습니다.');
			    window.location.href = 'memberEditRequest.do';
           	</script>
		</c:when> --%>
		<c:when test="${requestScope.checkUpdate == -1}">
			<script>
			    alert('현재 비밀번호가 일치하지 않습니다.');
           	</script>
		</c:when>
		<c:when test="${requestScope.checkUpdate == -2}">
			<script>
			    alert('수정할 비밀번호와 확인란이 일치하지 않습니다.');           	
			</script>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${requestScope.checkDelete == 0}">
			<script>
			    alert('삭제에 실패하였습니다.');
           	</script>
		</c:when>
	</c:choose>
	<c:choose>
        <c:when test="${sessionScope.isLoggedIn == true}">
            <jsp:include page="/WEB-INF/views/main/headerOUT.jsp" flush="true"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="/WEB-INF/views/main/headerIN.jsp" flush="true"/>
        </c:otherwise>
    </c:choose>

	<div class="container">
      <c:choose>
        <c:when test="${sessionScope.isLoggedIn == true}">
            <jsp:include page="/WEB-INF/views/main/side.jsp" flush="true"/>
        </c:when>
       </c:choose>


		<div class="content">
			<h2>${requestScope.member.userName}님 정보 수정</h2>
			<form action="/member/edit" method="post" id="del_form">

				<div class="reg-frm">
					<div class="reg-line">
						<label for="id">아이디</label> <input type="text" id="id" name="userId"
							value="${requestScope.member.userId}" readonly />
					</div>
					<div class="reg-line">
						<label for="current-password">현재 비밀번호</label> <input
							type="password" id="current-password" name="current-password"
							required>
					</div>
					<div class="reg-line">
						<label for="new-password">새 비밀번호</label> <input type="password"
							id="new-password" name="new-password" required>
					</div>
					<div class="reg-line">
						<label for="confirm-password">새 비밀번호 확인</label> <input
							type="password" id="confirm-password" name="confirm-password" required>
					</div>
					<div class="reg-line">
						<label for="email">이메일</label> <input type="email" id="email"
							name="userEmail" value="${requestScope.member.userEmail}">
					</div>
					<div class="reg-line">
						<label for="phone">전화번호</label> <input type="tel" id="phone"
							name="userPhone" value="${requestScope.member.userPhone}">
					</div>
					<div class="reg-line">
						<label for="address">주소</label> <input type="text" id="address"
							name="userAddress" value="${requestScope.member.userAddress}">
					</div>
				</div>


				<div class="reg-btnn">
					<input type="button" id="delete-user" value="회원 탈퇴" 	onclick="deleteSubmit()">
					<input type="submit" id="modify-user" value="정보 수정"> 
					<input type="button" id="reset" value="취소"
						onclick="window.location.href='/member/myPage;">
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/main/footer.jsp" flush="true"/>
</body>
</html>