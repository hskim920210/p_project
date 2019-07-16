package com.personal.khs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.khs.model.*;
import com.personal.khs.service.*;
import java.util.*;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	Board_InfoInsertService b_iiService;
	@Autowired
	SimpleBoardWithUserListService sbwulService;
	
	@GetMapping("/make")
	public String boardMake(@ModelAttribute("board_info") Board_Info board_info) {
		return "board/make";
	}
	@PostMapping("/make")
	public String boardMakeResult(Board_Info board_info, HttpServletRequest req) {
		int board_name_length = board_info.getBoard_name().trim().length();
		boolean insertResult = false;
		if( board_name_length > 0 && board_name_length <= 8 ) {
			if( insertResult = (boolean)b_iiService.service(board_info) ) {
				return "board/makeSubmit";
			} else {
				req.setAttribute("resultMsg", "데이터 베이스에 테이블 정보를 insert 하는데 실패했습니다. 관리자에게 문의하세요.");
				return "board/makeFail";
			}
		} else {
			req.setAttribute("resultMsg", "게시판 제목의 글자수 조건에 만족하지 않습니다. (1~8자)");
			return "board/makeFail";
		}
	}
	
	@GetMapping("/write/{board_id}")
	public String articleWrite(@PathVariable(value="board_id")Integer board_id, Model model, HttpSession session) {
		model.addAttribute("board_id", board_id);
		model.addAttribute("loginUser", session.getAttribute("loginUser"));
		return "board/writeArticle";
	}
	
	@PostMapping("/writeArticle")
	public String articleWriteResult(Model model, HttpSession session) {
		
		return "board/writeArticle";
	}
	
	@GetMapping("/{board_id}")
	public String simpleBoardList(@PathVariable(value="board_id")Integer board_id, Model model) {
		SimpleBoardWithUser sb = new SimpleBoardWithUser();
		sb.setBoard_id(board_id);
		List<SimpleBoardWithUser> simpleList = (List<SimpleBoardWithUser>)sbwulService.service(sb);
		model.addAttribute("simpleList", simpleList);
		model.addAttribute("board_id", board_id);
		return "board/simpleBoardList";
	}
	
	
}
