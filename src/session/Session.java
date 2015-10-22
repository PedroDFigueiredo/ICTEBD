package session;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.entities.User;


public class Session {
	private static Session instance;
	
	public static Session getInstance(){
		if (instance == null)
			instance = new Session();
		return instance;
	}
	
	private Session(){};
	
	private ExternalContext currentExternalContext(){
		if (FacesContext.getCurrentInstance() == null){
			throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisilção http");
		}else{
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}
	
	public User getUserLogado(){
		return (User) getAttribute("usuarioLogado");
	}
	
	public void setUserLogado(User usuario){
		setAttribute("usuarioLogado", usuario);
	}
	
	public Object getAttribute(String nome){
		return currentExternalContext().getSessionMap().get(nome);
	}
	
	public void setAttribute(String nome, Object valor){
		currentExternalContext().getSessionMap().put(nome, valor);
	}
	
	public void encerrarSession(){
		currentExternalContext().invalidateSession();
	}
}
