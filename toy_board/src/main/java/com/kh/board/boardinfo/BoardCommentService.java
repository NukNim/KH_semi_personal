package com.kh.board.boardinfo;

import java.sql.Connection;
import java.util.List;

import com.kh.board.boardDAO.BoardDao;
import com.kh.board.boardDAO.CommentDAO;
import com.kh.board.boardDTO.BoardDto;
import com.kh.board.boardDTO.CommentDTO;
import com.kh.jdbc.JdbcConnect;

public class BoardCommentService {

	public int insertComment(CommentDTO dto) {
		int result = -1;
		Connection conn = JdbcConnect.getConnection();
		
		CommentDAO dao = new CommentDAO();
		result = dao.insertComment(conn, dto);
		
		return result;
	}
	
	
	public List<CommentDTO> selectComment(int id){
		
		Connection conn = JdbcConnect.getConnection();
		
		List<CommentDTO> clist = new CommentDAO().selectComment(conn, id);
		
		JdbcConnect.close(conn);

		return clist;
		
	}

}
