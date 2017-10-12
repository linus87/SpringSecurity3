package com.linus.security.spring3.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.linus.security.spring3.Router;

@Controller
public class MainController {
	private static final Logger logger = Logger.getLogger(MainController.class.getName());

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView handleRequest() {
 		ModelAndView model = new ModelAndView();
 		logger.info("Home Page");
 		model.addObject("greeting", "hello");
 		model.setViewName(Router.home);
		return model;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView handleLoginRequest() {
 		ModelAndView model = new ModelAndView();
 		logger.info("Login Page");
 		model.addObject("greeting", "hello");
 		model.setViewName(Router.login);
		return model;
	}
}
