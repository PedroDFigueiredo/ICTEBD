package br.beans;

import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

import br.InlineCompilerExemple;


@ManagedBean
public class FileBean {
	
  private Part partFile;
  private String fileContent;
  private String fileName;

  public void upload() {
		try {
			setFileContent(new Scanner(partFile.getInputStream())
		      .useDelimiter("\\A").next());

			System.out.println(fileName);
			InlineCompilerExemple in = new InlineCompilerExemple(fileName, fileContent);
			in.CompileTheFuckingThing();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
}