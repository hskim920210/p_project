package com.personal.khs.repository;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

import com.personal.khs.model.*;

@Repository
public class SimpleBoardWithUserDAO {
	private JdbcTemplate jdbcTemplate;
	
	// root-context.xml에 정의해놓은 스프링 빈 객체인 dataSource 주입
	// DataSource 는 org.apache.tomcat.jdbc.poo.DataSource 임에 주의
	@Autowired
	public SimpleBoardWithUserDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// 쿼리 수행 결과 resultSet의 속성들을 기반으로 객체를 만들어 저장해주는 RowMapper 인터페이스.
	// 반드시 mapRow 메소드를 오버라이딩 한다.
	// org.springframework.jdbc.core.RowMapper 임에 주의
	class SimpleBoardWithUserRowMapper implements RowMapper<SimpleBoardWithUser> {
		@Override
		public SimpleBoardWithUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			SimpleBoardWithUser simpleBoardWithUser = new SimpleBoardWithUser();
			simpleBoardWithUser.setBoard_id(rs.getInt(1));
			simpleBoardWithUser.setArticle_num(rs.getInt(2));
			simpleBoardWithUser.setWriter_id(rs.getString(3));
			simpleBoardWithUser.setWriter_nick(rs.getString(4));
			simpleBoardWithUser.setArticle_title(rs.getString(5));
			simpleBoardWithUser.setWrite_date(rs.getDate(6));
			simpleBoardWithUser.setRead_count(rs.getInt(7));
			simpleBoardWithUser.setLike_count(rs.getInt(8));
			return simpleBoardWithUser;
		}
	}
	
	// board_id로 검색하여 해당 게시판의 글을 모두 가져오는 메소드
	public List<SimpleBoardWithUser> selectALLByBoard_Id(SimpleBoardWithUser model) throws Exception {
		String sql = "select * from board_info where board_id = ?";
		return this.jdbcTemplate.query(sql, new SimpleBoardWithUserRowMapper(), model.getBoard_id());
	}
	
}
