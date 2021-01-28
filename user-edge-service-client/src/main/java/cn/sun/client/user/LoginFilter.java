package cn.sun.client.user;

import cn.sun.thrift.user.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class LoginFilter implements Filter
{
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String token = request.getParameter("token");
		if (StringUtils.isBlank(token))
		{
			Cookie[] cookies = request.getCookies();
			if (cookies != null)
			{
				for (Cookie c : cookies)
				{
					if (c.getName().equals("token"))
					{
						token = c.getValue();
					}
				}
			}
		}
		UserDTO userInfo = null;
		if (StringUtils.isNotBlank(token))
		{
			userInfo = requestUserInfo(token);
		}
		if (userInfo == null)
		{
			response.sendRedirect("http://127.0.0.1:/4041/user/login");
			return;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{

	}

	@Override
	public void destroy()
	{

	}

	private UserDTO requestUserInfo(String token)
	{
		String url = "http://localhost:4041/user/authentication";
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.addHeader("token", token);
		InputStream inputStream = null;
		try {
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			{
				throw new RuntimeException("request user info failed! StatusLine: " + response.getStatusLine());
			}
			inputStream = response.getEntity().getContent();
			byte[] temp = new byte[1024];
			StringBuilder sb = new StringBuilder();
			int len = 0;
			while ((len = inputStream.read(temp)) > 0)
			{
				sb.append(new String(temp, 0, len));
			}
			UserDTO userDTO = new ObjectMapper().readValue(sb.toString(), UserDTO.class);
			return userDTO;
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (inputStream != null)
			{
				try {
					inputStream.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
