package login.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import login.dao.LoginDAO;
import login.dao.LoginDAOInter;
import login.vo.MemberVO;

@Service
public class LoginService implements LoginServiceInter{
	
	@Autowired
	LoginDAOInter dao;
	
	@Override
	public boolean login(MemberVO member) {
		return dao.login(member);
	}
	
	
}
