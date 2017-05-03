package week6.ex2;

import java.util.concurrent.Callable;

public class FindMaxNum implements Callable<Integer> {
	int[] subArray;
	public FindMaxNum(int[] numList){
		subArray = numList;
	}
	
	public Integer call() {
		int max=0;
		for (int a : subArray) 
			if (a > max) 
				max = a;
		return max;
	}

}
