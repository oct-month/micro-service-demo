package cn.sun.edge.user.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse extends Response
{
	public String token;

	public LoginResponse(String token)
	{
		this.token = token;
	}
}
