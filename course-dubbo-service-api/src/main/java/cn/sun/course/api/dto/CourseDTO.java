package cn.sun.course.api.dto;

import cn.sun.user.api.dto.TeacherDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CourseDTO implements Serializable
{
	private int id;
	private String title;
	private String description;
	private TeacherDTO teacher;
}
