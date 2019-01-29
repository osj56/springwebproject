package com.test.myfront.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.test.myfront.board.Criteria;
import com.test.myfront.board.PageMaker;
import com.test.myfront.chatVo.ChatRoom;
import com.test.myfront.chatVo.Message;
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
	public ModelAndView chatForm(HttpServletRequest request ,Message message) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("login");
		message.setFrom(user);
		mv.addObject("chat",message.getFrom());
		mv.setViewName("/chat/chat");
		
		System.out.println("컨트롤러");
		System.out.println(session.getAttribute("login"));
		return mv;
	}
	
/*	@MessageMapping("/message")
	@SendToUser("/queue/reply")
	public String processMessageFromClient(@Payload String message,Principal principal, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		return gson.fromJson(message, Map.class).get("name").toString();
	}
	
	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}
*/
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public Message send(Message message) throws Exception {
	    String time = new SimpleDateFormat("HH:mm").format(new Date());
	    return message;
	}
	
	@RequestMapping("/chatRoom")
	public ModelAndView getRoom(@ModelAttribute("cri")Criteria cri,ChatRoom room) {
		List<ChatRoom> list = service.getRoom(room);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("/chat/chatRoom");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bservice.listCountCriteria(cri));
		mv.addObject("pageMaker",pageMaker);
		return mv;
	}
	
	@RequestMapping("/create")
	public String CreatRoom() {
		return "/chat/createRoom";
	}
	@RequestMapping("/CreateRoom")
	public String InsertChatRoom(ChatRoom room) {
		service.insertChatRoom(room);
		return "redirect:/chat/chatRoom";
	}
	
}
