package com.personal.khs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.personal.khs.model.*;
import com.personal.khs.repository.Board_InfoDAO.Board_InfoRowMapper;

@Repository
public class Total_BoardDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Total_BoardDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	class Total_BoardRowMapper implements RowMapper<Total_Board> {
		@Override
		public Total_Board mapRow(ResultSet rs, int rowNum) throws SQLException {
			Total_Board total_Board = new Total_Board();
			total_Board.setArticle_num(rs.getInt(1));
			total_Board.setBoard_id(rs.getInt(2));
			total_Board.setWriter_id(rs.getString(3));
			total_Board.setWriter_nick(rs.getString(4));
			total_Board.setArticle_title(rs.getString(5));
			total_Board.setArticle_content(rs.getString(6));
			total_Board.setWrite_date(rs.getDate(7));
			total_Board.setRead_count(rs.getInt(8));
			total_Board.setLike_count(rs.getInt(9));
			total_Board.setDel_pw(rs.getInt(10));
			return total_Board;
		}
	}
	
	public int insert(Total_Board model) {
		String sql = "insert into total_board values (0,?,?,?,?,?,now(),0,0,?)";
		return this.jdbcTemplate.update(sql,
				model.getBoard_id(), model.getWriter_id(), model.getWriter_nick(),
				model.getArticle_title(), model.getArticle_content(), null); 
	}
	
	public Total_Board selectByArticle_Num(Total_Board model) throws Exception {
		String sql = "select * from total_board where article_num = ?";
		return this.jdbcTemplate.queryForObject(sql,
				new Total_BoardRowMapper(), model.getArticle_num());
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
