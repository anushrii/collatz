package collatz;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CollatzTest {

	@Test
	public void testSimpleComputeSequenceLength(){
		
		int[] expected = {0,1,2,8};
		int[] actual = Collatz.simpleComputeSequenceLengths(3);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testMemoizedComputeSequenceLength(){
		
		int[] expected = {0,1,2,8,3};
		int[] actual = Collatz.memoizedComputeSequenceLengths(4);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testCollatz_1(){
		long expected = 28;
		long actual = Collatz.collatz_1(9); 
		assertEquals(expected, actual);
	}
	
	@Test
	public void testlengthOfSequence(){
		long expected = 8;
		long actual = Collatz.lengthOfSequence(3); 
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSequenceOf(){
		long[] expected = {3,10,5,16,8,4,2,1};
		List<Long> list = Collatz.sequenceOf(3);
		long[] actual = new long[8];
		int i=0;
		for (long x:list){
			actual[i++]=x;
			}
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testLargestValueInSequence(){
		long expected = 16L;
		long actual = Collatz.largestValueInSequence(3);
		assertEquals(expected,actual);
	}
	
	@Test
	public void testeEqualLengthTwins(){
	
		String expected = "[(28, 19), (29, 19)]";
		String actual = Collatz.equalLengthTwins(28, 29).toString();
		assertEquals(expected, actual);

	}
	
	@Test
	public void testEqualMaxValueTwins(){
		
		String expected = "[(5, 16), (6, 16)]";
		String actual = Collatz.equalMaxValueTwins(4, 7).toString();
		assertEquals(expected, actual);

	}
	
	@Test
	public void testOccurrences(){
		
		int expected = 100;
		int actual = Collatz.occurrences(100,3)[1];
		assertEquals(expected, actual);

	}
	
	@Test
	public void testCommonLength(){
		
		int expected = 11;
		int actual = Collatz.commonLength(2048,682);
		assertEquals(expected, actual);

	}
}

