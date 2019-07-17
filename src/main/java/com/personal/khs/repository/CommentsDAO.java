package com.personal.khs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.personal.khs.model.*;
import com.personal.khs.repository.*;
import java.util.*;

@Repository
public class CommentsDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CommentsDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	class CommentsRowMapper implements RowMapper<Comments> {
		@Override
		public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
			Comments comments = new Comments();
			comments.setComment_id(rs.getInt(1));
			comments.setArticle_num(rs.getInt(2));
			comments.setUser_id(rs.getString(3));
			comments.setUser_nick(rs.getString(4));
			comments.setContent(rs.getString(5));
			comments.setWrite_time(rs.getDate(6));
			return comments;
		}
	}
	
	
	public List<Comments> selectByArticle_Num(Comments model) throws Exception {
		String sql = "select * from total_comment where article_num = ?";
		return this.jdbcTemplate.query(sql,
				new CommentsRowMapper(), model.getArticle_num());
	}
	
	
}
