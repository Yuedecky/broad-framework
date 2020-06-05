package com.broad.web.framework.dingtalk;

import com.broad.web.framework.dingtalk.request.DingTalkSendMessageBaseRequest;
import com.broad.web.framework.dingtalk.response.DingTalkBaseResponse;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 * @author broad
 * 钉钉基础封装
 */
@Data
public class DingTalkClient {


    private final String webHookUrl;


    private final RestTemplate restTemplate;


    public DingTalkClient(String webHookUrl, RestTemplate restTemplate) {
        this.webHookUrl = webHookUrl;
        this.restTemplate = restTemplate;
    }


    /**
     * 发送告警信息到钉钉群
     * @param sendMessageBaseRequest
     * @return
     */
    public DingTalkBaseResponse sendGroupMessage(DingTalkSendMessageBaseRequest sendMessageBaseRequest) {
        ResponseEntity<DingTalkBaseResponse> baseResponseResponseEntity = this.restTemplate.postForEntity(webHookUrl,
                sendMessageBaseRequest, DingTalkBaseResponse.class);
        return baseResponseResponseEntity.getBody();
    }


}
