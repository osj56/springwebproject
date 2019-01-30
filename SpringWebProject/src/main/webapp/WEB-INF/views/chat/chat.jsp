<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
    <head>
        <title>Chat WebSocket</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/chat.css">
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="<c:url value='/resources/js/chat.js'/>"></script> 
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
		
		
     	<script src=" https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
        <script type="text/javascript">
            var stompClient = null;
             
            function setConnected(connected) {
                document.getElementById('connect').disabled = connected;
                document.getElementById('disconnect').disabled = !connected;
                document.getElementById('conversationDiv').style.visibility 
                  = connected ? 'visible' : 'hidden';
                document.getElementById('response').innerHTML = '';
            }
             
    /*      function connect() {
                var socket = new SockJS('http://localhost:8080/springProj/chat');
                stompClient = Stomp.over(socket);  
                stompClient.connect({}, function(frame) {
                    setConnected(true);
                    console.log('Connected: ' + frame);
                    stompClient.subscribe('/topic/messages', function(messageOutput) {
                        showMessageOutput(JSON.parse(messageOutput.body));
                    });
                	 stompClient.subscribe("/user/queue/errors", function(message) {
                         alert("Error " + message.body);
                     });
              
                     stompClient.subscribe("/user/queue/reply", function(message) {
                         alert("Message " + message.body);
                     })
                 }, function(error) {
                     alert("STOMP error " + error)
                });
            }
             
            function disconnect() {
                if(stompClient != null) {
                    stompClient.close();
                }
                setConnected(false);
                console.log("Disconnected");
            }
       */      
       function connect() {
           var socket = new SockJS('/springProj/chat');
           stompClient = Stomp.over(socket);  
           stompClient.connect({}, function(frame) {
               setConnected(true);
               console.log('Connected: ' + frame);
               stompClient.subscribe('/topic/messages', function(messageOutput) {
                   showMessageOutput(JSON.parse(messageOutput.body));
               });
           });
       }
        
       function disconnect() {
           if(stompClient != null) {
               stompClient.disconnect();
           }
           setConnected(false);
           console.log("Disconnected");
       }
           function sendMessage() {
                var from = document.getElementById('from').value;
                var text = document.getElementById('text').value;
                stompClient.send("/app/chat", {}, 
                  JSON.stringify({'from':from, 'text':text}));
            }
             
            function showMessageOutput(messageOutput) {
                var response = document.getElementById('response');
                var p = document.createElement('p');
                p.style.wordWrap = 'break-word';
                p.appendChild(document.createTextNode("["+messageOutput.from+"]"+"  :  "+ messageOutput.text ));
                response.appendChild(p);
            }
            
        </script>
    </head>
    <body onload="disconnect()">
    <c:forEach items="${chat}" var="row">
    <div class="container">
    <div class="row chat-window col-xs-5 col-md-3" id="chat_window_1" style="margin-left:10px;">
        <div class="col-xs-12 col-md-12">
        	<div class="panel panel-default">
                <div class="panel-heading top-bar">
                    <div class="col-md-8 col-xs-8">
                        <h3 class="panel-title"><span class="glyphicon glyphicon-comment"></span> Chat</h3>
                    </div>
                    <div class="col-md-4 col-xs-4" style="text-align: right;">
                        <a href="#"><span id="minim_chat_window" class="glyphicon glyphicon-minus icon_minim"></span></a>
                        <a href="#"><span class="glyphicon glyphicon-remove icon_close" data-id="chat_window_1"></span></a>
                    </div>
                </div>
                 <div id="conversationDiv">
                <div class="panel-body msg_container_base">
                    <div class="row msg_container base_sent">
                        <div class="col-md-10 col-xs-10">
                            <div class="messages msg_sent">
                         
                               <p id="response"></p>
                      
                            </div>
                        </div>
                     
                    </div>
                   
                   
                   
                 
                </div>
                </div>
                <div class="panel-footer">
                    <div class="input-group">
                        <input id="text" type="text" class="form-control input-sm chat_input" placeholder="Write your message here..." />
                         
                        <span class="input-group-btn">
                     	  <button class="btn btn-primary btn-sm" id="sendMessage" onclick="sendMessage();">Send</button>
                         
                   
                        </span>
                          
                    </div>
                </div>
                
    		</div>
        </div>
    </div>
    <input type="hidden" id="from" value="${row}"/>
     <button class="btn btn-primary btn-sm" id="connect" onclick="connect();">Connect</button>
     <button class="btn btn-primary btn-sm" id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>

     <a class="btn btn-primary btn-sm"  href="${cp}/">메인</a>   
    <div class="btn-group dropup">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
            <span class="glyphicon glyphicon-cog"></span>
            <span class="sr-only">Toggle Dropdown</span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li><a href="#" id="new_chat"><span class="glyphicon glyphicon-plus"></span> Novo</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-list"></span> Ver outras</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-remove"></span> Fechar Tudo</a></li>
            <li class="divider"></li>
            <li><a href="#"><span class="glyphicon glyphicon-eye-close"></span> Invisivel</a></li>
        </ul>
    </div>
</div>
	</c:forEach>
    </body>
</html>