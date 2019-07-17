package com.personal.khs.model;

import java.util.*;
import java.sql.*;
import java.text.*;

public class Like_Info {
	private String user_id;
	private int article_num;
	
	public Like_Info() {}

	public Like_Info(String user_id, int article_num) {
		this.user_id = user_id;
		this.article_num = article_num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getArticle_num() {
		return article_num;
	}

	public void setArticle_num(int article_num) {
		this.article_num = article_num;
	}
	
	
	
}
	
