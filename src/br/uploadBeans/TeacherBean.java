package br.uploadBeans;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.servlet.http.Part;

import session.Session;
import br.entities.Exercise;
import br.entities.Method;
import br.entities.User;
import br.persistence.JPAUtil;


@ManagedBean
public class TeacherBean {
	
  private Part partFile;
  private String fileContent;
  private Part partFileTest;
  private String fileContentTest;
  
  private String fileName;
  private String src = "C:/Users/Pedro Diniz/Dropbox/PedroWorkspace/OnlineCompiler/";
  private String uploadSrc = "codeUploads/";
  private String inputCode;
  private Exercise exercise = new Exercise();
  private ArrayList<Method> methods; 

	public void upload() {
		try {
			setFileContent(new Scanner(partFile.getInputStream()).useDelimiter("\\A").next());
			new SaveFile(exercise.getInterfaceFileName(), fileContent, src+uploadSrc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			setFileContent(new Scanner(partFileTest.getInputStream()).useDelimiter("\\A").next());
			new SaveFile(exercise.getTestFileName(), fileContent, src+uploadSrc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User user = Session.getInstance().getUserLogado();
		exercise.setTeacherId(user.getUserId());
		saveExercice(exercise);
		
	}
	
	private void saveExercice(Exercise exe){
		try{
			EntityManager em = JPAUtil.getEntityManager(); 
			em.getTransaction().begin();
			em.persist(exe);
			em.getTransaction().commit(); 
			em.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
  	
  	public void listFilesForFolder() {
  		File folder = new File(src + uploadSrc);
  		File[] listOfFiles = folder.listFiles();
  		
  		String str ="";
  		for (File file : listOfFiles) {
  		    if (file.isFile()) {
  		        str+= file.getName()+"\n";
  		    }
  		}
  		inputCode = str;
	}

	public Part getPartFile() {
		return partFile;
	}

	public void setPartFile(Part partFile) {
		this.partFile = partFile;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public Part getPartFileTest() {
		return partFileTest;
	}

	public void setPartFileTest(Part partFileTest) {
		this.partFileTest = partFileTest;
	}

	public String getFileContentTest() {
		return fileContentTest;
	}

	public void setFileContentTest(String fileContentTest) {
		this.fileContentTest = fileContentTest;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getUploadSrc() {
		return uploadSrc;
	}

	public void setUploadSrc(String uploadSrc) {
		this.uploadSrc = uploadSrc;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public ArrayList<Method> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
	}
  	
  	
}