package br.entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="exercice")
public class Exercise {
	@Id @GeneratedValue
	private Integer exerciceId;
	private Integer teacherId;
	private String name;
	private String testFileName;
	private String interfaceFileName;
	private String obs;
	
	public Integer getExerciceId() {
		return exerciceId;
	}
	public void setExerciceId(Integer exerciceId) {
		this.exerciceId = exerciceId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTestFileName() {
		return testFileName;
	}
	public void setTestFileName(String testFileName) {
		this.testFileName = testFileName;
	}
	public String getInterfaceFileName() {
		return interfaceFileName;
	}
	public void setInterfaceFileName(String interfaceFileName) {
		this.interfaceFileName = interfaceFileName;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
		
}
