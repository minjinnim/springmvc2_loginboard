package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.vo.BoardVO;
import board.vo.PageList;

public interface BoardServiceInter {
	PageList pagelist(String currentPage);
	BoardVO findOne(int idx);
	int insert(HttpServletRequest req, HttpServletResponse resp);
	int update(HttpServletRequest req, HttpServletResponse resp);
	int delete(int idx);
	BoardVO replyInfo(int idx);
	int replyInsert(HttpServletRequest req, HttpServletResponse resp);

}

