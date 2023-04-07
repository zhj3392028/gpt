package com.gpt.wx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gpt.wx.domain.WeiXinMsgDTO;
import com.gpt.wx.domain.WeiXinMsgVO;
import com.gpt.wx.service.ChatGptService;
import com.gpt.wx.utils.CommonUtil;
import io.github.asleepyfish.util.OpenAiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

/**
 * @author: dong.zhang
 * @date: 2023-03-24 15:35
 */
@Slf4j
@Service
public class ChatGptServiceImpl implements ChatGptService {

    @Override
    public List<String> chat(String content, String userId) {
        List<String> chatCompletion = OpenAiUtils.createChatCompletion(content, userId);
        log.warn("用户请求,用户:{},请求内容:{},返回内容:{}", userId, content, chatCompletion);
        return chatCompletion;
    }

    @Override
    public WeiXinMsgVO chat(WeiXinMsgDTO params) {
        String content = getMsgList(params);
        return new WeiXinMsgVO(System.currentTimeMillis(),
                params.getToUserName(), params.getFromUserName(),
                "text", content.toString());
    }

    @Override
    public String chat(HttpServletRequest request) {
        JSONObject jsonObject = CommonUtil.xmlToJson(request);
        WeiXinMsgDTO params = JSON.parseObject(jsonObject.toJSONString(), WeiXinMsgDTO.class);
        String content = "感谢大佬关注!";
        if (StringUtils.hasLength(params.getContent())) {
            content = getMsgList(params);
        }
        log.warn("公众号用户请求,用户:{},请求内容:{},返回内容:{}", params.getFromUserName(), params.getContent(), content);
        return getResult(jsonObject, content);
    }

    private String getMsgList(WeiXinMsgDTO params) {
        StringBuilder content = new StringBuilder();
        List<String> msgList = OpenAiUtils.createChatCompletion(params.getContent(), params.getFromUserName());
        for (int i = 0; i < msgList.size(); i++) {
            content.append(msgList.get(i));
            if (i < msgList.size() - 1) {
                content.append("\\n");
            }
        }
        return content.toString();
    }


    private String getResult(JSONObject jsonObject, String content) {
        return "<xml>" + "<ToUserName><![CDATA[" + jsonObject.getString("FromUserName") + "]]></ToUserName>"
                + "<FromUserName><![CDATA[" + jsonObject.getString("ToUserName") + "]]></FromUserName>" + "<CreateTime>"
                + System.currentTimeMillis() + "</CreateTime>" + "<MsgType><![CDATA[text]]></MsgType>"
                + "<Content><![CDATA[" + new String(content.getBytes(), ISO_8859_1) + "]]></Content></xml>";
    }


}
