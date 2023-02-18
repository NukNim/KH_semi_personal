package com.kh.board.admincheck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminCheckController
 */
@WebServlet("/check.lo")
public class AdminCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("관리자 체크");
		
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		
		PrintWriter out = response.getWriter();
		System.out.println("비교 아이디 :    " + userid);
		System.out.println("비교 비밀번호 :    " + userpw);
		if(userid.equals("admin") && userpw.equals("admin")) {
			out.append("1");			
		}else {
			out.append("해당 정보로는 권한이 없습니다.");
		}

		out.flush();
		out.close();

		
	}

}
