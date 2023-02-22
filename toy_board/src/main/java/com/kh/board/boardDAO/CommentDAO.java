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
		String query = "";
		if(dto.getCommentStep() == 1) {
			query = "insert into BOARD_COMMENT (COMMENT_ID, REF_ID, USER_ID, USER_PW, CONTEXT, REG_DATE, COMMENT_STEP,COMM_REF_ID)"
					+ "VALUES(COMMENT_SEQ.NEXTVAL, ?, ?, ?, ?, SYSTIMESTAMP, ?,COMMENT_SEQ.NEXTVAL)";
		}else {
			query = "insert into BOARD_COMMENT (COMMENT_ID, REF_ID, USER_ID, USER_PW, CONTEXT, REG_DATE, COMMENT_STEP,COMM_REF_ID)"
					+ "VALUES(COMMENT_SEQ.NEXTVAL, ?, ?, ?, ?, SYSTIMESTAMP, ?,?)";
		}
			
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, dto.getRefId());
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, dto.getUserPw());
			pstmt.setString(4, dto.getContext());
			pstmt.setInt(5, dto.getCommentStep());
			
			if(dto.getCommentStep() != 1) {
				pstmt.setInt(6, dto.getCommentRefId());
			}
			
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
		
		String query = "select rownum as N, bc.comment_id ,bc.user_id, bc.user_pw, bc.context, bc.reg_date, bc.comment_step"
				+ "     from BOARD_COMMENT bc join TOY_BOARD tb on bc.ref_id = tb.id"
				+ "     where bc.ref_id = ?"
				+ "     order by ref_id DESC , comm_ref_id DESC, comment_step ASC, COMMENT_id DESC";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentDTO cdto = new CommentDTO();
				
				cdto.setCommentId(rs.getInt("COMMENT_ID"));
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
	
	public int searchId(Connection conn, CommentDTO dto) {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) AS CNT FROM BOARD_COMMENT WHERE COMMENT_ID = ? AND USER_ID = ? AND USER_PW = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getCommentId());
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, dto.getUserPw());
			
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

		return result;
	}
	
	public int updateComment(Connection conn, CommentDTO dto) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query = " UPDATE BOARD_COMMENT SET CONTEXT = ? , REG_DATE = SYSTIMESTAMP"
				+ "  WHERE COMMENT_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, dto.getContext());
			pstmt.setInt(2, dto.getCommentId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnect.close(pstmt);
		}

		return result;
	}

	public int deleteComment(Connection conn, CommentDTO dto) {
		int result = -1;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM BOARD_COMMENT "
				+ "  WHERE COMMENT_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, dto.getCommentId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnect.close(pstmt);
		}

		return result;
	}

}
