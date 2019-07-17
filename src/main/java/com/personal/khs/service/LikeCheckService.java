package com.personal.khs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.khs.model.*;
import com.personal.khs.repository.*;

@Service
public class LikeCheckService {
	@Autowired
	private Like_InfoDAO like_InfoDAO;
	
	// Like_Info가 넘어와서 테이블에 있는지 확인한다.
	// 테이블에 있으면 좋아요를 누른 사람으로 1이 반환
	// 테이블에 없으면 누르지 않은 사람으로 0이 반환
	public Object service(Object args) {
		Object result = null;
		try {
			result = like_InfoDAO.like_check((Like_Info)args);
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}
}







