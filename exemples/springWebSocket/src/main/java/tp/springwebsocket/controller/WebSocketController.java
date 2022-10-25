package tp.springwebsocket.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import tp.springwebsocket.model.ChatMessage;

@Controller
public class WebSocketController {

	@MessageMapping("/chat") //input message received from /chat
	@SendTo("/topic/messages") //output message pubish to /topic/messages
	public ChatMessage send(ChatMessage message) throws Exception {
	    String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
	    message.setTime(time);
	    return message;
	}

}