package com.chz.gateway1.jwt;

public enum ResponseCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(412, "失败"),
    LOGIN_ERROR(202, "用户名或密码错误"),

    UNKNOWN_ERROR(500, "未知错误"),
    PARAMETER_ILLEGAL(400, "参数不合法"),

    TOKEN_INVALID(412, "token 已过期或验证不正确！"),
    TOKEN_SIGNATURE_INVALID(403, "无效的签名"),
    TOKEN_MISSION(403, "token 缺失"),
    REFRESH_TOKEN_INVALID(412, "refreshToken 无效"),
    LOGOUT_ERROR(444, "用户登出失败");

    private final int code;
    private final String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}