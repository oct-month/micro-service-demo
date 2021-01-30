package cn.sun.course.edge.filter;

import cn.sun.user.api.dto.UserDTO;
import cn.sun.user.client.LoginFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseFilter extends LoginFilter
{
	@Override
	protected void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO)
	{
		request.setAttribute("user", userDTO);
	}
}
