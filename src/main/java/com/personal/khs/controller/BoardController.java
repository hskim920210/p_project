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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.personal.khs.model.*;
import com.personal.khs.service.*;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.*;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	Board_InfoInsertService b_iiService;
	@Autowired
	SimpleBoardNameWithUserListService sbnwulService;
	@Autowired
	Total_BoardInsertService t_biService;
	@Autowired
	Total_BoardSearchByArticle_NumService t_bsba_nService;
	@Autowired
	LikeCheckService lcService;
	@Autowired
	Like_InfoInsertAndDeleteService l_iiadService;
	@Autowired
	Like_CountPlusService l_cpService;
	@Autowired
	Like_CountMinusService l_cmService;
	@Autowired
	SimpleBoardNameWithUserListPagingService sbnwulpService;
	@Autowired
	Total_BoardUpdateRead_CountService t_bur_cService;
	@Autowired
	Total_CommentCountService t_ccService;
	
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
	public String articleWriteResult(Model model, HttpSession session, Total_Board t_b) {
		int result = (int)t_biService.service(t_b);
		if ( result == 1 ) {
			model.addAttribute("result", true);
			model.addAttribute("t_b", t_b);
		} else {
			model.addAttribute("result", false);
			model.addAttribute("t_b", t_b);
		}
		return "board/writeArticleSubmit";
	}
	
	@GetMapping("/{board_id}")
	public String simpleBoardList(@PathVariable(value="board_id")Integer board_id, Model model, HttpServletRequest req) {
		
		// 페이징
		int totalCount = 0;
		int page = 1;
		if( req.getParameter("page") != null ) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		// SimpleBoardNameWithUser sb = new SimpleBoardNameWithUser();
		// sb.setBoard_id(board_id);
		// List<SimpleBoardNameWithUser> simpleList = (List<SimpleBoardNameWithUser>)sbnwulService.service(sb);
		// model.addAttribute("simpleList", simpleList);
		// model.addAttribute("board_id", board_id);
		
		//
		HashMap<String, Object> values = new HashMap<>();
		values.put("board_id", board_id);
		values.put("page", page);
		HashMap<String, Object> resultPaging = (HashMap<String, Object>)sbnwulpService.service(values);
		totalCount = (int)resultPaging.get("totalCount");
		List<SimpleBoardNameWithUser> simpleList = (List<SimpleBoardNameWithUser>)resultPaging.get("pagingList");
		model.addAttribute("simpleList", simpleList);
		model.addAttribute("board_id", board_id);
		
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(totalCount);
		model.addAttribute("paging", paging);
		
		// 글번호를 키값으로 갖고 댓글 수를 밸류로 갖는 맵
		HashMap<Integer, Integer> article_NumAndCommentCount = new HashMap<Integer, Integer>();
		for(SimpleBoardNameWithUser sbwu : simpleList) {
			int article_num = sbwu.getArticle_num();
			Total_Comment t_c = new Total_Comment();
			t_c.setArticle_num(article_num);
			int commentCount = (int)t_ccService.service(t_c);
			article_NumAndCommentCount.put(article_num, commentCount);
		}
		model.addAttribute("article_NumAndCommentCount", article_NumAndCommentCount);
		
		return "board/simpleBoardList";
	}
	
	@GetMapping("/detailArticle/{article_num}")
	public String detailArticle(@PathVariable(value="article_num")Integer article_num, Model model, HttpSession session) {
		Total_Board t_b_source = new Total_Board();
		t_b_source.setArticle_num(article_num);
		Total_Board t_b = (Total_Board)t_bsba_nService.service(t_b_source);
		// 조회수 + 1
		t_bur_cService.service(t_b);
		Total_Board t_bUpdated = (Total_Board)t_bsba_nService.service(t_b_source);
		model.addAttribute("detailArticle", t_bUpdated);
		
		
		// 좋아요가 체크되어있는지 확인
		User loginUser = (User)session.getAttribute("loginUser");
		Like_Info l_i = new Like_Info(loginUser.getUser_id(), article_num);
		int like_checkInt = (int)lcService.service(l_i);
		if(like_checkInt == 1) {
			model.addAttribute("like_check", true);
		} else {
			model.addAttribute("like_check", false);
		}
		
		// 댓글 수
		Total_Comment t_c = new Total_Comment();
		t_c.setArticle_num(article_num);
		int commentCount = (int)t_ccService.service(t_c);
		model.addAttribute("commentCount", commentCount);
		return "board/detailArticle";
	}
	
	@PostMapping("/like")
	@ResponseBody
	public boolean like(@RequestBody String infos) {
		// ajax로 가져온 json을 파싱하여 article_num. user_id, like_count를 가져옴
		JsonParser jsonParser = new JsonParser();
		JsonObject obj = (JsonObject) jsonParser.parse(infos);
		int article_num = obj.get("article_num").getAsInt();
		String user_id = obj.get("user_id").getAsString();
		int like_count = obj.get("like_count").getAsInt();
		
		Like_Info l_i = new Like_Info(user_id, article_num);
		boolean like_check = false;
		int like_checkInt = (int)lcService.service(l_i);
		if(like_checkInt == 1) {
			System.out.println("좋아요 취소 로직 부분");
			like_check = true;
			// 좋아요 취소 및 -1 로직
			l_iiadService.service(l_i, like_check);
			l_cmService.service(l_i);
			return false;
		} else {
			System.out.println("좋아요 추가 로직 부분");
			like_check = false;
			// 좋아요 및 +1 로직
			l_iiadService.service(l_i, like_check);
			l_cpService.service(l_i);
			return true;
		}
	}
	
	@PostMapping("/comment")
	public void comment() {
		
	}
}





/*

String user_id = request.getParameter("user_id");
		String strArticle_num = request.getParameter("article_num");
		Integer article_num = null;
		article_num = Integer.parseInt(strArticle_num);
		
		boolean like_check = false;
		Boolean like_result = null;
		
		// ajax로 보낼 result. 좋아요 됐으면 true, 좋아요 취소면 false가 전달되게 만든다.
		Boolean result = null;
		
		try (Connection conn = ConnectionProvider.getConnection()) {

			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("user_id", user_id);
			values.put("article_num", article_num);
			
			HashMap<String, Object> resultMap = lcService.service(values);
			like_check = (boolean)resultMap.get("like_check");
			values.put("like_check", like_check);
			
			resultMap = liService.service(values);
			
			// like result는 좋아요 결과가 true, 좋아요 취소 결과가 false이다.
			like_result = (Boolean)resultMap.get("like_result");
			
			// 좋아요가 true 인 경우 totla_board에서 likecount를 1 올린다.
			if(like_result == true) {
				values.put("like_count_p", like_result);
				values.put("like_count_m", !like_result);
				lctService.service(values);
			} else if (like_result == false) {
				values.put("like_count_p", like_result);
				values.put("like_count_m", !like_result);
				lctService.service(values);
			}
			
			result = like_result;
			request.setAttribute("like_result", like_result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/plane;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


 
*/



