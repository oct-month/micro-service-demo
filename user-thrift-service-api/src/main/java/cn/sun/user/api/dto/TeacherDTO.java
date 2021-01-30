package cn.sun.user.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO extends UserDTO
{
	private String introduction;
	private String description;
}
