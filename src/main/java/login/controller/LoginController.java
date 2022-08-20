package login.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import login.service.LoginServiceInter;
import login.vo.MemberVO;

@Controller
public class LoginController {
	/*
	//header, footer가 필요없다면 이렇게 불러줘도 됨
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	*/
	
	@Autowired
	LoginServiceInter service;
	int timeout=2*60;
	
	@RequestMapping("login")
	public ModelAndView index() {
		ModelAndView mv=new ModelAndView();
		mv.addObject("page","login/login.jsp");
		mv.setViewName("index"); //->WEB-INF/view/login/index.jsp가됨
		return mv;
	}
	
	@RequestMapping("loginProc")
	public ModelAndView loginProc(MemberVO member,HttpServletRequest req) {
		
		ModelAndView mv=new ModelAndView();
		
		boolean result=service.login(member);
		
		if(result) {
			req.getSession().setAttribute("id", member.getId());
			req.getSession().setMaxInactiveInterval(timeout); 
		
			System.out.println("접속한 ip:"+req.getRemoteAddr());
			SimpleDateFormat fmt= new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
			System.out.println("접속시간:"+fmt.format(req.getSession().getCreationTime()));
			
			mv.addObject("sessiontime", req.getSession().getMaxInactiveInterval());
			mv.addObject("page","home/main.jsp");
			
			System.out.println("로그인성공");
			
		}else {
			mv.addObject("page", "login/login.jsp");
			System.out.println("로그인실패");
		}
		
		mv.setViewName("index");
		
		return mv;
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest req) {
		req.getSession().invalidate();
		System.out.println("로그아웃 시간:"+req.getSession().getLastAccessedTime());
		SimpleDateFormat fmt= new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		System.out.println("접속해제시간:"+fmt.format(req.getSession().getLastAccessedTime()));
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("page","home/main.jsp");
		mv.setViewName("index");
		return mv;
	}
	
	
}
