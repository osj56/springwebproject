package com.test.myfront.Controller;

import java.util.List;

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
import com.test.myfront.board.Criteria;
import com.test.myfront.board.PageMaker;
import com.test.myfront.member.Member;
import com.test.myfront.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService service;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	@RequestMapping(value="boardWrite")
	public String boardWrite(Board board, HttpSession session) {
		Member mem = new Member();
		System.out.println(mem.getMemId());
		return "/board/boardWrite";
	}
	@RequestMapping(value="boardcomplete", method=RequestMethod.POST)
	public String boardOk(Board board, HttpServletRequest request) {
		HttpSession session =request.getSession();
		String name=(String) session.getAttribute("login");
		board.setWriter(name);
		
		service.boardWrite(board);
		
	
		return "redirect:/board";
	}
	
	@RequestMapping(value="boardDetail")
	public ModelAndView boardDetail(Board board, HttpServletRequest request) {
		
		
		List<Board> detail = service.detailBoardList(board,board.getCnt());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("detail",detail);
		mv.setViewName("/board/boardDetail");
		System.out.println("상세보기 조회수");
		System.out.println(detail);
		return mv;
	}
	
	@RequestMapping(value="/boardModify")
	public ModelAndView boardModify(Board board,HttpServletRequest request) {
		
		List<Board> detail = service.showDefaultModify(board,board.getCnt());
		ModelAndView mv = new ModelAndView();
		mv.addObject("modify",detail);
		mv.setViewName("/board/boardModifyForm");
		System.out.println("d");
		System.out.println(board.getCnt());
		return mv;
	}
	
	@RequestMapping(value="/boardModifyOk")
	public ModelAndView boardModifyOk(Board board) {
		service.boardModify(board, board.getCnt());
	
		ModelAndView mv = new ModelAndView();
		mv.addObject("modify", board);
		mv.setViewName( "redirect:/board");
		
		System.out.println("아래는 수정값");
		System.out.println(board.getTitle());
		System.out.println(board.getContent());
		System.out.println(board.getCnt());
		return mv;
	}
	
	@RequestMapping(value="/boardDelete")
	public String BoardDelete(Board board) {
		service.boardDelete(board, board.getCnt());
		return "redirect:/board";
	}
	
	@RequestMapping(value="/listCri")
	public String listCri(Criteria cri, Model model) {
		model.addAttribute("list", service.listCriteria(cri));
		return "board/listCri";
	}
	
	@RequestMapping("listPage")
	public String listPage(Criteria cri, Model model) {
		
		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(131);
		model.addAttribute("pageMaker", pageMaker);
		return "board/listPage";
	}
	
}
