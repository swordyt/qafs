package com.qafs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "view/**")
	public ModelAndView view() {
		ModelAndView v;
		String uri=request.getServletPath();
		v=new ModelAndView(uri.substring(6));
		return v;
	}
}
