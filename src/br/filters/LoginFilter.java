package br.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.beans.LogBean;

public class LoginFilter implements Filter{

	public LoginFilter() {
		super();
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp= (HttpServletResponse) response;
		LogBean session = (LogBean) req.getSession().getAttribute("logBean");
		String url = req.getRequestURI();
		
		if(session == null || !session.isLogged){
			if(url.indexOf("student.xhtml") >= 0 || url.indexOf("teacher.xhtml") >= 0 || url.indexOf("logout.xhtml") >= 0){
				resp.sendRedirect(req.getServletContext().getContextPath()+"/login.xhtml");
			}else{
				chain.doFilter(request, response);
			}
		}else{
			if(url.indexOf("register.xhtml") >=0 || url.indexOf("login.xhtml") >= 0){
				String str = session.checkUser().getType().equals("S") ? "/student.xhtml" : "/teacher.xhtml";
				resp.sendRedirect(req.getServletContext().getContextPath() + str);
				
			}else if (url.indexOf("logout.xhtml") >=0){
				req.getSession().removeAttribute("logBean");
				resp.sendRedirect(req.getServletContext().getContextPath()+"/login.xhtml");
			}else{
				chain.doFilter(request, response);
			}
		}
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	
	
}
