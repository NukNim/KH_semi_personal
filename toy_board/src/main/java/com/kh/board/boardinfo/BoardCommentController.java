package com.kh.board.boardinfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.boardDTO.CommentDTO;

/**
 * Servlet implementation class BoardCommentController
 */
@WebServlet("/commentReg")
public class BoardCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardId = request.getParameter("boardId");
		String pNum = request.getParameter("pNum");
		String userId = request.getParameter("commentId");
		String userPw = request.getParameter("commentPw");
		String comment = request.getParameter("comment");
		String step = request.getParameter("stepType");

		
		CommentDTO dto = new CommentDTO();
		dto.setRefId(Integer.parseInt(boardId));
		dto.setUserId(userId);
		dto.setUserPw(userPw);
		dto.setContext(comment);
		dto.setCommentStep(Integer.parseInt(step));
		
		BoardCommentService bcs = new BoardCommentService();
		
		int result = bcs.insertComment(dto);
		
		response.sendRedirect(request.getContextPath()+"/view?id="+boardId+"&p="+pNum);
		
	}

}
