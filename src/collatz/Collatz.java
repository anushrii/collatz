package collatz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Collatz {
	static int[] simpleComputeSequenceLengths(long n){
		int[] ret_array = new int[(int)n+1];
		
		for(int i=1 ; i<ret_array.length; i++){
			
			ret_array[i] =lengthOfSequence(i);
			}
		
		return ret_array;
	}
	
	static int[] memoizedComputeSequenceLengths(long n){
		int[] ret_array = new int[(int)n+1];
		for(int i=1;i<ret_array.length;i++){
			ret_array[i] = getSequenceLength(i,ret_array);
		}
		
		return ret_array;
	}
	
	static int getSequenceLength(long n,int[] memoizedArray){

		long original = n;
		if(n>Integer.MAX_VALUE)
		{
			System.out.println("Invalid arguements, array size exceeded.");
			return 0;
		}
		int count = 1;
		while(n!=1){
			if (n%2==1){
				if (n<original){
					count = count + memoizedArray[(int)n] -1;
					break;
				}
				else{
				n = 3*n + 1;
				count++;
				}
			}
			else {
				if (n<original){
					count = count + memoizedArray[(int)n] -1;
					break;
				}
				else{
				n = n/2;
				count++;
				}
			}
		}
		return count;
		
	}
	
	static int lengthOfSequence(long n){
		if(n>Integer.MAX_VALUE)
		{
			System.out.println("Invalid arguements, array size exceeded.");
			return 0;
		}
		int count = 1;
		while(n!=1){
			if (n%2==1){
				n = 3*n + 1;
				count++;
			}
			else {
				n = n/2;
				count++;
			}
		}
		return count;	
	}
	static long collatz_1(long n){
		if (n%2==1)
			n = 3*n + 1;
		else
			n = n/2;
		return n;
	}
	
	static List<Long> sequenceOf(long n){
		List<Long> returnList = new ArrayList<Long>();
		while(n!=1){
			returnList.add(n);
			
			if (n%2==1){
				n = 3*n + 1;
				
			}
			else {
				n = n/2;
				
			}
		}
		returnList.add(1L);
		return returnList;
	}
	
	static long largestValueInSequence(long n){
		List<Long> list = sequenceOf(n);
		return Collections.max(list);
	}
	
	static List<Pair<Long, Integer>> equalLengthTwins(long lo, long hi){
		
		List<Pair<Long,Integer>> pairedList = new ArrayList<Pair<Long,Integer>>();
		if(lo == hi){
			pairedList.add(new Pair<Long,Integer>(lo,lengthOfSequence(lo)));
			return pairedList;
		}
		
		for (long i=lo;i<=hi;i++)
		{
			int length_a = lengthOfSequence(i);
			
				Pair<Long,Integer> p1 = new Pair<Long,Integer>(i,length_a);
				pairedList.add(p1);
 			
		}
		
		Set<Pair<Long,Integer>> list = new LinkedHashSet<Pair<Long,Integer>>();
		
		for (int i = 0; i < pairedList.size()-1;i++){
			Pair<Long,Integer> p1 = pairedList.get(i);
			Pair<Long,Integer> p2 = pairedList.get(i+1);
			if(p1._2() == p2._2()){
				list.add(p1);
				list.add(p2);
			}
		}
		return new ArrayList<Pair<Long,Integer>>(list);
	}
	
	static List<Pair<Long, Long>> equalMaxValueTwins(long lo, long hi){
		List<Pair<Long,Long>> pairedList = new ArrayList<Pair<Long,Long>>();
		if(hi==lo){
			pairedList.add(new Pair<Long,Long>(lo, largestValueInSequence(lo)));
			return pairedList;
		}
		for (long i=lo;i<hi;i++){
			long max_a = largestValueInSequence(i);
			Pair<Long,Long> p1 = new Pair<Long,Long>(i,max_a);
			pairedList.add(p1);
		}
		
		Set<Pair<Long,Long>> list = new LinkedHashSet<Pair<Long,Long>>();
				
		for (int i = 0; i < pairedList.size()-1;i++){
			Pair<Long,Long> p1 = pairedList.get(i);
			Pair<Long,Long> p2 = pairedList.get(i+1);
			if(p1._2() == p2._2()){
			list.add(p1);
			list.add(p2);
			}
		}
		return new ArrayList<Pair<Long,Long>>(list);
	}
	
	static int[] occurrences(long n, int counts){
		int[] array = new int[counts+1];
		for(int i=1;i<=counts;i++){
			for(long j=1; j<=n;j++){
				
				List<Long> list = sequenceOf(j);
				
				if(list.contains(new Long(i)))
					array[i]++;  
			}
				
		}
		return array;
			
	}
	
	static int commonLength(long n1 , long n2){
		Set<Long> set1 = new HashSet<Long>(sequenceOf(n1));
		Set<Long> set2 = new HashSet<Long>(sequenceOf(n2));
		
		set1.retainAll(set2);
		return set1.size();
	}
	
	
	
	static void doTimings(long n){
		System.gc();
		long startTime = System.nanoTime();
		for(int i=0;i<100;i++){
			simpleComputeSequenceLengths(n);
		}
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("Average time required for simpleComputeSequenceLength "+(elapsedTime/100)+ " ns." );
		
		System.gc();
		startTime = System.nanoTime();
		for(int i=0;i<100;i++){
			memoizedComputeSequenceLengths(n);
		}
		elapsedTime = System.nanoTime() - startTime;
		System.out.println("Average time required for memoisedComputeSequenceLength "+(elapsedTime/100)+ " ns." );
		
		
	}
	
	
	public static void main(String arg[]){

		for(long i=100000L;i<=1000000;i=i+100000){
			doTimings(i);
		}
		
		for(int i=1;i<100;i++){
			System.out.println(i+": length \t"+lengthOfSequence(i)+", max\t"+largestValueInSequence(i)+": "+sequenceOf(i).toString().replace("[","").replace("]", ""));
		}
		
		System.out.println("Equal Length Twins :"+equalLengthTwins(1L,100L));
		System.out.println("Equal Max value Twins :"+equalMaxValueTwins(1L,100L));
		System.out.println("Occurrences :"+Arrays.toString(occurrences(1000000L, 100)));
		System.out.println("Common Length :" +commonLength(59L, 666L));
	}
}


class Pair<A, B> {
    private A first;
    private B second;
    
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
    public A _1() { return first; }
    public B _2() { return second;}
    @Override public String toString() { return "(" + first + ", " + second + ")"; }
}



