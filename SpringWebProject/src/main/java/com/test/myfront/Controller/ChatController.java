package com.test.myfront.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.test.myfront.board.Criteria;
import com.test.myfront.board.PageMaker;
import com.test.myfront.chat.ChatRoom;
import com.test.myfront.chat.Message;
import com.test.myfront.service.BoardService;
import com.test.myfront.service.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
	ChatService service;
	
	@Autowired
	BoardService bservice;
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	private Gson gson = new Gson();
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@RequestMapping("/chat")
	public ModelAndView chatForm(HttpServletRequest request,HttpServletResponse response 
			,Message message, String roomname) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("login");
		message.setFrom(user);
	
		List<String> name = service.getUser(roomname);

		if(name.get(0).contains(user)==true) {
		mv.addObject("chat",message.getFrom());
		mv.setViewName("/chat/chat");
		return mv;
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('입장할 수 없는 유저입니다'); history.go(-1);</script>");
			out.flush();
			mv.addObject("deny",user);
		
			return mv;
		}
	}

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public Message send(Message message) throws Exception {
	    String time = new SimpleDateFormat("HH:mm").format(new Date());
	    return message;
	}
	
	@RequestMapping("/chatRoom")
	public ModelAndView getRoom(@ModelAttribute("cri")Criteria cri,ChatRoom room, HttpServletRequest request) {
		List<ChatRoom> list = service.getRoom(room);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("/chat/chatRoom");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		mv.addObject("pageMaker",pageMaker);
		mv.addObject("plist", service.listCriteria(cri));
		return mv;
	}
	
	@RequestMapping("/create")
	public ModelAndView CreatRoom(HttpServletRequest request, String other) {
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("login");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.addObject("other",other);
		mv.setViewName("/chat/createRoom");
		return mv;
	}
	
	@RequestMapping(value="/CreateRoom", method=RequestMethod.POST)
	public String InsertChatRoom(ChatRoom room) {
		service.insertChatRoom(room);

		return "redirect:/chat/chatRoom";
	}
	
	@RequestMapping("/listPage")
	public ModelAndView listPage(@ModelAttribute("cri")Criteria cri, Model model) {
		
		ModelAndView mv = new ModelAndView();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		mv.addObject("pageMaker", pageMaker);
		mv.setViewName("/chat/createRoom");
		return mv;
	}
	
}
