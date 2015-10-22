package br.uploadBeans;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.Part;

import session.Session;
import br.compilationUnit.MyCompiler;
import br.entities.Exercise;
import br.entities.User;
import br.persistence.JPAUtil;

@ManagedBean
public class StudentBean {
	
  private Part partFile;
  private String fileContent;
  private String fileName;
  private String inputCode;

  private String src = "C:/";
  private String uploadSrc = "codeUploads/";
  private String userFolder = Session.getInstance().getUserLogado().getRegistration();
  private String teacherTestClass;
  private Integer count=0;
  
  private List<User> teachers;
  private List<Exercise> exercices;
  
  public void upload() {
  		File file = null;
		try {
			setFileContent(new Scanner(partFile.getInputStream()).useDelimiter("\\A").next());
			SaveFile save = new SaveFile(fileName, fileContent, src+uploadSrc+userFolder);
			
			MyCompiler compiler = new MyCompiler(src, uploadSrc, userFolder, "CalculadoraJunit.java");
			inputCode = compiler.CompileTheFuckingThing(save.getFile());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
  public List<User> getTeachers(){
	  if(teachers == null){
			EntityManager em = JPAUtil.getEntityManager(); 
			Query q = em.createQuery("select a from User a where type ='T'", User.class);
			this.teachers = q.getResultList();
			em.close();
		}
		return teachers;
  }
  
	public List<Exercise> getExercices() {
		try{
			User user = teachers.get(count);
			if(user!= null){
				EntityManager em = JPAUtil.getEntityManager(); 
				Query q = em.createQuery("select a from Exercise a where teacherId = "+ teachers.get(count).getUserId()+" ", Exercise.class);
				exercices = q.getResultList();
				em.close();
				count++;
			}
		}catch(Exception e){
			
		}
		return exercices;
	}

	public void setExercices(List<Exercise> exercices) {
		this.exercices = exercices;
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
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getInputCode() {
		return inputCode;
	}
	
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
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
	
	public String getUserFolder() {
		return userFolder;
	}
	
	public void setUserFolder(String userFolder) {
		this.userFolder = userFolder;
	}
	
	public String getTeacherTestClass() {
		return teacherTestClass;
	}
	
	public void setTeacherTestClass(String teacherTestClass) {
		this.teacherTestClass = teacherTestClass;
	}
	
	public void setTeachers(List<User> teachers) {
		this.teachers = teachers;
	}

  
}