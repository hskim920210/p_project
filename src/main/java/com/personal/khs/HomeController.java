package com.personal.khs;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.personal.khs.service.*;
import com.personal.khs.model.*;
import java.util.*;

@Controller
public class HomeController {
	@Autowired
	private ServletContext app;
	
	@Autowired
	private Board_InfoListService b_ilService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		List<Board_Info> board_info_list = (List<Board_Info>)b_ilService.service();
		app.setAttribute("board_info_list", board_info_list);
		
		return "home";
	}
	
}
