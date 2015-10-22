//import junit.framework.*;
public class CalculadoraJunit extends TestCase{

	public void testSoma(){
		Integer val1 = 10;
		Integer val2 = 5;
		Integer soma = 15;
		Calc calc = new Calc();
		
		int somaFeito =  calc.soma(val1, val2);

		assertEquals(soma, somaFeito, 0);
	}
}