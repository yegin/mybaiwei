package com.sxt.baiwei.controller;

import com.sxt.baiwei.bean.ChatResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.HashMap;

@Controller
public class WScontroller {

    @Autowired//消息发送模板
    SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 接收客户端发来的消息，参数就是消息本身、
     *
     * 通信协议可以自定义————可自定义参数的格式，可以接收json格式的数据
     *
     * 传递josn数据时不需要添加额外注解@Requestbody
     *
     * 消息发送者不是从前端传递过来的，而是从springsecurity中获取的
     *
     * @MessageMapping 指定目的地是“/app/ws/chat”（“/app”前缀是隐含的，因为我们将其配置为应用的目的地前缀）。
     * @param msg
     */
    @MessageMapping("/ws/chat")
    public void receiveMessage(String msg, Principal principal) {

        String destUser = msg.substring(msg.lastIndexOf(";") + 1, msg.length());
        String message = msg.substring(0, msg.lastIndexOf(";"));
        simpMessagingTemplate.convertAndSendToUser(destUser, "/queue/chat", new ChatResp(message, principal.getName()));
    }

    @MessageMapping("/ws/nf")
    @SendTo("/topic/nf")
    public String handleNF() {
        return "系统消息";
    }

}
