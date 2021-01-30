package cn.sun.user.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO extends UserDTO
{
	private String introduction;
	private int stars;

	@Override
	public String toString()
	{
		return "TeacherDTO{" +
				"introduction='" + introduction + '\'' +
				", stars='" + stars + '\'' +
				", id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", realName='" + realName + '\'' +
				", mobile='" + mobile + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
