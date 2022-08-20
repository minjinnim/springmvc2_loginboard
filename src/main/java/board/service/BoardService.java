package board.service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.BoardDAOInter;
import board.vo.BoardVO;
import board.vo.PageList;


@Service
public class BoardService implements BoardServiceInter{

	@Autowired(required=false)
	BoardDAOInter dao;
	
	public PageList pagelist(String _currentPage) {
		
		//전체게시물 수
		int totalCount=0;
		//페이지당 글의 수
		int countPerPage=10;
		//전체페이지수
		int totalPage=0;
		//시작페이지
		int startPage=0;
		//끝페이지
		int endPage=0;
		//글의 시작번호
		int startRow=0;
		//글의 끝번호
		int endRow=0;
		//현재 페이지
		int currentPage=1;
		
		List<BoardVO> list = null;
		
		PageList plist=new PageList();
		
		//전체게시물 수 check
		totalCount=dao.totalCount();
		
		//전체페이지수
		totalPage=(totalCount/countPerPage)+1;
		//전체게시물수가 나머지 0이될 경우 페이지는 1페이지가 작아짐
		if((totalCount%countPerPage)==0) 
		totalPage=(totalCount/countPerPage);
	
		if(_currentPage==null) {
			currentPage=1;
		}else if(!_currentPage.equals("")) {
			currentPage=Integer.parseInt(_currentPage);
		}
		
		//현재페이지에 대한 시작번호와 끝번호
		startRow=(currentPage-1)*countPerPage+1;
		endRow=startRow+countPerPage-1;
				
		//위에서 확인한 정보를 이용해 페이지의 dao로부터 정보를 가져옴
		list=dao.pageList(startRow, endRow, totalPage, currentPage, totalCount);
				
		//표시할 시작페이지
		if(currentPage<=5){
			startPage=1;	
		}else{
			if(currentPage%5==0){
				startPage=(currentPage/5)*5;	
			}else{
				startPage=(currentPage/5)*5+1; //--처리 10페이지일 때 문제 발생
			}
		}
		
		//표시할 끝페이지
		endPage=startPage+4;
		
		//전체페이지가 표현하려는 페이지보다 클 경우 전체페이지수까지 표시 
		if(endPage>totalPage) endPage=totalPage;
				
		//findAll.jsp에 view를 하기위해 필요한 데이터만 담아둠(Model)
		plist.setList(list);
		plist.setStartPage(startPage);
		plist.setEndPage(endPage);
		plist.setCurrentPage(currentPage);
		plist.setCountPerPage(countPerPage);
		plist.setTotalCount(totalCount);
		plist.setTotalPage(totalPage);
		
		return plist;
	}

	@Override
	public BoardVO findOne(int idx) {
		dao.readcountUp(idx);
		BoardVO board=dao.findOne(idx);
		return board;
	}

	@Override
	public int insert(HttpServletRequest req, HttpServletResponse resp) {
		String savePath="C:\\project\\work\\jwork\\loginboard\\src\\main\\webapp\\WEB-INF\\static\\file\\uploadFold";
		int fileSize=10*1024*1024;
		MultipartRequest multi=null;
		
		try {
			
			multi = new MultipartRequest(req,savePath,fileSize,"utf-8",new DefaultFileRenamePolicy());
			
			Enumeration files=multi.getFileNames();
			
			String file=(String)files.nextElement();
			
			String title=multi.getParameter("title");
			String content=multi.getParameter("content");
			String filename = multi.getFilesystemName(file);
			
			BoardVO board=new BoardVO();
			board.setTitle(title);
			board.setContent(content);
			board.setWriteId((String)req.getSession().getAttribute("id"));
			board.setWriteName((String)req.getSession().getAttribute("id"));
			board.setFilename(filename);
			dao.insert(board);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int update(HttpServletRequest req, HttpServletResponse resp) {
		BoardVO board=new BoardVO();
		int idx=0;
		String title=null;
		String content=null;
		String filename=null;
		
		String savePath="C:\\project\\work\\jwork\\loginboard\\src\\main\\webapp\\WEB-INF\\static\\file\\uploadFold";
		int fileSize=10*1024*1024;
		MultipartRequest multi=null;
		
		try {
			multi = new MultipartRequest(req,savePath,fileSize,"utf-8",new DefaultFileRenamePolicy());
			Enumeration files=multi.getFileNames();
			
			idx=Integer.parseInt(multi.getParameter("idx"));
			title=multi.getParameter("title");
			content=multi.getParameter("content");
			
			board.setIdx(idx);
			board.setTitle(title);
			board.setContent(content);
		
			return dao.update(board);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public int delete(int idx) {
		return dao.delete(idx);
	}
	
	@Override
	public BoardVO replyInfo(int idx) {
		BoardVO board=dao.findOne(idx);
		BoardVO sendboard=new BoardVO();
		sendboard.setIdx(board.getIdx());
		sendboard.setTitle(board.getTitle());
		sendboard.setGroupid(board.getGroupid());
		sendboard.setDepth(board.getDepth());
		
		return sendboard;
	}
	
	@Override
	public int replyInsert(HttpServletRequest req, HttpServletResponse resp) {
		String title="";
		String content="";
		String writeName="";
		String filename="";
		String groupid="";
		String depth="";
		String savePath="C:\\project\\work\\jwork\\loginboard\\src\\main\\webapp\\WEB-INF\\static\\file\\uploadFold";
		int fileSize=10*1024*1024;
		MultipartRequest multi=null;
		try {
		
			multi = new MultipartRequest(req,savePath,fileSize,"utf-8",new DefaultFileRenamePolicy());
			
			title=multi.getParameter("title");
			content=multi.getParameter("content");
			writeName=multi.getParameter("writerName");
			groupid=multi.getParameter("groupid");
			depth=multi.getParameter("depth");
			
			Enumeration files=multi.getFileNames();
			String file=(String)files.nextElement();
			filename=multi.getFilesystemName(file);
				
			BoardVO board=new BoardVO();
			board.setTitle(title);
			board.setContent(content);
			board.setWriteId((String)req.getSession().getAttribute("id"));
			board.setWriteName((String)req.getSession().getAttribute("id"));
			board.setGroupid(Integer.parseInt(groupid));
			board.setDepth(Integer.parseInt(depth));
			board.setFilename(filename);
			return dao.replyInsert(board);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
