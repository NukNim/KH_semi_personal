package com.kh.board.boardDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.board.boardDTO.BoardDto;
import com.kh.board.boardDTO.CategoryDto;
import com.kh.jdbc.JdbcConnect;

public class BoardDao {
	
	public BoardDao() {
		// TODO Auto-generated constructor stub
	}
	
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
	
	public BoardDto selectView(Connection conn, int id) {
		BoardDto result = new BoardDto();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String query = "SELECT ID, TITLE ,CONTENT, USER_ID, USER_PW ,CREATE_DATE ,MODI_DATE ,DEL_FLAG ,CATEGORY_NAME \r\n"
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
	
}
