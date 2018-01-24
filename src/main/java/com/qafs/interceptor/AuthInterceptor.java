package com.qafs.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.qafs.dao.LogMapper;
import com.qafs.domain.Log;
import com.qafs.domain.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger.getLogger(AuthInterceptor.class);
	@Autowired
	Log requestLog;
	@Resource
	LogMapper logDao;

	/**
	 * 记录被访问的日志记录
	 * */
	private void logRecord(HttpServletRequest request) {
		requestLog.setUrl(request.getServletPath());
		requestLog.setRequestparameter(JSON.toJSONString(request
				.getParameterMap()));
		Object user = request.getAttribute("user");
		if (user != null) {
			requestLog.setUserid(((User)user).getId());
		}
		requestLog.setStatus(1);
		logDao.insertSelective(requestLog);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logRecord(request);
		return true;
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

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
}
