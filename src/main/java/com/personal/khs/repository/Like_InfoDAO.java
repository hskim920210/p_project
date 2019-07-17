package com.personal.khs.repository;

import java.sql.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.personal.khs.model.*;;

@Repository
public class Like_InfoDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Like_InfoDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	class Like_InfoRowMapper implements RowMapper<Like_Info> {
		@Override
		public Like_Info mapRow(ResultSet rs, int rowNum) throws SQLException {
			Like_Info like_Info = new Like_Info();
			like_Info.setUser_id(rs.getString(1));
			like_Info.setArticle_num(rs.getInt(2));
			return like_Info;
		}
	}
	
	public int like_ok(Like_Info model) {
		String sql = "insert into like_info values (?,?)";
		return this.jdbcTemplate.update(sql, model.getUser_id(), model.getArticle_num());
	}
	
	
	public int like_cancle(Like_Info model) {
		String sql = "delete from like_info where user_id=? and article_num=?";
		return this.jdbcTemplate.update(sql, model.getUser_id(), model.getArticle_num());
	}
		
	// 좋아요를 이미 누른 사람이라면 1이 반환, 아니면 0이 반환
	public int like_check(Like_Info model) throws Exception {
		String sql = "select * from like_info where user_id=? and article_num=?";
		return this.jdbcTemplate.queryForObject(
				sql,
				new Like_InfoRowMapper(),
				model.getUser_id(),
				model.getArticle_num()) == null ? 0 : 1;
	}
}
