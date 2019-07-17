package com.personal.khs.model;

import java.util.*;

public class Total_Comment {
	private int comment_id;
	private int article_num;
	private String user_id;
	private String content;
	private Date write_time;
	private int comment_parent;
	private int comment_depth;
	private int comment_order;
	
	public Total_Comment() {
	}

	public Total_Comment(int comment_id, int article_num, String user_id, String content, Date write_time,
			int comment_parent, int comment_depth, int comment_order) {
		this.comment_id = comment_id;
		this.article_num = article_num;
		this.user_id = user_id;
		this.content = content;
		this.write_time = write_time;
		this.comment_parent = comment_parent;
		this.comment_depth = comment_depth;
		this.comment_order = comment_order;
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

	public int getComment_parent() {
		return comment_parent;
	}

	public void setComment_parent(int comment_parent) {
		this.comment_parent = comment_parent;
	}

	public int getComment_depth() {
		return comment_depth;
	}

	public void setComment_depth(int comment_depth) {
		this.comment_depth = comment_depth;
	}

	public int getComment_order() {
		return comment_order;
	}

	public void setComment_order(int comment_order) {
		this.comment_order = comment_order;
	}
}
