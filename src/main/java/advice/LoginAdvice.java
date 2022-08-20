package advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class LoginAdvice {
	//@Autowired
	//HttpServletRequest req;
	
	public LoginAdvice() {}
	
	public Object loginCheck(ProceedingJoinPoint joinpoint) throws Throwable{ 
		
		
		HttpServletRequest req=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		System.out.println(req.getAttribute("id"));
		
		
		/*
		Object[] obj = jointpoint.getArgs();
		HttpSession session = (HttpSession)obj[2];
		*/
		
		
		if(req.getSession().getAttribute("id")==null) {
			//"login/login"으로 이동
			return "null";
		}else {
			Object obj = joinpoint.proceed();	
			return obj;
		}
		
	}
}
