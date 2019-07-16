package com.personal.khs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.khs.model.*;
import com.personal.khs.repository.*;

@Service
public class Total_BoardSearchByArticle_NumService {
	@Autowired
	private Total_BoardDAO total_BoardDAO;
	
	public Object service(Object args) {
		Object result = null;
		try {
			result = total_BoardDAO.selectByArticle_Num((Total_Board)args);
		} catch (Exception e) {
			System.out.println("Total_BoardSearchByArticle_NumService 예외처리부분");
			e.printStackTrace();
		}
		return result;
	}
}
