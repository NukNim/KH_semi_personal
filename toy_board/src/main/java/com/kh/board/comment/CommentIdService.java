package com.kh.board.comment;

import java.sql.Connection;
import java.sql.JDBCType;

import com.kh.board.boardDAO.CommentDAO;
import com.kh.board.boardDTO.CommentDTO;
import com.kh.jdbc.JdbcConnect;

public class CommentIdService {
	
	public int searchId(CommentDTO dto) {
		Connection conn = JdbcConnect.getConnection();
		CommentDAO dao = new CommentDAO();
		int result = -1;
		
		result = dao.searchId(conn, dto);
		
		JdbcConnect.close(conn);
		
		return result;
	}
	
	public int updateComment(CommentDTO dto) {
		Connection conn = JdbcConnect.getConnection();
		CommentDAO dao = new CommentDAO();
		int result = -1;
		
		result = dao.updateComment(conn, dto);
		
		JdbcConnect.close(conn);
		
		return result;
	}

	public int deleteComment(CommentDTO dto) {
		Connection conn = JdbcConnect.getConnection();
		CommentDAO dao = new CommentDAO();
		int result = -1;
		
		result = dao.deleteComment(conn, dto);
		
		JdbcConnect.close(conn);
		
		return result;
	}

}
