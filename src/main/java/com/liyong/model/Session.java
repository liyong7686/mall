package com.liyong.model;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class Session {
	private String openid;

    @JSONField(name = "session_key")
    private String sessionKey;

    
}
