package week6.ex1;

import java.util.concurrent.Callable;

public class ConvertInt implements Callable<int[][]> {
	private int[][] subMap;
	public ConvertInt(int[][] subArea){
		subMap= subArea;
	}
	
	public int[][] call() {
		for (int y = 0 ; y < subMap.length ; y ++)
			for (int x = 0 ; x < subMap[y].length ; x++ )
				subMap[y][x] = subMap[y][x] % 2;
		return subMap;
	}

}
