package week6.ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class TestDriverMaxNum {

	private final int ARRAY_SIZE = 10000;
	private final int MAX_THREAD = 2;
	private final int SUBARRAY_SIZE = ARRAY_SIZE/MAX_THREAD;
	int[] arrayInt= new int[ARRAY_SIZE];
	List<Future<Integer>> futList = new ArrayList<Future<Integer>>(MAX_THREAD);
	public static void main(String[] args) {
		TestDriverMaxNum test = new TestDriverMaxNum();
		for (int i = 0 ; i < test.arrayInt.length ; i++ ) {
			test.arrayInt[i] = ThreadLocalRandom.current().nextInt(0, 256);
		}
		ExecutorService pool = Executors.newFixedThreadPool(2);
		test.submitJob(pool);
		System.out.println(test.getJob(pool));

	}
	
	private void submitJob(ExecutorService pool) {
		for (int i = 0 ; i < MAX_THREAD ; i++){
			futList.add(pool.submit(new FindMaxNum(getArraySubsections(i))));
	
		}
	}
	
	private int getJob(ExecutorService pool) {
		int max = 0;
		for (int i = 0 ; i < MAX_THREAD ; i++){
			int curInt;
			try {
				curInt = futList.get(i).get();
				if (curInt > max)
					max = curInt;
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return max;
	}
	
	private int[] getArraySubsections(int index){
		
		int[] subArray = new int[SUBARRAY_SIZE];
		
		for (int start = 0 ; start < SUBARRAY_SIZE; start++) {
			subArray[start] = arrayInt[index + start];
		}
		
		return subArray;
	}

}
