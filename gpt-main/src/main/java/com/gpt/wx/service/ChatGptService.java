package com.gpt.wx.service;

import com.gpt.wx.domain.WeiXinMsgDTO;
import com.gpt.wx.domain.WeiXinMsgVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * chat gpt服务
 *
 * @author dong.zhang
 * @date 2023/3/25 12:07
 */
public interface ChatGptService {

    /**
     * 聊天
     *
     * @param request 请求参数
     * @return String
     * @author dong.zhang
     * @date 2023/3/25 12:04
     */
    String chat(HttpServletRequest request);

    /**
     * 聊天
     *
     * @param content 内容
     * @param userId  用户id
     * @return java.util.List<java.lang.String>
     * @author dong.zhang
     * @date 2023/3/25 12:04
     */
    List<String> chat(String content, String userId);

    /**
     * 聊天
     *
     * @param params 请求参数
     * @return WeiXinMsgVO
     * @author dong.zhang
     * @date 2023/3/25 12:04
     */
    WeiXinMsgVO chat(WeiXinMsgDTO params);

}
