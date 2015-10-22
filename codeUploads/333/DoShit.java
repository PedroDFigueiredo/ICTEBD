public class DoShit implements  DoStuff{
	static {
		//System.out.println("New Instance DoShit");
	}

	public String nome;
	public String matricula;

	public void doStuff() {
	 	System.out.println("Void doStuff");

	}

	public String doStr(){
		System.out.println("nome "+nome);
		System.out.println("matricula "+matricula);
		return "";
	}
	public DoShit(){

		nome = "jow";
		matricula = "2012785210";

		System.out.println("Doshit Contructor");
		//doStuff();
	}
	public DoShit(String jow){
		System.out.println("DoShit Contructor "+jow+" ---\n");
	}

	public Integer joew(){
		return 1;
	}
}




