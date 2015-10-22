package br.compilationUnit;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;


public class MyCompiler {
	
	private String src;
	private String uploadSrc;
	private String userFolder;
	private String loadClasName;

	public MyCompiler(String src, String uploadSrc, String userFolder,
			String loadClasName) {
		super();
		this.src = src;
		this.uploadSrc = uploadSrc;
		this.userFolder = userFolder;
		this.loadClasName = loadClasName;
	}

	public String CompileTheFuckingThing(File file){
  		String aux="";
		try{
            /** Compilation Requirements *********************************************************************************************/
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

            // This sets up the class path that the compiler will use.
            // I've added the .jar file that contains the DoStuff interface within in it...
            List<String> optionList = new ArrayList<String>();

            String separator = System.getProperty("path.separator"); 
            StringBuilder path = new StringBuilder(64);
            
            path.append("-classpath ;");
            path.append(src+uploadSrc+"junit.jar junit.textui.TestRunner"+separator);
            path.append(src+uploadSrc+separator);
            path.append(src+uploadSrc+userFolder+separator);
            
            path.append(System.getProperty("java.class.path"));
            /*
            String path = buildClassPath(src+uploadSrc ,src+uploadSrc+"*", src+uploadSrc+userFolder);
           // path += System.getProperty("java.class.path");
            System.out.println(path.toString());*/
            optionList.add(path.toString());
            
            System.out.println(path.toString());
            Iterable<? extends JavaFileObject> compilationUnit
                    = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file, new File(src+uploadSrc+loadClasName)));
            
            JavaCompiler.CompilationTask task = compiler.getTask(
                null, 
                fileManager, 
                diagnostics, 
                optionList, 
                null, 
                compilationUnit);
            
            /********************************************************************************************* Compilation Requirements **/
            if (task.call()) {
                /** Load and execute *************************************************************************************************/
                System.out.println("Yipe");
                // Create a new custom class loader, pointing to the directory that contains the compiled
                // classes, this should point to the top of the package structure!
               
                URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(src).toURI().toURL()});
                URLClassLoader teacharClassLoader = new URLClassLoader(new URL[]{new File(src+uploadSrc+userFolder).toURI().toURL(), 
                													   			 new File(src+uploadSrc).toURI().toURL()},classLoader);
                // Load the class from the classloader by name....
                
                Class<?> studentClass = teacharClassLoader.loadClass(loadClasName.replace(".java", ""));

                Method[] allMethods = studentClass.getDeclaredMethods();
                
                for(Method method: allMethods){
                	Class params[] = {};
                	Object paramsObj[] = {};
                	Object instance = studentClass.newInstance();
                	Method thisMethod = studentClass.getDeclaredMethod(method.getName(), params);
                    
                	// run the testAdd() method on the instance:
                	thisMethod.invoke(instance, paramsObj);
                	/*for(Class cs : params){
                		System.out.println(cs.toString());
                	}*/
                }
                
                aux = "Classe compilada com sucesso!";
                
               
                /************************************************************************************************* Load and execute **/
            } else {
            	
                for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                  /*  System.out.format("%s\nError on line %d in %s%n",
                            diagnostic.getMessage(null), diagnostic.getLineNumber(),
                            diagnostic.getSource().toUri());*/
                    aux+= diagnostic.getMessage(null)+ "\n"+diagnostic.getLineNumber()+"\n";
                    String [] temp = diagnostic.getSource().toUri().toString().split("/");
                    aux+= temp[temp.length-1]+"\n\n";
                }
            }
            fileManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return aux;
    }
	
	private static String buildClassPath(String... paths) {
	    StringBuilder sb = new StringBuilder();
	    for (String path : paths) {
	        if (path.endsWith("*")) {
	            path = path.substring(0, path.length() - 1);
	            File pathFile = new File(path);
	            for (File file : pathFile.listFiles()) {
	                if (file.isFile() && file.getName().endsWith(".jar")) {
	                    sb.append(path);
	                    sb.append(file.getName());
	                    sb.append(System.getProperty("path.separator"));
	                }
	            }
	        } else {
	            sb.append(path);
	            sb.append(System.getProperty("path.separator"));
	        }
	    }
	    return sb.toString();
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


	public String getLoadClasName() {
		return loadClasName;
	}


	public void setLoadClasName(String loadClasName) {
		this.loadClasName = loadClasName;
	}
}


