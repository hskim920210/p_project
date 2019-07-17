package com.personal.khs.service;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.khs.model.*;
import com.personal.khs.repository.*;

@Service
public class Like_InfoInsertAndDeleteService {
	@Autowired
	private Like_InfoDAO like_InfoDAO;
	
	// like_result에 이미 좋아요를 누른 사람이면 true, 좋아요를 누르지 않은 사람이면 false가 넘어온다.
	// 이미 좋아요를 누른 사람(true) 면 like_cancle, 누르지 않은 사람(false) 면 like_ok
	// result에는 언제나 1이 반환된다.
	public Object service(Object args, boolean like_check) {
		Object result = null;
		if(like_check == true) {
			result = like_InfoDAO.like_cancle((Like_Info)args);
		} else {
			result = like_InfoDAO.like_ok((Like_Info)args);
		}
		return result;
	}
}







