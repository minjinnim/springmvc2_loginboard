package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.service.BoardServiceInter;

@Controller
public class BoardController{
	
	@Autowired(required = false)
	BoardServiceInter service;
	
	@RequestMapping("list")
	public ModelAndView list(String currentPage,HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagelist", service.pagelist(currentPage));
		mv.addObject("page","board/pageList.jsp");
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("findOne")
	public ModelAndView findOne(int idx) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("board", service.findOne(idx));
		mv.addObject("page","board/findOne.jsp");
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("writeForm")
	public ModelAndView writeForm() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("page","board/writeForm.jsp");
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("writeFormProc")
	public String writeFormProc(HttpServletRequest req, HttpServletResponse resp) {
		int result=service.insert(req,resp);
		return "redirect:/board/list";
	}
	
	@RequestMapping("update")
	public String update(HttpServletRequest req, HttpServletResponse resp) {
		int result=service.update(req,resp);
		return "redirect:/board/list";
	}
	
	@RequestMapping("delete")
	public String delete(int idx) {
		int result=service.delete(idx);
		return "redirect:/board/list";
	}
	
	@RequestMapping("replyForm")
	public ModelAndView replyForm(int idx) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("board", service.replyInfo(idx));
		mv.addObject("page","board/replyForm.jsp");
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("replyFormProc")
	public String replyFormProc(HttpServletRequest req, HttpServletResponse resp) {
		int result=service.replyInsert(req,resp);
		return "redirect:/board/list";
	}

}
