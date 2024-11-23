<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주소록 상세</title>
    <link rel="stylesheet" href="/SimpleProject/Address/css/address-read.css">
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
                <h1>${address.addressName }</h1>
                <div class="form-group">
                    <label>그룹</label>
                    <select disabled>
                        <!-- <option value="family">가족</option> -->
                        <option value="${address.addressGroup }">${address.addressGroup }</option>
                        <!-- <option value="work">직장</option> -->
                    </select>
                </div>
                <div class="form-group">
                    <label>연락처</label>
                    <input type="text" value="${address.addressPhone }" disabled>
                </div>
                <div class="form-group">
                    <label>집전화</label>
                    <input type="email" value="${address.addressHome }" disabled>
                </div>
                <div class="form-group">
                    <label>이메일</label>
                    <input type="text" value="${address.addressEmail }" disabled>
                </div>
                <div class="form-group">
                    <label>메모</label>
                    <textarea disabled>${address.addressMemo }</textarea>
                </div>
                <input type="submit" onclick="location.href='/SimpleProject/address/update?addressid=${address.addressId}'" class="btn" value="수정"/>
                <input type="submit" class="btn" onclick="location.href='/SimpleProject/address/list'" value="목록"/>
                <input type="submit" onclick="location.href='/SimpleProject/address/delete?addressid=${address.addressId}'" class="btn" value="삭제"/>
            </div>
        </div>
    </div>
    <jsp:include page="/main/footer.jsp" flush="true"/>
</body>
</html>
