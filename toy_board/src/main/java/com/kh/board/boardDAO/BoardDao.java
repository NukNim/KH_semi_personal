package com.kh.board.boardDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.board.boardDTO.BoardDto;
import com.kh.board.boardDTO.CategoryDto;
import com.kh.jdbc.JdbcConnect;

public class BoardDao {
	
	public BoardDao() {
		// TODO Auto-generated constructor stub
	}
	//전체 게시판 리스트 
	public List<BoardDto> BorderList(Connection conn){
		List<BoardDto> blist = new ArrayList<BoardDto>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String query = "SELECT ID, TITLE ,CONTENT, USER_ID, USER_PW ,CREATE_DATE ,MODI_DATE ,DEL_FLAG ,CATEGORY_NAME, VIEW_CNT "
				+ "FROM TOY_BOARD b join CATEGORY c on b.CATEGORY =c.CATEGORY_ID"
				+ " WHERE c.CATEGORY_ID != (60)"
				+ " AND del_flag ='N'"
				+ " ORDER BY ID DESC";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					BoardDto b = new BoardDto();
					b.setId(rs.getInt("id"));
					b.setTitle(rs.getString("title"));
					b.setContent(rs.getString("content"));
					b.setUserId(rs.getString("user_id"));
					b.setUserPw(rs.getString("user_pw"));
					b.setDelFlag(rs.getString("del_flag"));
					b.setCreateDate(rs.getDate("create_date"));
					b.setModifyDate(rs.getDate("modi_date"));
					b.setCategoryName(rs.getNString("category_name"));
					b.setViewCnt(rs.getInt("VIEW_CNT"));
					
					blist.add(b);

				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcConnect.close(rs);
			JdbcConnect.close(pstmt);	
		}

		return blist;
	}
	
	//공지 리스트
	public List<BoardDto> BoardNotiList(Connection conn){
		List<BoardDto> bnlist = new ArrayList<BoardDto>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String query = "SELECT ID, TITLE ,CONTENT, USER_ID, USER_PW ,CREATE_DATE ,MODI_DATE ,DEL_FLAG ,CATEGORY_NAME, VIEW_CNT "
				+ "FROM TOY_BOARD b join CATEGORY c on b.CATEGORY =c.CATEGORY_ID"
				+ " WHERE c.CATEGORY_ID = 50"
				+ " AND del_flag ='N'"
				+ " ORDER BY ID DESC";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					BoardDto b = new BoardDto();
					b.setId(rs.getInt("id"));
					b.setTitle(rs.getString("title"));
					b.setContent(rs.getString("content"));
					b.setUserId(rs.getString("user_id"));
					b.setUserPw(rs.getString("user_pw"));
					b.setDelFlag(rs.getString("del_flag"));
					b.setCreateDate(rs.getDate("create_date"));
					b.setModifyDate(rs.getDate("modi_date"));
					b.setCategoryName(rs.getNString("category_name"));
					b.setViewCnt(rs.getInt("VIEW_CNT"));
					
					bnlist.add(b);

				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcConnect.close(rs);
			JdbcConnect.close(pstmt);	
		}

		return bnlist;
	}
	//전체 공지 리스트
	public List<BoardDto> AllNotiList(Connection conn){
		List<BoardDto> anlist = new ArrayList<BoardDto>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String query = "SELECT ID, TITLE ,CONTENT, USER_ID, USER_PW ,CREATE_DATE ,MODI_DATE ,DEL_FLAG ,CATEGORY_NAME, VIEW_CNT FROM TOY_BOARD b join CATEGORY c on b.CATEGORY =c.CATEGORY_ID where c.CATEGORY_ID = 60 and del_flag ='N' ORDER BY ID DESC";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					BoardDto b = new BoardDto();
					b.setId(rs.getInt("id"));
					b.setTitle(rs.getString("title"));
					b.setContent(rs.getString("content"));
					b.setUserId(rs.getString("user_id"));
					b.setUserPw(rs.getString("user_pw"));
					b.setDelFlag(rs.getString("del_flag"));
					b.setCreateDate(rs.getDate("create_date"));
					b.setModifyDate(rs.getDate("modi_date"));
					b.setCategoryName(rs.getNString("category_name"));
					b.setViewCnt(rs.getInt("VIEW_CNT"));
					
					anlist.add(b);
					
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcConnect.close(rs);
			JdbcConnect.close(pstmt);	
		}
		
		return anlist;
	}
	
	//게시판 상세 
	public BoardDto selectView(Connection conn, int id) {
		BoardDto result = new BoardDto();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String query = "SELECT ID, TITLE ,CONTENT, USER_ID, USER_PW ,CREATE_DATE ,MODI_DATE ,DEL_FLAG ,CATEGORY_ID,CATEGORY_NAME \r\n"
				+ "FROM TOY_BOARD b join CATEGORY c on b.CATEGORY =c.CATEGORY_ID\r\n"
				+ " WHERE b.ID  = ?\r\n"
				+ " AND del_flag ='N'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result.setId(rs.getInt("id"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
				result.setUserId(rs.getString("user_id"));
				result.setUserPw(rs.getString("user_pw"));
				result.setDelFlag(rs.getString("del_flag"));
				result.setCreateDate(rs.getDate("create_date"));
				result.setModifyDate(rs.getDate("modi_date"));
				result.setCategoryName(rs.getNString("category_name"));
				result.setCategoryId(rs.getInt("category_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcConnect.close(rs);
			JdbcConnect.close(pstmt);	
		}
		
		
		return result;
	}
	
	//게시글 작성 
	public int insertBoard(Connection conn, BoardDto dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO TOY_BOARD (ID, TITLE, CONTENT, USER_ID, USER_PW, CREATE_DATE, DEL_FLAG, CATEGORY)";
				query+= " VALUES (BOARD_SEQ.NEXTVAL, ?, ?,? , ?, sysdate, 'N', ?)";
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getUserId());
			pstmt.setString(4, dto.getUserPw());
			pstmt.setInt(5, dto.getCategoryId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnect.close(pstmt);
		}

		return result;
	}
	
	//글 카테고리 목록 
	public List<CategoryDto> selectCategory(Connection conn) {
		List<CategoryDto> calist = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String query = "select category_id, category_name from category order by category_id asc";
		try {
			calist = new ArrayList<CategoryDto>();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CategoryDto c = new CategoryDto();
				c.setCategoryId(rs.getInt("category_id"));
				c.setCategoryName(rs.getString("category_name"));
				
				calist.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnect.close(rs);
			JdbcConnect.close(pstmt);
		}
		return calist;
	}
	
	//게시글 수정
	public int updateBoard(Connection conn, BoardDto dto) {
		int result = -1;
		
		PreparedStatement pstmt = null;
		String query = "UPDATE TOY_BOARD set TITLE = ?, CONTENT =?, CATEGORY=?,MODI_DATE =SYSTIMESTAMP "
				+ " where id = ? and DEL_FLAG ='N'";
		
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getCategoryId());
			pstmt.setInt(4, dto.getId());
			
			
			result = pstmt.executeUpdate();
			System.out.println("1 성공 , 나머지 실패 : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnect.close(pstmt);
		}
		return result;
	}
	
	//게시글 삭제 
	public int deleteBoard(Connection conn, BoardDto dto) {
		int result = -1;
		
		PreparedStatement pstmt = null;
		String query = "UPDATE TOY_BOARD set DEL_FLAG = 'Y' "
				+ " where id = ? and DEL_FLAG ='N'";
		
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, dto.getId());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnect.close(pstmt);
		}
		return result;
	}
	
	//입력한 아이디 체크
	public int userIdCheck(Connection conn, BoardDto dto) {	
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query ="SELECT COUNT(*) AS cnt from TOY_BOARD tb where id=? "
				+ " AND  USER_ID =? AND  USER_PW =?";
		
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getId());
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, dto.getUserPw());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public int increaseView(Connection conn, int id) {
		int result = -1;
		
		PreparedStatement pstmt = null;
		String query = "UPDATE TOY_BOARD set VIEW_CNT = VIEW_CNT+1 where id = ? and DEL_FLAG ='N'";
		
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnect.close(pstmt);
		}
		return result;
	}
	
	//전체 게시글 페이징 
	public List<BoardDto> PagingList(Connection conn, Map page){
		List<BoardDto> blist = new ArrayList<BoardDto>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String query = "SELECT ID, TITLE ,CONTENT, USER_ID, USER_PW ,CREATE_DATE ,MODI_DATE ,DEL_FLAG ,CATEGORY_NAME, VIEW_CNT\r\n"
				+ "from(\r\n"
				+ "SELECT rownum as n, ID, TITLE ,CONTENT, USER_ID, USER_PW ,CREATE_DATE ,MODI_DATE ,DEL_FLAG ,CATEGORY_NAME, VIEW_CNT \r\n"
				+ "FROM TOY_BOARD b join CATEGORY c on b.CATEGORY =c.CATEGORY_ID\r\n"
				+ " WHERE c.CATEGORY_ID != (60)\r\n"
				+ " AND del_flag ='N'\r\n"
				+ " ORDER BY ID DESC\r\n"
				+ " )\r\n"
				+ " where n BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (int)page.get("start"));
			pstmt.setInt(2, (int)page.get("end"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					BoardDto b = new BoardDto();
					b.setId(rs.getInt("id"));
					b.setTitle(rs.getString("title"));
					b.setContent(rs.getString("content"));
					b.setUserId(rs.getString("user_id"));
					b.setUserPw(rs.getString("user_pw"));
					b.setDelFlag(rs.getString("del_flag"));
					b.setCreateDate(rs.getDate("create_date"));
					b.setModifyDate(rs.getDate("modi_date"));
					b.setCategoryName(rs.getNString("category_name"));
					b.setViewCnt(rs.getInt("VIEW_CNT"));
					
					blist.add(b);

				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcConnect.close(rs);
			JdbcConnect.close(pstmt);	
		}

		return blist;
	}
	
	public int totalRow(Connection conn) {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query ="SELECT COUNT(*) as cnt from TOY_BOARD where DEL_FLAG ='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnect.close(rs);
			JdbcConnect.close(pstmt);	
		}
		
		System.out.println("토탈 로우 : " + result);
		return result;
	}
	
	
	
}
