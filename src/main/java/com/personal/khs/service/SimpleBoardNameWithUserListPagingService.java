package com.personal.khs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.khs.repository.*;
import java.util.*;

@Service
public class SimpleBoardNameWithUserListPagingService {
	@Autowired
	private SimpleBoardNameWithUserDAO simpleBoardNameWithUserDAO;
	
	// 불린 타입이 리턴되는 서비스.
	// insert에 성공하면 true, 실패하면 false가 Object에 리턴된다.
	public Object service(Object args) {
		HashMap<String, Object> model = (HashMap<String, Object>)args;
		int board_id = (int)model.get("board_id");
		int page = (int)model.get("page");
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("totalCount", simpleBoardNameWithUserDAO.selectSimpleBoardCount(board_id));
		result.put("pagingList", simpleBoardNameWithUserDAO.selectSimpleBoardPaging(board_id, page));
		return result;
	}
}
