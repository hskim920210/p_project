package com.personal.khs.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.khs.model.*;
import com.personal.khs.repository.*;

@Service
public class Like_CountMinusService {
	@Autowired
	private Total_BoardDAO total_BoardDAO;
	
	public Object service(Object args) {
		return total_BoardDAO.like_CountMinus((Like_Info)args);
	}
}







