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
 * Servlet implementation class CommentIdController
 */
@WebServlet("/commid.lo")
public class CommentIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentIdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		String commid = request.getParameter("commid");
		CommentIdService cis = new CommentIdService();
		CommentDTO dto = new CommentDTO();
		dto.setCommentId(Integer.parseInt(commid));
		dto.setUserId(userid);
		dto.setUserPw(userpw);
		
		PrintWriter out = response.getWriter();
		
		if(userid.equals("admin") && userpw.equals("admin")) {
			out.append("1");			
		}else if(!userid.isEmpty() && userid != null){
			String result = String.valueOf(cis.searchId(dto));
			out.append(result);
		}else {
			out.append("해당 정보로는 권한이 없습니다.");
		}

		out.flush();
		out.close();
		
		
	}

}
