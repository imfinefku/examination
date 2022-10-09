package com.study.common.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * 返回对象实体
 */
public class CommonResult {

	/** 状态码 */
	private int code;

	/** 提示信息 */
	private String message;

	/** 返回数据 */
	private Object data;

	/** 数据总量 */
	private int count;

	private CommonResult(int code, String message, Object data, int count) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.count = count;
	}

	private CommonResult(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 成功
	 * 
	 * @return
	 */
	public static CommonResult success() {
		return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
	}

	/**
	 * 成功
	 * 
	 * @param data
	 * @return
	 */
	public static CommonResult success(Object data) {
		return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
	}

	/**
	 * 成功
	 * 
	 * @param data
	 * @return
	 */
	public static CommonResult success(Object data, int count) {
		return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, count);
	}

	/**
	 * 失败
	 * 
	 * @return
	 */
	public static CommonResult failed() {
		return new CommonResult(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), null);
	}

	/**
	 * 失败
	 * 
	 * @return
	 */
	public static CommonResult failed(String message) {
		return new CommonResult(ResultCode.FAILED.getCode(), message, null);
	}

	/**
	 * 参数验证失败返回结果
	 */
	public static CommonResult validateFailed() {
		return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(), null);
	}

	/**
	 * 参数验证失败返回结果
	 * 
	 * @param message 提示信息
	 */
	public static CommonResult validateFailed(String message) {
		return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(), message, null);
	}

	/**
	 * 未登录返回结果
	 */
	public static CommonResult unauthorized(Object data) {
		return new CommonResult(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
	}

	/**
	 * 未授权返回结果
	 */
	public static CommonResult forbidden(Object data) {
		return new CommonResult(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
	}

	/**
	 * 参数校验失败
	 * 
	 * @param bindingResult
	 * @return
	 */
	public static CommonResult checkError(BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		List<String> errorList = new ArrayList<String>();
		for (FieldError fe : fieldErrors) {
			errorList.add(fe.getDefaultMessage());
		}
		return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(), "参数校验失败", errorList);
	}

	@Override
	public String toString() {
		return "CommonResult{" +
				"code=" + code +
				", message='" + message + '\'' +
				", data=" + data +
				", count=" + count +
				'}';
	}
}
