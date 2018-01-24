package com.qafs.interceptor;
import java.io.PrintWriter;

import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.qafs.common.Constant;
import com.qafs.dao.UserMapper;
import com.qafs.domain.User;
import com.qafs.util.JwtUtil;
import com.qafs.vm.Response;

public class TokenInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger.getLogger(TokenInterceptor.class);
	@Autowired
	JwtUtil jwt;
	@Autowired
	UserMapper userDao;
	String jwtString;
	@Autowired
	Response resp;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.info("======Token start======");
		boolean flag = true;
		Integer userId = null;
		User user = null;
		try {
			String token = request.getParameter("token");// 获取客服端token
			if (token == null) {// 如果为携带tokenId参数，将从cookie中获取
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("token")) {
							token = cookie.getValue();
						}
					}
				}

			}
			if (token == null) {
				flag = false;
			} else {
				Claims claims = jwt.parseJWT(token);
				Long expTime = Long.parseLong(claims.get("exp") + "") * 1000; // 获取token中保存过期时间
				userId = Integer.parseInt(jwt
						.parseSubject(jwt, token, "userId") + "");
				Long now = System.currentTimeMillis();
				user = userDao.selectByPrimaryKey(userId);
				if (user == null || (now - expTime) > 0) {
					flag = false;
				}
			}

			//
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		if (!flag) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			resp.setCode(Constant.CODE.NOLOGIN);
			resp.setMsg(Constant.MSG.NOLOGIN);
			out.append(JSONObject.toJSONString(resp));
			out.close();
		} else {
			request.setAttribute("user", user);
		}
		log.info("======token end======");
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
