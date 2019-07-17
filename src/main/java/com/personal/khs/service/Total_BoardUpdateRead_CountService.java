package com.personal.khs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.khs.model.*;
import com.personal.khs.repository.*;

@Service
public class Total_BoardUpdateRead_CountService {
	@Autowired
	private Total_BoardDAO total_BoardDAO;
	
	public Object service(Object args) {
		Object result = null;
		try {
			result = total_BoardDAO.updateRead_Count((Total_Board)args);
		} catch (Exception e) {
			System.out.println("Total_BoardUpdateRead_CountService 예외처리부분");
			e.printStackTrace();
		}
		return result;
	}
}
