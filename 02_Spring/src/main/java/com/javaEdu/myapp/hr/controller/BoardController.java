package com.javaEdu.myapp.hr.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaEdu.myapp.hr.model.CommentVO;
import com.javaEdu.myapp.hr.model.ContentVO;
import com.javaEdu.myapp.hr.model.ResultContent;
import com.javaEdu.myapp.hr.service.IBoardService;

@Controller
public class BoardController {
	
	@Autowired
	IBoardService boardService;
	
	
	@GetMapping("/")
	public String home() {
		return "/main/index";
	}
	@RequestMapping( "/board/list")
	public String listContent(@RequestParam(value = "querySelect", required = false) String querySelect,
					          @RequestParam(value = "orderSelect", required = false) String orderSelect,
					          @RequestParam(value = "queryWord", required = false) String queryWord,
					          @RequestParam(value = "page", required = false) String page,
					          Model model) {
		System.out.println(querySelect);
		System.out.println(queryWord);
		try {
			ResultContent resultContent = boardService.listContent(querySelect, orderSelect, queryWord, page);
			model.addAttribute("resultContent", resultContent);
			model.addAttribute("querySelect", querySelect);
			model.addAttribute("orderSelect", orderSelect);
			model.addAttribute("queryWord", queryWord);
			model.addAttribute("page", page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "board/board-list";
	}
	
	@GetMapping("/board/write")
	public String insertContent() {
		return "board/board-write";
	}
	
	@PostMapping("/board/write")
	public String insertContent(ContentVO content, Model model) {
		try{
			boardService.insertContent(content);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/delete/{contentId}")
	public String deleteContent(@PathVariable int contentId) {
		boardService.deleteContent(contentId);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/update/{contentId}")
	public String updateContent(@PathVariable("contentId") int contentId, Model model) {
		model.addAttribute("content", boardService.getContent(contentId));
		model.addAttribute("contentId",contentId);
		return "board/board-update";
	}
	
	@PostMapping("/board/update/{contentId}")
	public String updateContent(ContentVO content) {
		try{
			boardService.updateContent(content);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/read/" + content.getContentId();
	}
	
	@GetMapping("/board/read/{contentId}")
	public String getContent(@PathVariable int contentId, Model model){
		try{
			model.addAttribute("content", boardService.getContent(contentId));
		
		ContentVO prev = boardService.getPrevContent(contentId);
		List<CommentVO> commentList = boardService.getParentComments(contentId);
		model.addAttribute("comments", commentList);
		model.addAttribute("prevContentVO", prev);
		model.addAttribute("nextContentVO", boardService.getNextContent(contentId));
		
		boardService.incrementViewCountContent(contentId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "board/board-read";
	}
	
	@PostMapping("/board/read/{contentId}")
	public String getContent(int contentId) {
		boardService.getContent(contentId);
		return "redirect:/board/board-read/" + contentId;
	}
	
	@PostMapping("/board/insertCmt/{contentId}")
	public String insertComment(@PathVariable("contentId") int contentId, CommentVO comment) {
		try{
			boardService.insertComment(comment);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/read/" + contentId;
	}
	
	@PostMapping("/board/cmtdelete/{commentId}")
	public String deleteComment(@PathVariable("commentId")int commentId) {
		int contentId = boardService.getComment(commentId).getContentId();
		boardService.deleteComment(commentId);
		return "redirect:/board/read/" + contentId;
	}

}