import static org.junit.Assert.*;
import org.junit.Test;


public class ComputeTest 
{
	GraphicInterface gi = new GraphicInterface();
	Compute com = new Compute();
	
	@Test
	public void testCalculate() //Testing the base functional of the interpreter 
	{
		
		assertEquals(-1,com.calculate("(-1)"),0);
		assertEquals(0.5,com.calculate("2^(-1)"),0);
		assertEquals(-2,com.calculate("2/(-1)"),0);
		assertEquals(-2,com.calculate("2*(-1)"),0);
		assertEquals(1,com.calculate("2+(-1)"),0);
		assertEquals(3,com.calculate("2-(-1)"),0);
		
	}
	
	@Test
	public void testClaculateSum() //Testing function sum
	{
		assertEquals(1,com.calculate("sum(1)"),0);
	}
	
	@Test
	public void testClaculateMin() //Testing function min
	{
		assertEquals(1,com.calculate("min(7,2,4.5,3-2)"),0);
	}
	
	@Test
	public void testClaculateMax() //Testing function max
	{
		assertEquals(32,com.calculate("max(4,6,3/2,2^(3+2))"),0);
	}
	
	@Test
	public void testClaculateSqrt() //Testing function sqrt
	{
		assertEquals(4,com.calculate("sqrt(16)"),0);
	}
	
	@Test
	public void testIsCorrect() //Testing isCorrect function
	{
		assertEquals(1,gi.isCorrect("2(2+3)"),0);
		assertEquals(3,gi.isCorrect("2*(+3)"),0);
		assertEquals(1,gi.isCorrect("2(2+3)"),0);
		assertEquals(6,gi.isCorrect("2*sut()"),0);
		assertEquals(7,gi.isCorrect("2*(2+3)4"),0);
		assertEquals(7,gi.isCorrect("2*(2+3)4"),0);
		assertEquals(5,gi.isCorrect("2*()(("),0);
		assertEquals(4,gi.isCorrect("2^()))"),0);
	}
}
