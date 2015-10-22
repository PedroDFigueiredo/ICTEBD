package br.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import session.Session;
import br.entities.User;
import br.persistence.JPAUtil;
import br.utils.MD5;

@ManagedBean(name="logBean")
@SessionScoped
public class LogBean implements Serializable{
	
	private String registration;
	private String pass;
	private String type; 

	public boolean isLogged = false;
	
	public LogBean(){
		
	}
	
	public String action(){
		System.out.println("entrou");
		User nop = checkUser();
		System.out.println("1");
		if(nop != null){
			System.out.println("0");
			Session.getInstance().setUserLogado(nop);
			System.out.println("1");
			isLogged = true;
			System.out.println("2");
			return nop.getType().toString().equals("S") ? "student" : "teacher";
		}
		System.out.println("Saiu");
		return "login";
	}
	
	public User checkUser(){
		EntityManager em = JPAUtil.getEntityManager();
		String str = "select a from User a where  a.registration = '"+registration+"'  and a.pass = '"+new MD5(pass).passMD5+"'";
		
		Query q = em.createQuery(str, User.class);
		List<User>users = q.getResultList();
		try{
		if(users.size() != 0){
			return users.get(0);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String logOut(){
		System.out.println("Logout "+registration);
		isLogged = false;
		return "index";
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	
	
}
