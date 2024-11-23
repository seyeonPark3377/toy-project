<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover"><title>회원가입</title>
    <link rel="stylesheet" href="/SimpleProject/post/css/base-style.css">
    <link rel="stylesheet" href="/SimpleProject/post/css/board-write.css">
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
            <h2>게시글 작성</h2>
            
            <form action="/SimpleProject/board/write" method="post">
                <div class="content-title-input">
                    <input name="content-title" type="text" placeholder="  제목을 입력하세요." required/>
                </div>
                
				<div class="content-main-input">
					<textarea name="content-main" id="content-main-input" placeholder="  내용을 입력하세요." required></textarea>
				</div>
                
                <div class="reg-btn">
                    <button type="submit">저장</button>
                    <button type="reset">취소</button>
                </div>
            </form>
			<script type="text/javascript" src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>
            <script type="text/javascript" src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/translations/ko.js"></script>
            <script type="text/javascript">    
                ClassicEditor.create( document.querySelector('#content-m-input'), {
                    language: "ko"
                } );
            </script>
        </div>
    </div>

	<jsp:include page="/main/footer.jsp" flush="true"/>
</body>
</html>







