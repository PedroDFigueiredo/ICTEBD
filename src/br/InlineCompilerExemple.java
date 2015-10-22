package br;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
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

//import codeUploads.DoStuff;

public class InlineCompilerExemple {
	
	
	private File file;
	private File root;
	private File temp;
	public InlineCompilerExemple(String fileName, String fileContent) throws Exception{
		root = new File("C:/Users/Pedro Diniz/Dropbox/PedroWorkspace/OnlineCompiler/src/");
		file = new File(root, "br/codeUploads/"+fileName);
		
		file.getParentFile().mkdirs();
		new FileWriter(file).append(fileContent).close();
		
        Writer writer = null;
        writer = new FileWriter(file);
        writer.write(fileContent);
        writer.flush();
        writer.close();
		
	               
	}

	public void CompileTheFuckingThing(){
		try{
            /** Compilation Requirements *********************************************************************************************/
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

            // This sets up the class path that the compiler will use.
            // I've added the .jar file that contains the DoStuff interface within in it...
            List<String> optionList = new ArrayList<String>();
            optionList.add("-classpath");
            optionList.add(".;C:/Users/Pedro Diniz/Dropbox/PedroWorkspace/OnlineCompiler/build/classes;"+System.getProperty("java.class.path"));

            Iterable<? extends JavaFileObject> compilationUnit
                    = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file));
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
                URLClassLoader classLoader = new URLClassLoader(new URL[]{new File("C:/Users/Pedro Diniz/Dropbox/PedroWorkspace/OnlineCompiler/src/").toURI().toURL(),
                		new File("C:/Users/Pedro Diniz/Dropbox/PedroWorkspace/OnlineCompiler/build/classes/").toURI().toURL()});
                // Load the class from the classloader by name....
                System.out.println("br/codeUploads/"+file.getName().replace(".java", ""));
                Class<?> loadedClass = classLoader.loadClass("br.codeUploads."+file.getName().replace(".java", ""));
                // Create a new instance...
                Object obj = loadedClass.newInstance();
                // Santity check
                
                //jdk 8 seria getTypeName()
                System.out.println(obj.getClass().getName()+"\n"+obj.getClass().getCanonicalName()+"\n"+
                obj.getClass().getConstructors()+"\n"+obj.getClass().getMethods()[0]+"\n"+obj.getClass());
                
                //obj.getClass().getMethods()[0].invoke(obj, args)
              /*  if (obj instanceof DoStuff) {
                    // Cast to the DoStuff interface
                    DoStuff stuffToDo = (DoStuff)obj;
                    // Run it baby
                    stuffToDo.doStuff();
                }*/
                /************************************************************************************************* Load and execute **/
            } else {
                for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                    System.out.format("%s\nError on line %d in %s%n",
                            diagnostic.getMessage(null), diagnostic.getLineNumber(),
                            diagnostic.getSource().toUri());
                }
            }
            fileManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}

	

