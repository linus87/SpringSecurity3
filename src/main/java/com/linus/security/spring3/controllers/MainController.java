package com.linus.security.spring3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.linus.security.spring3.Router;
import com.sun.istack.internal.logging.Logger;

@Controller
@RequestMapping(value="/hello")
public class MainController {
	Logger logger = Logger.getLogger(MainController.class);

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView handleRequest() {
 		ModelAndView model = new ModelAndView();
 		logger.info("Home Page");
 		model.setViewName(Router.home);
		return model;
	} 
}
