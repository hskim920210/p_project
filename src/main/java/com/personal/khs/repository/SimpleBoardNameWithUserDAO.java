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
public class SimpleBoardNameWithUserDAO {
	private JdbcTemplate jdbcTemplate;
	
	// root-context.xml에 정의해놓은 스프링 빈 객체인 dataSource 주입
	// DataSource 는 org.apache.tomcat.jdbc.poo.DataSource 임에 주의
	@Autowired
	public SimpleBoardNameWithUserDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// 쿼리 수행 결과 resultSet의 속성들을 기반으로 객체를 만들어 저장해주는 RowMapper 인터페이스.
	// 반드시 mapRow 메소드를 오버라이딩 한다.
	// org.springframework.jdbc.core.RowMapper 임에 주의
	class SimpleBoardNameWithUserRowMapper implements RowMapper<SimpleBoardNameWithUser> {
		@Override
		public SimpleBoardNameWithUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			SimpleBoardNameWithUser simpleBoardNameWithUser = new SimpleBoardNameWithUser();
			simpleBoardNameWithUser.setBoard_id(rs.getInt(1));
			simpleBoardNameWithUser.setBoard_name(rs.getString(2));
			simpleBoardNameWithUser.setArticle_num(rs.getInt(3));
			simpleBoardNameWithUser.setWriter_id(rs.getString(4));
			simpleBoardNameWithUser.setWriter_nick(rs.getString(5));
			simpleBoardNameWithUser.setArticle_title(rs.getString(6));
			simpleBoardNameWithUser.setWrite_date(rs.getDate(7));
			simpleBoardNameWithUser.setRead_count(rs.getInt(8));
			simpleBoardNameWithUser.setLike_count(rs.getInt(9));
			return simpleBoardNameWithUser;
		}
	}
	
	// board_id로 검색하여 해당 게시판의 글을 모두 가져오는 메소드
	public List<SimpleBoardNameWithUser> selectALLByBoard_Id(SimpleBoardNameWithUser model) throws Exception {
		String sql = "select * from simpleBoardNameWithUser where board_id = ?";
		return this.jdbcTemplate.query(sql, new SimpleBoardNameWithUserRowMapper(), model.getBoard_id());
	}
	
	public List<SimpleBoardNameWithUser> selectSimpleBoardPaging(int board_id, int page) {
		String sql = "select s.* from (select * from simpleBoardNameWithUser order by article_num desc) s where board_id = ? limit ?, 5";
		int startNum = (page-1)*5;
		List<SimpleBoardNameWithUser> result = this.jdbcTemplate.query(sql,
				new SimpleBoardNameWithUserRowMapper(), board_id, startNum);
		
		return result;
	}
	
	public int selectSimpleBoardCount(int board_id) {
		int result = 0;
		String sql = "select count(*) from simpleBoardNameWithUser where board_id=?";
		result = this.jdbcTemplate.queryForObject(sql, Integer.class, board_id);
		return result;
	}
	
}
