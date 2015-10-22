package br.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoginMB {
	private String login;
	private String senha;
	
	public String autenticar(){
		System.out.println("jow");
		return "index";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
