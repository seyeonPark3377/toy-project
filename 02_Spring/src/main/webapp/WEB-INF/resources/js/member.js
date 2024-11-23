/**
 * 
 */
 
function showAddressBookAlert() {
            alert('로그인 후 사용 가능합니다.');
}

function deleteSubmit() {
    var result = confirm("정말로 탈퇴 하시겠습니까?");
    if (result) {
        // 사용자가 "네"를 선택한 경우
        alert("탈퇴 처리 되었습니다.");
        var form = document.getElementById("del_form");
        form.method = "post";
        form.action = "/member/memberDelete"; // 원하는 서블릿 경로 설정
        form.submit(); // 폼 제출
    } else {
        // 사용자가 "아니오"를 선택한 경우
        alert("취소하였습니다.");
        // 여기에 아니오를 선택했을 때 실행할 코드를 추가하세요.
    }
  
}

function doubleCheck() {
    var form = document.getElementById("join_form");
    form.action = "/member/idDoubleCheck"; // 원하는 서블릿 경로 설정
    form.submit(); // 폼 제출
}

function submitToServlet() {
    var form = document.getElementById("join_form");
    var id = form.elements["user_id"].value;
    var name = form.elements["user_name"].value;
    var pw = form.elements["user_pw"].value;
    var cfmPw = form.elements["cfm-pw"].value;
    var check = form.elements["check"].value;
    var join = true;
	console.log(id);
	
    if (check != -1) {
    
        alert('아이디 중복체크를 해주세요.');
        join = false;
    }

    if (id.length < 4) {
        alert('아이디를 4자 이상 입력하세요.');
        join = false;
    }

    if (!name.trim()) {
        alert("이름을 입력하세요.");
        join = false;
    }

    if (pw.length < 4) {
        alert("비밀번호를 4자 이상 입력하세요.");
        join = false;
    }

    if (pw !== cfmPw) {
        alert("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        join = false;
    }

    if (join) {
    	alert("회원가입을 축하합니다!");
        form.method = "post";
        form.action = "/member/join"; // 원하는 서블릿 경로 설정
        form.submit();
    } else {
    	alert("정보가 정확하지 않습니다. 다시 입력해주세요.");
    	form.method = "Get";
        form.action = "/member/join"; // 원하는 서블릿 경로 설정
        form.submit();
    }
}