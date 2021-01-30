package cn.sun.user.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserDTO implements Serializable
{
	private int id;
	private String username;
	private String password;
	private String realName;
	private String mobile;
	private String email;
}
