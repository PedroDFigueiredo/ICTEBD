public class Calc implements Calculadora{
	public int soma(int a, int b){
		System.out.println("JOE "+a+" "+b+" "+(a+b));
		return a+b;
	}
	public int subtracao(int a, int b){
		System.out.println(a+" "+b+" "+(a-b));
		return a-b;
	}
	public int mult(int a, int b){
		System.out.println(a+" "+b+" "+(a*b));
		return a*b;
	}
	public int div(int a, int b){
		System.out.println(a+" "+b+" "+(a/b));
		return a/b;
	}
}