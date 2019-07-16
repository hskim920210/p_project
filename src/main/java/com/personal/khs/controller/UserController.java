package com.personal.khs.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.khs.model.*;
import com.personal.khs.service.*;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserIdCheckService uicService;
	@Autowired
	private UserNickCheckService unService;
	@Autowired
	private UserInsertService uiService;
	
	
	@GetMapping("/regist")
	public String registForm() {
		
		return "user/regist/registForm";
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public String idCheck(@RequestBody User user) {
		// uicService로 user객체가 반환되면 테이블에 해당 id인 user가 존재.
		// 반환된 user 객체가 null이면 가입이 가능
		if( uicService.service(user) != null ) {
			// 가입할 수 없는 ID
			return "{\"result\" : \"false\"}";
		} else {
			// 가입할 수 있는 ID
			return "{\"result\" : \"true\"}";
		}
		
	}
	
	@PostMapping("/nickCheck")
	@ResponseBody
	public String nickCheck(@RequestBody User user) {
		// uicService로 user객체가 반환되면 테이블에 해당 id인 user가 존재.
		// 반환된 user 객체가 null이면 가입이 가능
		if( unService.service(user) != null ) {
			// 가입할 수 없는 ID
			return "{\"result\" : \"false\"}";
		} else {
			// 가입할 수 있는 ID
			return "{\"result\" : \"true\"}";
		}
		
	}
	
	@PostMapping("/regist")
	public String registSubmit(@ModelAttribute("regResultUser") User user) {
		boolean result = (boolean)uiService.service(user);
		if( result ) {
			return "user/regist/registSubmit";
		} else {
			return "user/regist/registFail";
		}
	}
	
	@PostMapping("/loginCheck")
	@ResponseBody
	public String loginCheck(@RequestBody User user, HttpSession session, HttpServletResponse res,
			@CookieValue(value="idSave", required = false)Cookie idSaveCookie) {
		System.out.println(idSaveCookie == null);
		// idCheckService로부터 반환되는 유저타입이 null이면 아이디가 존재하지 않음
		User tableUser = (User)uicService.service(user);
		if( tableUser == null ) {
			return "{\"result\" : \"noId\"}";
		} else {
			if( tableUser.getUser_pw().trim().equals(user.getUser_pw().trim()) ) {
				session.setAttribute("loginUser", tableUser);
				Cookie cookie = new Cookie("idSave", tableUser.getUser_id());
				res.addCookie(cookie);
				// System.out.println(cookie.getValue());
				return "{\"result\" : \"ok\"}";
			} else {
				return "{\"result\" : \"noPw\"}";
			}
		}
	}
	
	@PostMapping("/login")
	public void login(HttpServletResponse res, HttpServletRequest req) {}
	
	@PostMapping("/logout")
	public void logout(HttpSession session, HttpServletResponse res, HttpServletRequest req) {
		session.invalidate();
		try {
			res.sendRedirect(req.getContextPath() + "/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
