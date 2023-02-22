package com.kh.board.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.boardDTO.CommentDTO;
import com.kh.board.boardinfo.BoardCommentService;

/**
 * Servlet implementation class BoardRecommController
 */
@WebServlet("/recommreg.lo")
public class BoardRecommController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardRecommController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String step = request.getParameter("step");
		String boardId = request.getParameter("boardId");
		String userId = request.getParameter("userid");
		String userPw = request.getParameter("userpw");
		String context = request.getParameter("context");
		String commid = request.getParameter("commid");

		CommentDTO dto = new CommentDTO();
		dto.setRefId(Integer.parseInt(boardId));
		dto.setUserId(userId);
		dto.setUserPw(userPw);
		dto.setContext(context);
		dto.setCommentStep(Integer.parseInt(step));
		dto.setCommentRefId(Integer.parseInt(commid));
		
		BoardCommentService bcs = new BoardCommentService();
		
		int result = bcs.insertComment(dto);
		
		
		PrintWriter out = response.getWriter();
		out.append(String.valueOf(result));
		out.flush();
		out.close();
	}

}
