package com.springboot.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

	
	@RequestMapping(value="/index", method=RequestMethod.GET)
    public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
        return model;
    }
}
