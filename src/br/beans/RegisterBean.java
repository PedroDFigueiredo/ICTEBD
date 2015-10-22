package br.beans;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.entities.User;
import br.persistence.JPAUtil;
import br.utils.MD5;

@ManagedBean(name = "register")
public class RegisterBean {
	private String name;
	private String email;
	private String registration;
	private String pass;
	private String type;
	
	
	private List<SelectItem> types;
	
	private User user = new User();
	
	public String save(User entryUser){
		if(checkFields(entryUser)){
			entryUser.setPass(new MD5(entryUser.getPass()).passMD5);
			
			EntityManager em = JPAUtil.getEntityManager(); 
			em.getTransaction().begin();
			em.persist(entryUser);
			em.getTransaction().commit(); 
			em.close();
			String type = entryUser.getType();
			if(type.equals("S")){
				type = "student";
				String src = "C:/Users/Pedro Diniz/Dropbox/PedroWorkspace/OnlineCompiler/codeUploads/";
				File dir = new File(src+entryUser.getRegistration()+"/");
				dir.mkdir();
				
			}else
				type = "teacher";
			 System.out.println(type);
			return type+".xhtml?faces-redirect=true";
		}
		return "Usuário já registrado";
	}
	
	private boolean checkFields(User entryUser){
		EntityManager em = JPAUtil.getEntityManager(); 
		String str = "select a from User a where a.email = '"+entryUser.getEmail()+"' or a.registration = '"+entryUser.getRegistration()+"'";
		Query q = em.createQuery(str, User.class);
		List<User>users = q.getResultList(); 

		if(users.size() != 0)
			return false;

		return true;
	}
	
	@PostConstruct
	public void init() {
	    types = new ArrayList<SelectItem>();
	    types.add(new SelectItem("S", "Student"));
	    types.add(new SelectItem("T", "Teacher"));
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public List<SelectItem> getTypes() {
		return types;
	}

	public void setTypes(List<SelectItem> types) {
		this.types = types;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	

}
