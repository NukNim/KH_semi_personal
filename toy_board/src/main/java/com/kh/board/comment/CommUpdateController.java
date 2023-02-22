package com.kh.board.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.boardDTO.CommentDTO;

/**
 * Servlet implementation class CommUpdateController
 */
@WebServlet("/commupdate.lo")
public class CommUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		String commid = request.getParameter("commid");
		String boardid = request.getParameter("boardId");
		String pNum = request.getParameter("pNum");
		String context = request.getParameter("context");
		
		CommentIdService cis = new CommentIdService();
		CommentDTO dto = new CommentDTO();
		String result = "";
		
		dto.setCommentId(Integer.parseInt(commid));
		dto.setUserId(userid);
		dto.setUserPw(userpw);
		dto.setContext(context);
		int check = cis.searchId(dto);
		
		PrintWriter out = response.getWriter();
		
		if(userid.equals("admin") && userpw.equals("admin")) {
			int suc = cis.updateComment(dto);
			out.append(String.valueOf(suc));			
		}else if(check == 1){
			int suc = cis.updateComment(dto);
			out.append(String.valueOf(suc));
		}else {
			out.append("해당 정보로는 권한이 없습니다.");
		}

		out.flush();
		out.close();
		
	}

}
