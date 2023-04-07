package com.gpt.wx.controller;

import com.gpt.wx.service.ChatGptService;
import com.gpt.wx.service.WxService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 * chat gpt控制器
 */
@RestController
@RequestMapping("/chatGpt")
public class ChatGptController {
    @Resource
    private ChatGptService chatGptService;
    @Resource
    private WxService wxService;

    /**
     * 用于微信接入验证
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return java.lang.String
     * @author dong.zhang
     * @date 2023/3/27 9:38
     */
    @GetMapping("/callback")
    public String callback(@RequestParam("signature") String signature,
                          @RequestParam("timestamp") String timestamp,
                          @RequestParam("nonce") String nonce,
                          @RequestParam("echostr") String echostr) {
        return wxService.checkSignature(signature, timestamp, nonce, echostr);
    }

    /**
     * 用于接收微信消息
     * @param request
     * @param pw
     * @return void
     * @author dong.zhang
     * @date 2023/3/27 9:37
     */
    @PostMapping("/callback")
    public void callback(HttpServletRequest request, PrintWriter pw) {
        pw.write(chatGptService.chat(request));
    }


}
