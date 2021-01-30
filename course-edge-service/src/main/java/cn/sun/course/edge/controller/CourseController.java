package cn.sun.course.edge.controller;

import cn.sun.course.api.dto.CourseDTO;
import cn.sun.course.api.service.ICourseService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController
{
	@DubboReference
	private ICourseService courseService;

	@RequestMapping(value = "/courseList", method = RequestMethod.GET)
	@ResponseBody
	public List<CourseDTO> courseList(HttpServletRequest request)
	{
		return courseService.courseList();
	}
}
