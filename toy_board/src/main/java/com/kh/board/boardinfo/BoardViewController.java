package com.kh.board.boardinfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.boardDTO.BoardDto;
import com.kh.board.boardDTO.CommentDTO;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		BoardDto b = new BoardViewService().BoardView(Integer.parseInt(id));
		new BoardViewService().increaseView(id);
		
		List<CommentDTO> clist = new BoardCommentService().selectComment(Integer.parseInt(id));
		
		request.setAttribute("bview", b);
		request.setAttribute("clist", clist);
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/BoardView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
