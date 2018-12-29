package com.liyong.model;

import lombok.Data;


@Data
public class LoginDTO {
    // 用户信息原始数据
    private String rawData;

    // 用于验证用户信息是否被篡改过
    private String signature;

    // 用户获取 session_key 的 code
    private String code;

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    
}
