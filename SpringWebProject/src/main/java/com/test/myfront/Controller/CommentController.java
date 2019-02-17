package com.test.myfront.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.myfront.board.Board;
import com.test.myfront.comment.Comment;
import com.test.myfront.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService service;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertOk(Comment comment,Board board,HttpSession session, HttpServletRequest request) {
		//System.out.println(comment.getContent());
		session = request.getSession();
		String name = (String)session.getAttribute("login");
		comment.setName(name);
		comment.setCommentcnt(board.getCnt());
		service.CommentInsert(comment);
		
		return "redirect:/board";
	}

	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String Modify(Comment comment,HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		String name=(String)session.getAttribute("login");
		
		//comment.setCommentcnt(board.getCnt());
		comment.setName(name);
		//comment.setContent(content);
		System.out.println(comment.getCommentcnt());
		System.out.println(comment.getContent());
		//Map<Integer,String> map = new HashMap<Integer,String>();
		//map.put(board.getCnt(), name);
		service.CommentModify(comment);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/delete")
	public String Delete(Comment comment, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name=(String)session.getAttribute("login");
		comment.setName(name);
		System.out.println(comment.getCommentcnt());
		service.CommentDelete(comment);
		return "redirect:/board";
	}
}
