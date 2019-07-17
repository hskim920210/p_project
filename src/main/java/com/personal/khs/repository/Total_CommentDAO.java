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
public class Total_CommentDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Total_CommentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	class Total_CommentRowMapper implements RowMapper<Total_Comment> {
		@Override
		public Total_Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Total_Comment total_Comment = new Total_Comment();
			total_Comment.setComment_id(rs.getInt(1));
			total_Comment.setArticle_num(rs.getInt(2));
			total_Comment.setUser_id(rs.getString(3));
			total_Comment.setContent(rs.getString(4));
			total_Comment.setWrite_time(rs.getDate(5));
			total_Comment.setComment_parent(rs.getInt(6));
			total_Comment.setComment_depth(rs.getInt(7));
			total_Comment.setComment_order(rs.getInt(8));
			return total_Comment;
		}
	}
	
	public int insertNew(Total_Comment model) {
		String sql = "insert into total_comment values (0,?,?,?,now(),null,1,1)";
		return this.jdbcTemplate.update(sql,
				model.getArticle_num(), model.getUser_id(), model.getContent()); 
	}
	
	public List<Total_Comment> selectByArticle_Num(Total_Comment model) throws Exception {
		String sql = "select * from total_comment where article_num = ?";
		return this.jdbcTemplate.query(sql,
				new Total_CommentRowMapper(), model.getArticle_num());
	}
	
	public int commentCountByArticle_Num(Total_Comment model) throws Exception {
		String sql = "select count(*) from total_comment where article_num=?";
		int totalCount = 0;
		totalCount = this.jdbcTemplate.queryForObject(sql, Integer.class, model.getArticle_num());
		return totalCount;
	}
	
	public int like_CountPlus(Like_Info model) {
		String sql = "update total_board set like_count = like_count + 1 where article_num = ?";
		return this.jdbcTemplate.update(sql, model.getArticle_num());
	}
	
	public int like_CountMinus(Like_Info model) {
		String sql = "update total_board set like_count = like_count - 1 where article_num = ?";
		return this.jdbcTemplate.update(sql, model.getArticle_num());
	}
	
	public int updateRead_Count(Total_Board model) {
		String sql = "update total_board set read_count=read_count+1 where article_num=?";
		return this.jdbcTemplate.update(sql, model.getArticle_num());
	}
	
}
