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
@WebServlet("/commdelete.lo")
public class CommDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommDeleteController() {
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
		
		CommentIdService cis = new CommentIdService();
		CommentDTO dto = new CommentDTO();
		String result = "";
		
		dto.setCommentId(Integer.parseInt(commid));
		dto.setUserId(userid);
		dto.setUserPw(userpw);
		int check = cis.searchId(dto);
		
		PrintWriter out = response.getWriter();
		
		if(userid.equals("admin") && userpw.equals("admin")) {
			int suc = cis.deleteComment(dto);
			out.append(String.valueOf(suc));			
		}else if(check == 1){
			int suc = cis.deleteComment(dto);
			out.append(String.valueOf(suc));
		}else {
			out.append("해당 정보로는 권한이 없습니다.");
		}

		out.flush();
		out.close();
		
	}

}
