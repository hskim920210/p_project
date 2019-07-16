package com.personal.khs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.khs.model.*;
import com.personal.khs.repository.*;

@Service
public class Total_BoardInsertService {
	@Autowired
	private Total_BoardDAO total_BoardDAO;
	
	public Object service(Object args) {
		Object result = null;
		result = total_BoardDAO.insert((Total_Board)args);
		return result;
	}
}
