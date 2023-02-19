package com.kh.board.list;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.board.boardDAO.BoardDao;
import com.kh.board.boardDTO.BoardDto;
import com.kh.board.page.BoardPaging;
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
	
	public BoardPaging PagingList(int pNum, int cnt){
		Connection conn = JdbcConnect.getConnection();
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("start", (pNum - 1) * cnt + 1);
		page.put("end", pNum * cnt);
		
		List<BoardDto> blist = new BoardDao().PagingList(conn, page);

		int totalRowCount = new BoardDao().totalRow(conn);
		int mod = totalRowCount % cnt ==0? 0 : 1;
		int pageCount = totalRowCount/cnt +mod;
		
		BoardPaging paging = new BoardPaging(blist, pageCount, pNum,  cnt, 5);
		
		JdbcConnect.close(conn);
		
		return paging;
	}

}
