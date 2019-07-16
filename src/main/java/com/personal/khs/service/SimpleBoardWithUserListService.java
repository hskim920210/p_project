package com.personal.khs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.khs.repository.*;
import com.personal.khs.model.*;

@Service
public class SimpleBoardWithUserListService {
	@Autowired
	private SimpleBoardWithUserDAO simpleBoardWithUserDAO;
	
	// 불린 타입이 리턴되는 서비스.
	// insert에 성공하면 true, 실패하면 false가 Object에 리턴된다.
	public Object service(Object args) {
		Object result = null;
		try {
			result = simpleBoardWithUserDAO.selectALLByBoard_Id((SimpleBoardWithUser)args);
		} catch (Exception e) {
			result = null;
			System.out.println("SimpleBoardWithUserListService 예외처리 부분");
			e.printStackTrace();
		}
		return result;
	}
}
