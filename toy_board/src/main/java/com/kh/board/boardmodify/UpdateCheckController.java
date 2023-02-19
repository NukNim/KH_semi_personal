package com.kh.board.boardmodify;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.boardDTO.BoardDto;
import com.kh.board.boardDTO.CategoryDto;
import com.kh.board.boardinfo.BoardViewService;
import com.kh.board.boardwrite.BoardWriteService;

/**
 * Servlet implementation class UpdateCheckController
 */
@WebServlet("/update")
public class UpdateCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCheckController() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		List<CategoryDto> calist = new BoardWriteService().selectCategory();
		BoardDto b = new BoardViewService().BoardView(Integer.parseInt(id));

		request.setAttribute("bview", b);
		request.setAttribute("calist", calist);
		
		
		request.getRequestDispatcher("/WEB-INF/view/BoardUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UpdateCheckService ucs = new UpdateCheckService();
		BoardDto dto = new BoardDto();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String bTitle = request.getParameter("boardtitle");
		String bContent = request.getParameter("bContext");
		int cId = Integer.parseInt(request.getParameter("cateSelect"));
		
		String userid = request.getParameter("boardid");
		String userpw = request.getParameter("boardpw");
		
		dto.setId(id);
		dto.setTitle(bTitle);
		dto.setContent(bContent);
		dto.setCategoryId(cId);
		
		int result = ucs.updateBoard(dto);
		
		if(result == 1) {
			response.sendRedirect(request.getContextPath()+"/view?id="+id+"&p="+request.getParameter("pNum"));
		}else {
			response.sendRedirect(request.getContextPath()+"/update?id="+id+"&p="+request.getParameter("pNum"));
		}
	}

}
