<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover"><title>회원가입</title>
    <link rel="stylesheet" href="/css/base-style.css">
    <link rel="stylesheet" href="/css/board-read.css">
    <script src="/js/functions.js"></script>
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
	
	
        
    <div class="container">
		<c:choose>
        <c:when test="${sessionScope.isLoggedIn == true}">
            <jsp:include page="/WEB-INF/views/main/side.jsp" flush="true"/>
        </c:when>
    	</c:choose>
	    
        <div class="content">
            <h2>게시글 확인</h2>
            <div class="reg-btn">
            	<c:if  test="${sessionScope.userId.equals(content.contentAuthor)}">
	                <button onclick="location.href='/board/update/${content.contentId}'">게시글 수정</button>
	                <button onclick="location.href='/board/delete/${content.contentId}'">게시글 삭제</button> 
                </c:if>
            </div>
            <div class="content-frm">
                <div id="content-title">
                	${content.contentTitle}
                </div>
                <div id="content-info">
                    <div id="content-author">${content.contentAuthor}</div>
                    <div id="add-date">작성일 : ${content.addDate}</div>
                    <div id="edit-date">수정일 : ${content.editDate}</div>
                </div>
                <div id="content-main">
					${content.contentMain}
                </div>

            </div>
            
            <div class="comment-frm">
                <div id="comment-reg">
                    <form action="/board/insertCmt/${content.contentId}" method="post">
                        <textarea placeholder=" 댓글을 남겨주세요." name="commentMain"></textarea>
                        <input name="commentAuthor" value="${ sessionScope.userId}" type="hidden"/>
                        <button type="submit">댓글 입력</button>
                    </form>
                </div>
                <div id="comment-view" class="${comments.size()}">
                	<c:if test="${comments == null || comments.isEmpty()}">
                		댓글이 없습니다..ㅠ^ㅠ
                	</c:if>
                	
                	<c:if test="${comments != null && comments.size()!=0}">
	                	<c:forEach var="i" begin="0" end="${comments.size()-1}" step="1">
		                	
		                	<div class="comment">
			                	
			                	<div class="comment-author">
		                            ${comments[i].commentAuthor}
		                        </div>
		                        
		                        <div class="comment-main">
		                            ${comments[i].commentMain}
		                        </div>
		                        
		                        <div class="comment_fn">
	                                <button onclick="showButtonAdd(${i})">댓글 작성</button>
	                                <c:if  test="${sessionScope.userId.equals(comments[i].commentAuthor)}">

		                                <form action="/board/cmtdelete/${comments[i].commentId}" method="post">
		                                	<button type="submit">댓글 삭제</button>
	                       				</form>
                       				</c:if>
                       			</div>
                       			
                       		</div>
                       		
                       		<%-- <div id="comment-reg-add${i}" class="comment-reg-hidden">
			                    <form action="" method="post">
			                        <textarea placeholder=" 댓글을 입력하세요" name="comment-main"></textarea>
			                        <button type="submit">댓글 입력</button>
			                    </form>
		                	</div> --%>
		                
      
	                    </c:forEach>
                    </c:if>
	                
                </div>
            </div>
            <div class="page-frm">
            	<c:if test="${prevContentVO!=null}">
	                <div class="prev-next">
	                    <a href="/board/read/${prevContentVO.contentId}">이전글 : ${prevContentVO.contentTitle}</a>
	                </div>
                </c:if>
                <c:if test="${nextContentVO!=null}">
	                <div class="prev-next">
	                    <a href="/board/read/${nextContentVO.contentId}">다음글 : ${nextContentVO.contentTitle}</a>
	                </div>
                </c:if>
            </div>
        </div>
    </div>

	<jsp:include page="/WEB-INF/views/main/footer.jsp" flush="true"/>
</body>
</html>