package com.personal.khs.model;

import java.util.*;

public class SimpleBoardWithUser {
	private int board_id;
	private int article_num;
	private String writer_id;
	private String writer_nick;
	private String article_title;
	private String article_content;
	private Date write_date;
	private int read_count;
	private int like_count;
	private int del_pw;
	
	public SimpleBoardWithUser() {
	}

	public SimpleBoardWithUser(int board_id, int article_num, String writer_id, String writer_nick,
			String article_title, String article_content, Date write_date, int read_count, int like_count, int del_pw) {
		this.board_id = board_id;
		this.article_num = article_num;
		this.writer_id = writer_id;
		this.writer_nick = writer_nick;
		this.article_title = article_title;
		this.article_content = article_content;
		this.write_date = write_date;
		this.read_count = read_count;
		this.like_count = like_count;
		this.del_pw = del_pw;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getArticle_num() {
		return article_num;
	}

	public void setArticle_num(int article_num) {
		this.article_num = article_num;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getWriter_nick() {
		return writer_nick;
	}

	public void setWriter_nick(String writer_nick) {
		this.writer_nick = writer_nick;
	}

	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public int getDel_pw() {
		return del_pw;
	}

	public void setDel_pw(int del_pw) {
		this.del_pw = del_pw;
	}
	
}
