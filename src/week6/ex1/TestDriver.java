package week6.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class TestDriver {

	int[][] map = new int[8][8];
	private final int MAX_THREAD = 4;
	List<Future<int[][]>> futList = new ArrayList<Future<int[][]>>(MAX_THREAD);
	List<Integer> startXList = new ArrayList<Integer>(MAX_THREAD);
	List<Integer> startYList = new ArrayList<Integer>(MAX_THREAD);
	int subMapYSize = 4;
	int subMapXSize = 4;
	public static void main(String[] args) {
		int[][] map = new int[8][8];
		TestDriver test= new TestDriver();
		map = test.initMap();
		test.printMap();
		ExecutorService pool = Executors.newFixedThreadPool(test.MAX_THREAD);
		test.submitJob(pool);
		System.out.println("AFTER :");
		test.getJob();
		test.printMap();
		System.out.println("Time taken for the task : " +  System.currentTimeMillis());
	}
	
	private int[][] initMap(){
		
		for (int y = 0; y < map.length ; y++) 
			for (int x = 0; x < map[y].length; x ++)
				map[y][x]=ThreadLocalRandom.current().nextInt(0, 101);
		return map;
	}
	
	private void submitJob(ExecutorService pool) {
		int counterList= 0;
		futList.add(pool.submit(new ConvertInt(getSubMap(0, 0 , subMapYSize, subMapXSize))));
		futList.add(pool.submit(new ConvertInt(getSubMap(0, 4 , subMapYSize, subMapXSize))));
		futList.add(pool.submit(new ConvertInt(getSubMap(4, 0 , subMapYSize, subMapXSize))));
		futList.add(pool.submit(new ConvertInt(getSubMap(4, 4 , subMapYSize, subMapXSize))));
	}
	
	private void getJob() {
		int counterList = 0;
			try {
				fillSubMap(0, 0 , subMapYSize, subMapXSize, futList.get(0).get());
				fillSubMap(0, 4 , subMapYSize, subMapXSize, futList.get(1).get());
				fillSubMap(4, 0 , subMapYSize, subMapXSize, futList.get(2).get());
				fillSubMap(4, 4 , subMapYSize, subMapXSize, futList.get(3).get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		
	}
	
	private int[][] getSubMap(int startingY, int startingX ,int subMapYSize, int subMapXSize) {
		int[][] subMap = new int[subMapYSize][subMapXSize];
		
		for (int y = 0; y < subMapYSize ; y++) 
			for (int x = 0; x < subMapXSize; x ++) {
				subMap[y][x] = map[startingY + y][startingX + x];
			}
		
		return subMap;
	}
	
	private void fillSubMap(int startingY, int startingX, int subMapYSize, int subMapXSize, int[][] subMap) {
		for (int y = 0; y < subMapYSize ; y++) 
			for (int x = 0; x < subMapXSize; x ++)
				map[startingY + y][startingX + x] = subMap[y][x]; 
	}
	
	private void printMap(){
		for (int y = 0; y < map.length ; y++) {
			for (int x = 0; x < map[y].length; x ++){
				System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
	}
	
}
