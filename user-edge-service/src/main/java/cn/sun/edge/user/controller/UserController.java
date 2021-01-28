package cn.sun.edge.user.controller;

import cn.sun.edge.user.redis.RedisClient;
import cn.sun.edge.user.response.LoginResponse;
import cn.sun.edge.user.response.Response;
import cn.sun.edge.user.thrift.ServiceProvider;
import cn.sun.thrift.user.dto.UserDTO;
import cn.sun.thrift.user.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private ServiceProvider serviceProvider;
    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("username") String username,
                          @RequestParam("password") String password)
    {
        // 1、验证用户名密码
        UserInfo userInfo;
        try {
            userInfo = serviceProvider.getUserService().getUserByName(username);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USERNAME_PASSWORD_INVALID;
        }
        if (userInfo == null || !userInfo.getPassword().equalsIgnoreCase(md5(password)))
        {
            return Response.USERNAME_PASSWORD_INVALID;
        }
        // 2、生成token
        String token = getToken();
        // 3、缓存用户
        redisClient.set(token, toDTO(userInfo), 3600);
        return new LoginResponse(token);
    }

    @RequestMapping(value = "/send/VerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public Response sendVerifyCode(@RequestParam(value = "mobile", required = false) String mobile,
                                   @RequestParam(value = "email", required = false) String email)
    {
        String message = "Verify code is: ";
        String code = randomCode("0123456789", 6);
        message = message + code;
        boolean result = false;
        try {
            if (StringUtils.isNotBlank(mobile))
            {
                result = serviceProvider.getMessageService().sendMobileMessage(mobile, message);
                redisClient.set(mobile, code);
            }
            else if (StringUtils.isNotBlank(email))
            {
                result = serviceProvider.getMessageService().sendEmailMessage(email, message);
                redisClient.set(email, code);
            }
            else
            {
                return Response.MOBILE_OR_EMAIL_REQUIRED;
            }
        } catch (TException e) {
            e.printStackTrace();
            return Response.exception(e);
        }
        if (!result)
        {
            return Response.SEND_VERIFY_CODE_FAILED;
        }
        return Response.SUCCESS;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam(value = "mobile", required = false) String mobile,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam("verifyCode") String verifyCode)
    {
        if (StringUtils.isNotBlank(mobile))
        {
            String redisCode = redisClient.get(mobile);
            if (!verifyCode.equals(redisCode))
            {
                return Response.VERIFY_CODE_INVALID;
            }
        }
        else if (StringUtils.isNotBlank(email))
        {
            String redisCode = redisClient.get(email);
            if (!verifyCode.equals(redisCode))
            {
                return Response.VERIFY_CODE_INVALID;
            }
        }
        else
        {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(md5(password));
        userInfo.setMobile(mobile);
        userInfo.setEmail(email);
        try {
            serviceProvider.getUserService().registerUser(userInfo);
        } catch (TException e) {
            e.printStackTrace();
            return Response.exception(e);
        }
        return Response.SUCCESS;
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @ResponseBody
    public UserDTO authentication(@RequestHeader("token") String token)
    {
        return redisClient.get(token);
    }

    private UserDTO toDTO(UserInfo userInfo)
    {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);
        return userDTO;
    }

    private String getToken()
    {
        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
    }

    private String randomCode(String s, int size)
    {
        StringBuilder result = new StringBuilder(size);
        Random random = new Random();
        for (int i = 0; i < size; i++)
        {
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }

    private String md5(String password)
    {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(password.getBytes(StandardCharsets.UTF_8));
            return HexUtils.toHexString(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
