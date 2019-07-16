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
public class Board_InfoDAO {
	private JdbcTemplate jdbcTemplate;
	
	// root-context.xml에 정의해놓은 스프링 빈 객체인 dataSource 주입
	// DataSource 는 org.apache.tomcat.jdbc.poo.DataSource 임에 주의
	@Autowired
	public Board_InfoDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// 쿼리 수행 결과 resultSet의 속성들을 기반으로 객체를 만들어 저장해주는 RowMapper 인터페이스.
	// 반드시 mapRow 메소드를 오버라이딩 한다.
	// org.springframework.jdbc.core.RowMapper 임에 주의
	class Board_InfoRowMapper implements RowMapper<Board_Info> {
		@Override
		public Board_Info mapRow(ResultSet rs, int rowNum) throws SQLException {
			Board_Info board_info = new Board_Info();
			board_info.setBoard_id(rs.getInt(1));
			board_info.setBoard_name(rs.getString(2));
			return board_info;
		}
	}
	
	// board_id로 검색하여 결과를 Board_Info로 리턴하는 메소드
	// 결과는 1개만 나올 것이므로 queryForObject를 사용한다.
	public Board_Info selectByBoard_Id(Board_Info model) throws Exception {
		String sql = "select * from board_info where board_id = ?";
		return this.jdbcTemplate.queryForObject(sql,
				new Board_InfoRowMapper(), model.getBoard_id());
	}
	
	// board_name로 검색하여 결과를 Board_Info로 리턴하는 메소드
	// 결과는 1개만 나올 것이므로 queryForObject를 사용한다.
	public Board_Info selectByBoard_Name(Board_Info model) throws Exception {
		String sql = "select * from board_info where board_name = ?";
		return this.jdbcTemplate.queryForObject(sql,
				new Board_InfoRowMapper(), model.getBoard_name());
	}
	
	// 게시판을 만드는 insert 메소드
	// 성공하면 1을 리턴하고 실패하면 0을 리턴한다.
	public int insert(Board_Info model) {
		String sql = "insert into board_info values (0,?)";
		return this.jdbcTemplate.update(sql, model.getBoard_name());
	}
	
	
	// 모든 게시판의 이름과 id를 가져오는 selectAll 메소드
	public List<Board_Info> selectAll() {
		String sql = "select * from board_info";
		return this.jdbcTemplate.query(sql, new Board_InfoRowMapper());
	}
}
