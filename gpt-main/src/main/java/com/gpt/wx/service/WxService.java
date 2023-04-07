package com.gpt.wx.service;

/**
 * @author: dong.zhang
 * @date: 2023-03-25 10:10
 */
public interface WxService {

    String refreshToken();

    String checkSignature(String signature, String timestamp, String nonce, String echostr);
}
