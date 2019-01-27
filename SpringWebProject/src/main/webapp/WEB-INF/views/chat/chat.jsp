<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
    <head>
        <title>Chat WebSocket</title>
        
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
                p.appendChild(document.createTextNode(messageOutput.from + ": " 
                  + messageOutput.text ));
                response.appendChild(p);
            }
            
        </script>
    </head>
    <body onload="disconnect()">
    <c:forEach items="${chat}" var="row">
        <div>
           <div>
                <input type="text" id="from" value="${row}"/>
            </div>
            <br />
            <div>
                <button id="connect" onclick="connect();">Connect</button>
                <button id="disconnect" disabled="disabled" onclick="disconnect();">
                    Disconnect
                </button>
            </div>
            <br />
            <div id="conversationDiv">
                <input type="text" id="text" placeholder="Write a message..."/>
                <button id="sendMessage" onclick="sendMessage();">Send</button>
                <p id="response"></p>
            </div>
        </div>
 </c:forEach>
    </body>
</html>