package cn.sun.course.api.service;

import cn.sun.course.api.dto.CourseDTO;

import java.util.List;

public interface ICourseService
{
	List<CourseDTO> courseList();
}
