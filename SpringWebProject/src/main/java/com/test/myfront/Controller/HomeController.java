  package com.test.myfront.Controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.test.myfront.board.Board;
import com.test.myfront.board.Criteria;
import com.test.myfront.board.PageMaker;
import com.test.myfront.member.Member;
import com.test.myfront.service.BoardService;
import com.test.myfront.service.MemberService;

@Controller
public class HomeController {
	
	
	@Autowired
	MemberService service;
	@Autowired
	BoardService bservice;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;



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
	public String memjoin(Member member){
		String enc = passwordEncoder.encode(member.getMemPw());
		member.setMemPw(enc);
		service.memberRegister(member);
		return "/home";
		
	}
	
	@RequestMapping(value="/loginForm", method=RequestMethod.POST)
	public String getLoginCheck(Member member, HttpSession session) {
		Member mem=service.memberSearch(member);
		
		
		if(this.passwordEncoder.matches(member.getMemPw(), mem.getMemPw())==true) {
		
		session.setAttribute("login", mem.getMemId());
		return "redirect:/";
		}
		else return "/board/login";
	}
	
	@RequestMapping(value="/logOut")
	public String logOut(Member member, HttpSession session) {
		session.invalidate();
		return "/home";
	}
	
	@RequestMapping(value="/clientInfo")
	public ModelAndView clientInfo(Member member, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memId =(String)session.getAttribute("login");
		member.setMemId(memId);
		ModelAndView mv = new ModelAndView();
		List<Member> mem = service.clientInfo(member);
		
		mv.addObject("list", mem);
		mv.setViewName("/board/clientInfo");
		return mv;
	}
	@RequestMapping("/memModifyForm")
	public ModelAndView memModify(Member member, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memId =(String)session.getAttribute("login");
		member.setMemId(memId);
		List<Member> mem = service.clientInfo(member);
		ModelAndView mv = new ModelAndView();
		mv.addObject("modify",mem);
		mv.setViewName("/board/memModify");
		return mv;
		
	}
	@RequestMapping(value="/memModify", method=RequestMethod.POST)
	public String memModify(Member member) {
		String enc = passwordEncoder.encode(member.getMemPw());
		member.setMemPw(enc);
		service.memberModify(member);
		return "redirect:/";
	}
	@RequestMapping("/memDeleteForm")
	public ModelAndView memDeleteForm(Member member, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memId =(String)session.getAttribute("login");
		member.setMemId(memId);
		List<Member> mem = service.clientInfo(member);
		ModelAndView mv = new ModelAndView();
		mv.addObject("delete",mem);
		mv.setViewName("/board/memDelete");
		return mv;
		
	}
	@RequestMapping(value="/memDelete")
	public String memDelete(Member member, HttpServletRequest request) {
		Member mem=service.memberSearch(member);
		if(this.passwordEncoder.matches(member.getMemPw(), mem.getMemPw())==true) {
		HttpSession session = request.getSession();
		session.invalidate();
		service.memberDelete(member);
		return "/home";
		}else return "redirect:/";
		
	}
	@RequestMapping(value="/board")
	public ModelAndView board(Board board, HttpServletRequest request, @ModelAttribute("cri")Criteria cri) {
		ModelAndView mv = new ModelAndView();           
		HttpSession session = request.getSession();
		session.getAttribute("login");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bservice.listCountCriteria(cri));   
		
		List<Board> list = bservice.boardList(board);
		Member mem = new Member();
		
		
		mv.addObject("list",list);
		mv.addObject("pageMaker", pageMaker);
		mv.addObject("plist", bservice.listCriteria(cri));
		mv.setViewName("/board/board");
	
		return mv;
	}
}
