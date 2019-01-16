package com.test.myfront.Controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.test.myfront.board.Board;
import com.test.myfront.member.Member;
import com.test.myfront.service.BoardService;
import com.test.myfront.service.RMemberService;

@Controller
public class HomeController {
	
	
	@Autowired
	RMemberService service;
	@Autowired
	BoardService bservice;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value="/login", method= RequestMethod.GET)
	public String login(Member member) {
		return "/board/login";
	}
	
	@RequestMapping("/memlogin")
	public String memlogin() {
		return "home";
	}
	
	@RequestMapping(value="/memjoin", method= RequestMethod.GET)
	public String join() {
		
		return "/board/joinForm";
	}
	
	@RequestMapping(value="/join", method= RequestMethod.POST)
	public String memjoin(Member member) {
		service.memberRegister(member);
		return "/board/joinOk";
	}
	
	@RequestMapping(value="/loginForm", method=RequestMethod.POST)
	public String getLoginCheck(Member member, HttpSession session) {
		Member mem=service.memberSearch(member);
		if(mem==null) return "/board/login";
		System.out.println(mem.getMemId());
		session.setAttribute("login", mem.getMemId());
		return "redirect:/";
	}
	
	@RequestMapping(value="/logOut")
	public String logOut(Member member, HttpSession session) {
		session.invalidate();
		return "/home";
	}
	
	@RequestMapping(value="/board")
	public ModelAndView board(Board board, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		session.getAttribute("login");

		List<Board> list = bservice.boardList(board);
		Member mem = new Member();
		
		//board.setCnt(cnt);
		mv.addObject("list",list);
		mv.setViewName("/board/board");
	//	System.out.println(mem.getMemMail());
	
		return mv;
	}
}
