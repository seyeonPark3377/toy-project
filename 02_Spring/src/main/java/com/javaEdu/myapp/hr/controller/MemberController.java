package com.javaEdu.myapp.hr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaEdu.myapp.hr.model.MemberVO;
import com.javaEdu.myapp.hr.service.IMemberService;

@Controller
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
	@GetMapping("/member/join")
	public String insertMember() {
		return "member/memberJoin";
	}
	
	@PostMapping("/member/join")
	public String insertMember(MemberVO member) {
		try{
			memberService.insertMember(member);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/member/login";
	}
	
	@PostMapping("/member/idDoubleCheck")
	public String doubleCheck(@RequestParam("userId") String userId, HttpServletRequest request) {
		int check = memberService.confirmId(userId);
		if(userId.length() < 4) {
			check = 2;
		}
		request.setAttribute("check", check);
		request.setAttribute("inputName", request.getParameter("userName"));
		request.setAttribute("inputId", request.getParameter("userId"));
		request.setAttribute("inputPW", request.getParameter("userPw"));
		request.setAttribute("inputCfmPW", request.getParameter("cfm-pw"));
		request.setAttribute("inputEmail", request.getParameter("userEmail"));
		request.setAttribute("inputPhone", request.getParameter("userPhone"));
		request.setAttribute("inputAddr", request.getParameter("userAddress"));
		
		return "/member/memberJoin";
	}
	
	@GetMapping("/member/login")
	public String memberLogin() {
		return "member/memberLogin";
	}
	
	@PostMapping("/member/login")
	public String memberLogin(@RequestParam("user_id") String userId, @RequestParam("user_pw") String userPw, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int check = memberService.userCheck(userId, userPw);
		System.out.println(check);
		
		if(check == 1) {
			request.setAttribute("check", check);
			
			session.setAttribute("userName", memberService.getMember(userId).getUserName());
			session.setAttribute("userId", userId);
			session.setAttribute("isLoggedIn", true);
			
			return "member/memberLoginOk";
		}
		return "member/memberLogin";
	}
	
	@GetMapping("/member/logout")
	public String memberLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "/member/memberLogin";
	}
	
	@GetMapping("/member/search")
	public String memberSearch() {
		return "member/memberSearch";
	}
	
	@PostMapping("/member/search")
	public String memberSearch(HttpServletRequest request) {
		String result = null;
		
		String formId = request.getParameter("formName");
		
		if(formId.equals("id")) {
			String name = request.getParameter("userName");
			String phoneNum = request.getParameter("userPhone");
			result = memberService.searchId(name, phoneNum);
				
		}else if(formId.equals("pw")) {
			String id = request.getParameter("userId");
			String name = request.getParameter("userName");
			String phoneNum = request.getParameter("userPhone");
			result = memberService.searchPw(id, name, phoneNum);
		}
		
		request.setAttribute("result", result);
		
		return "member/memberIDPWResult";
	}
	
	@RequestMapping("/member/myPage")
	public String myPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		if(userId != null) {
			request.setAttribute("member", memberService.getMember(userId));
			return "member/memberMain";
		}
		
		return "/member/memberLogin";
	}
	
	
	@GetMapping("/member/pwCheck")
	public String memberPWCheck() {
		return "member/memberPWCheck";
	}
	
	@PostMapping("/member/pwCheck")
	public String memberPWCheck(HttpServletRequest request, 
			@RequestParam("userPw") String userPw, 
			RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String pw = memberService.getMember(userId).getUserPw();
		int pwCheck;
		
		if(userPw == "") {
			pwCheck = -1;
			redirectAttributes.addFlashAttribute("pwCheck", pwCheck);
			return "redirect:/member/pwCheck";
		}else if(userPw.equals(pw)) {
			request.setAttribute("member", memberService.getMember(userId));
			return "member/memberEdit";
		}else {
			pwCheck = 0;
			request.setAttribute("pwCheck", pwCheck);
			return "redirect:/member/pwCheck";
		}
	}
	
	@PostMapping("/member/edit")
	public String updateMember(MemberVO member,
			@RequestParam("new-password")String newPw,
			@RequestParam("current-password")String currentPw,
			@RequestParam("confirm-password")String confirmPw, HttpServletRequest request) {
		int checkUpdate=0;
		try{
			
			if (!memberService.getMember(member.getUserId()).getUserPw().equals(currentPw)) {
				checkUpdate = -1;
				request.setAttribute("member", member);
				request.setAttribute("checkUpdate", checkUpdate);
				return "/member/memberEdit";
				
			} else if (!newPw.equals(confirmPw)) {
				checkUpdate = -2;
				request.setAttribute("member", member);
				request.setAttribute("checkUpdate", checkUpdate);
				return "/member/memberEdit";	
			} else {
				checkUpdate = 1;
				member.setUserPw(newPw);
				memberService.updateMember(member);
				HttpSession session = request.getSession();
				String userId = (String)session.getAttribute("userId");
				request.setAttribute("member", memberService.getMember(userId));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "/member/memberMain";
	}
	
	@PostMapping("/member/memberDelete")
	public String deleteMember(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try{
			String userId = (String) session.getAttribute("userId");
			session.invalidate();
			
			memberService.deleteMember(userId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/member/login";
	}

}
