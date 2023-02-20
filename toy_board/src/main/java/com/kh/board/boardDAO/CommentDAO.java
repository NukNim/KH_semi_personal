package com.kh.board.boardDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.board.boardDTO.CommentDTO;
import com.kh.jdbc.JdbcConnect;

public class CommentDAO {
	
	
	public int insertComment(Connection conn, CommentDTO dto) {
		int result = -1;
		String query = "insert into BOARD_COMMENT (COMMENT_ID, REF_ID, USER_ID, USER_PW, CONTEXT, REG_DATE, COMMENT_STEP)\r\n"
				+ "VALUES(COMMENT_SEQ.NEXTVAL, ?, ?, ?, ?, SYSTIMESTAMP, ?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, dto.getRefId());
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, dto.getUserPw());
			pstmt.setString(4, dto.getContext());
			pstmt.setInt(5, dto.getCommentStep());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnect.close(pstmt);
		}
		return result;
	}
	
	public List<CommentDTO> selectComment(Connection conn, int id){
		List<CommentDTO> clist = new ArrayList<CommentDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select rownum as N, bc.user_id, bc.user_pw, bc.context, bc.reg_date, bc.comment_step"
				+ "     from BOARD_COMMENT bc join TOY_BOARD tb on bc.ref_id = tb.id"
				+ "     where bc.ref_id = ? and comment_step = 1"
				+ "     order by bc.comment_id desc";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			System.out.println("쿼리 실행");
			while(rs.next()) {
				CommentDTO cdto = new CommentDTO();
				
				System.out.println("쿼리 실행값 넣는중");
				
				cdto.setUserId(rs.getString("USER_ID"));
				cdto.setUserPw(rs.getString("USER_PW"));
				cdto.setContext(rs.getString("CONTEXT"));
				cdto.setRegDate(rs.getDate("REG_DATE"));
				cdto.setCommentStep(rs.getInt("COMMENT_STEP"));
				
				clist.add(cdto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return clist;
	}

}
