package com.kh.board.list;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.boardDTO.BoardDto;

@WebServlet("/list")
public class BoardController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardListService bls = new BoardListService();
		List<BoardDto> blist = bls.boardList();
		List<BoardDto> bnlist = bls.BoardNotiList();
		List<BoardDto> anlist = bls.AllNotiList();
		
		req.setAttribute("blist", blist);
		req.setAttribute("bnlist", bnlist);
		req.setAttribute("anlist", anlist);
		req.getRequestDispatcher("/WEB-INF/view/BoardList.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
