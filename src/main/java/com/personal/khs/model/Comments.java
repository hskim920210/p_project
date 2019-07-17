package com.personal.khs.model;

import java.util.*;

public class Comments {
	private int comment_id;
	private int article_num;
	private String user_id;
	private String user_nick;
	private String content;
	private Date write_time;
	
	public Comments() {
	}

	public Comments(int comment_id, int article_num, String user_id, String user_nick, String content,
			Date write_time) {
		this.comment_id = comment_id;
		this.article_num = article_num;
		this.user_id = user_id;
		this.user_nick = user_nick;
		this.content = content;
		this.write_time = write_time;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getArticle_num() {
		return article_num;
	}

	public void setArticle_num(int article_num) {
		this.article_num = article_num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_nick() {
		return user_nick;
	}

	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWrite_time() {
		return write_time;
	}

	public void setWrite_time(Date write_time) {
		this.write_time = write_time;
	}
	
}
