package com.kh.board.boardinfo;

import java.sql.Connection;
import java.util.List;

import com.kh.board.boardDAO.BoardDao;
import com.kh.board.boardDTO.BoardDto;
import com.kh.jdbc.JdbcConnect;

public class BoardViewService {

	public BoardDto BoardView(int id){
		Connection conn = JdbcConnect.getConnection();
		
		BoardDto bview = new BoardDao().selectView(conn, id);
		
		JdbcConnect.close(conn);
		
		return bview;
		
	}
	
	public int increaseView(String id) {
		int result = -1;
		
		Connection conn = JdbcConnect.getConnection();
		result = new BoardDao().increaseView(conn, Integer.parseInt(id));
		
		return result;
	}
}
