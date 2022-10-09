package com.study.common.bean;

/**
 * 常用状态码及返回信息
 */
public enum ResultCode {

	SUCCESS(200, "操作成功"),
	FAILED(500, "操作失败"),
	UNAUTHORIZED(401, "暂未登录或token已经过期"),
	VALIDATE_FAILED(402, "参数检验失败"),
	FORBIDDEN(403, "没有相关权限");

	/** 状态码 */
	private int code;

	/** 提示信息 */
	private String message;

	private ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
