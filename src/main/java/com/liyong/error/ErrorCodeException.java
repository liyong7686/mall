package com.liyong.error;

import lombok.Getter;
import lombok.Setter;

public class ErrorCodeException extends RuntimeException implements IErrorCode {
    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String message;

    public ErrorCodeException(IErrorCode iErrorCode) {
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
    }

    @Override
    public String toString() {
        return "ErrorCodeException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
