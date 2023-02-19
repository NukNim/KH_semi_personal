package com.kh.board.boardmodify;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.boardDTO.BoardDto;

/**
 * Servlet implementation class DeleteCheckController
 */
@WebServlet("/delete")
public class DeleteCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//작성글 아이디 비밀번호 확인 페이지로 이동
		
		request.getRequestDispatcher("/WEB-INF/view/BoardDelete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDto udto = new BoardDto();
		UpdateCheckService ucs = new UpdateCheckService();
		
		int bId = Integer.parseInt(request.getParameter("id"));
		String userid = request.getParameter("boardid");
		String userpw = request.getParameter("boardpw");
		
		udto.setId(bId);
		udto.setUserId(userid);
		udto.setUserPw(userpw);
		
		int idCheck = ucs.userIdCheck(udto);
		
		if(idCheck ==1) {
			int delCheck = ucs.deleteBoard(udto);
			if(delCheck ==1 ) {
				response.sendRedirect(request.getContextPath()+"/list");
			}else {
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.getRequestDispatcher("/WEB-INF/view/Boarderror.jsp").forward(request, response);
			}
			//계정 정보가 admin 경우 삭제 
		}else if(userid.equals("admin") && userpw.equals("admin")){
			int delCheck = ucs.deleteBoard(udto);
			if(delCheck ==1 ) {
				response.sendRedirect(request.getContextPath()+"/list");
			}else {
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.getRequestDispatcher("/WEB-INF/view/Boarderror.jsp").forward(request, response);
			}	
		}else {
			request.setAttribute("msg", "권한이 없는 계정 입니다.");
			request.getRequestDispatcher("/WEB-INF/view/Boarderror.jsp").forward(request, response);
		}
	
	}
}
