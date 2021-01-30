package cn.sun.course.service.service;

import cn.sun.course.api.dto.CourseDTO;
import cn.sun.course.api.service.ICourseService;
import cn.sun.course.service.mapper.CourseMapper;
import cn.sun.course.service.thrift.ServiceProvider;
import cn.sun.user.api.UserInfo;
import cn.sun.user.api.dto.TeacherDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "1.0.0")
public class CourseServiceImpl implements ICourseService
{
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private ServiceProvider serviceProvider;

	@Override
	public List<CourseDTO> courseList()
	{
		List<CourseDTO> courseDTOS = courseMapper.listCourse();
		if (courseDTOS != null)
		{
			for (CourseDTO courseDTO : courseDTOS)
			{
				Integer teacherId = courseMapper.getCourseTeacherId(courseDTO.getId());
				if (teacherId != null)
				{
					try {
						UserInfo userInfo = serviceProvider.getUserService().getTeacherById(teacherId);
						courseDTO.setTeacher(transTeacherDTO(userInfo));
					}
					catch (TException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return courseDTOS;
	}

	private TeacherDTO transTeacherDTO(UserInfo userInfo)
	{
		TeacherDTO teacherDTO = new TeacherDTO();
		BeanUtils.copyProperties(userInfo, teacherDTO);
		return teacherDTO;
	}
}
