package cn.sun.user.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserDTO implements Serializable
{
	protected int id;
	protected String username;
	@JsonIgnore
	protected String password;
	protected String realName;
	protected String mobile;
	protected String email;

	@Override
	public String toString()
	{
		return "UserDTO{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", realName='" + realName + '\'' +
				", mobile='" + mobile + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
