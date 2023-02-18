package com.kh.board.list;

import java.sql.Connection;
import java.util.List;

import com.kh.board.boardDAO.BoardDao;
import com.kh.board.boardDTO.BoardDto;
import com.kh.jdbc.JdbcConnect;

public class BoardListService {
	public BoardListService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<BoardDto> boardList(){
		Connection conn = JdbcConnect.getConnection();
		
		List<BoardDto> blist = new BoardDao().BorderList(conn);
		
		JdbcConnect.close(conn);

		return blist;
		
	}
	public List<BoardDto> BoardNotiList(){
		Connection conn = JdbcConnect.getConnection();
		
		List<BoardDto> blist = new BoardDao().BoardNotiList(conn);
		
		JdbcConnect.close(conn);
		
		return blist;
		
	}
	public List<BoardDto> AllNotiList(){
		Connection conn = JdbcConnect.getConnection();
		
		List<BoardDto> blist = new BoardDao().AllNotiList(conn);
		
		JdbcConnect.close(conn);
		
		return blist;
		
	}

}
