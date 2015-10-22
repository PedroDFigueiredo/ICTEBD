package br.uploadBeans;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class SaveFile {
	private File file;

	@SuppressWarnings("resource")
	public SaveFile(String fileName, String fileContent, String src) {
		try{
			File root = new File(src);
			file = new File(root, fileName);
			
			new FileWriter(file).append(fileContent).close();
			
	        Writer writer = null;
	        writer = new FileWriter(file);
	        writer.write(fileContent);
	        writer.flush();
	        writer.close();
	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
