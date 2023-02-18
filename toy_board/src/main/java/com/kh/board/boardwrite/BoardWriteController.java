package com.kh.board.boardwrite;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.boardDTO.BoardDto;
import com.kh.board.boardDTO.CategoryDto;

/**
 * Servlet implementation class BoardWriteController
 */
@WebServlet("/write")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDto dto = new BoardDto();
		BoardWriteService bws = new BoardWriteService();
		List<CategoryDto> calist = bws.selectCategory();
		
		dto = (BoardDto)request.getSession().getAttribute("userinfo");
		request.setAttribute("calist", calist);
		
		request.getRequestDispatcher("/WEB-INF/view/BoardWrite.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDto dto = new BoardDto();
		BoardDto sdto = new BoardDto();
		BoardWriteService bws = new BoardWriteService();
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("boardid");
		String bTitle = request.getParameter("boardtitle");
		String bContent = request.getParameter("bContext");
		String pw = request.getParameter("boardpw");
		int cId = Integer.parseInt(request.getParameter("cateSelect"));
		
		System.out.println(cId);
		
		dto.setUserId(id);
		dto.setUserPw(pw);
		dto.setTitle(bTitle);
		dto.setContent(bContent);
		dto.setCategoryId(cId);
		
		
		int result = bws.insertBoard(dto);
		
		
		
		sdto.setUserId(id);
		sdto.setUserPw(pw);

		if(result == 1) {
			request.getSession().setAttribute("userinfo", sdto);
			response.sendRedirect(request.getContextPath()+"/list");
		}else {
			response.sendRedirect(request.getContextPath()+"/");
		}

	}

}
