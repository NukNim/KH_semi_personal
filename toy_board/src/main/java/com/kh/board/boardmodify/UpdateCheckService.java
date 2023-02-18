package com.kh.board.boardmodify;

import java.sql.Connection;

import com.kh.board.boardDAO.BoardDao;
import com.kh.board.boardDTO.BoardDto;
import com.kh.jdbc.JdbcConnect;

public class UpdateCheckService {

	public int updateBoard(BoardDto dto) {
		Connection conn = JdbcConnect.getConnection();
		int result = -1;
		
		result = new BoardDao().updateBoard(conn, dto);
		
		JdbcConnect.close(conn);
		
		return result;
	}
}
