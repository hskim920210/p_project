package com.personal.khs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.khs.repository.*;
import com.personal.khs.model.*;

@Service
public class Board_InfoInsertService {
	@Autowired
	private Board_InfoDAO board_infoDAO;
	
	// 불린 타입이 리턴되는 서비스.
	// insert에 성공하면 true, 실패하면 false가 Object에 리턴된다.
	public Object service(Object args) {
		return board_infoDAO.insert((Board_Info)args) == 1 ? true : false;
	}
}
