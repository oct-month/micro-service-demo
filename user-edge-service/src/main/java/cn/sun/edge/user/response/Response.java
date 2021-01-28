package cn.sun.edge.user.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Response implements Serializable
{
    private int code;
    private String message;

    public static final Response USERNAME_PASSWORD_INVALID = new Response(1001, "username or password is error");
    public static final Response MOBILE_OR_EMAIL_REQUIRED = new Response(1002, "mobile or email is required");
    public static final Response SEND_VERIFY_CODE_FAILED = new Response(1003, "send verify code failed");
    public static final Response VERIFY_CODE_INVALID = new Response(1004, "verifyCode invalid");
    public static final Response SUCCESS = new Response();

    public Response()
    {
        this.code = 0;
        this.message = "success";
    }

    public Response(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public static Response exception(Exception e)
    {
        return new Response(9999, e.getMessage());
    }
}
