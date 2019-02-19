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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.myfront.board.Board;
import com.test.myfront.board.Criteria;
import com.test.myfront.board.PageMaker;
import com.test.myfront.comment.Comment;
import com.test.myfront.fileUpload.FileUpload;
import com.test.myfront.member.Member;
import com.test.myfront.service.BoardService;
import com.test.myfront.service.CommentService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService service;
	
	@Autowired
	FileUpload fileUpload;
	
	@Autowired
	CommentService cservice;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	@RequestMapping(value="boardWrite")
	public String boardWrite(Board board, HttpSession session) {
		
		return "/board/boardWrite";
	}
	@RequestMapping(value="boardcomplete", method=RequestMethod.POST)
	public String boardOk(Board board, HttpServletRequest request, @RequestParam("file") MultipartFile uploadFile, MultipartHttpServletRequest mRequest) {
		HttpSession session =request.getSession();
		String name=(String) session.getAttribute("login");
		board.setWriter(name);
		String path= fileUpload.fileUpload(mRequest, uploadFile);
		service.boardWrite(board);
	
		return "redirect:/board";
	}
	
	@RequestMapping(value="boardDetail")
	public ModelAndView boardDetail(Comment comment,Board board, HttpServletRequest request) {
		
		
		List<Board> detail = service.detailBoardList(board,board.getCnt());
		List<Comment> cdetail = cservice.getList(comment,board.getCnt());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("comment", cdetail);
		mv.addObject("detail",detail);
		mv.setViewName("/board/boardDetail");
		return mv;
	}
	
	@RequestMapping(value="/boardModify")
	public ModelAndView boardModify(Board board,HttpServletRequest request) {
		
		List<Board> detail = service.showDefaultModify(board,board.getCnt());
		ModelAndView mv = new ModelAndView();
		mv.addObject("modify",detail);
		mv.setViewName("/board/boardModifyForm");
		return mv;
	}
	
	@RequestMapping(value="/boardModifyOk")
	public ModelAndView boardModifyOk(Board board) {
		service.boardModify(board, board.getCnt());
	
		ModelAndView mv = new ModelAndView();
		mv.addObject("modify", board);
		mv.setViewName( "redirect:/board");
		
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
	
	@RequestMapping("/listPage")
	public ModelAndView listPage(@ModelAttribute("cri")Criteria cri, Model model) {
		
		ModelAndView mv = new ModelAndView();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		mv.addObject("pageMaker", pageMaker);
		mv.addObject("list", service.listCriteria(cri));
		mv.setViewName("board/board");
		return mv;
	}
	
}
