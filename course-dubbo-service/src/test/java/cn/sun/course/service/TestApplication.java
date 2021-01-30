package cn.sun.course.service;

import static org.junit.jupiter.api.Assertions.*;

import cn.sun.course.service.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class TestApplication
{
	@Autowired
	private ApplicationContext context;
	@Autowired
	private CourseMapper mapper;

	@Test
	public void testTeacherId()
	{
		Integer id = mapper.getCourseTeacherId(0);
		assertEquals(id, null);
	}
}
