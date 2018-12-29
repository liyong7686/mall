package com.liyong.model;

import lombok.Data;

@Data
public class TokenDTO {
    private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
    
}