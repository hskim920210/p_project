package com.personal.khs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.khs.model.*;
import com.personal.khs.repository.*;

@Service
public class Total_CommentCountService {
	@Autowired
	private Total_CommentDAO total_CommentDAO;
	
	public Object service(Object args) {
		int result = 0;
		try {
			result = total_CommentDAO.commentCountByArticle_Num((Total_Comment)args);
		} catch (Exception e) {
			System.out.println("Total_CommentCountService 예외처리부분");
			e.printStackTrace();
		}
		return result;
	}
}
