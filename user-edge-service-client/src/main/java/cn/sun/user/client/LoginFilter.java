package cn.sun.user.client;

import cn.sun.user.api.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
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
import java.util.concurrent.TimeUnit;

public abstract class LoginFilter implements Filter
{
	private static Cache<String, UserDTO> cache = CacheBuilder.newBuilder()
			.maximumSize(10000)
			.expireAfterWrite(3, TimeUnit.MINUTES)
			.build();

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
			userInfo = cache.getIfPresent(token);
			if (userInfo == null)
			{
				userInfo = requestUserInfo(token);
				if (userInfo != null)
				{
					cache.put(token, userInfo);
				}
			}
		}
		if (userInfo == null)
		{
			response.sendRedirect("http://local.oct-month.top/user/login");
			return;
		}
		login(request, response, userInfo);
		filterChain.doFilter(request, response);
	}

	protected abstract void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO);

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
		String url = "http://user-edge-service:4041/user/authentication";
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
