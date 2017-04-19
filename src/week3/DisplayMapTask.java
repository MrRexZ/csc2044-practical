package week3;

public class DisplayMapTask implements Runnable {

	private int[][] passedMap;
	public DisplayMapTask(int[][] map) {
		passedMap = map;
	}
	@Override
	public void run() {
		
		
		while(true) {
			try {
				Thread.sleep(300);
				synchronized(passedMap) {
					displayMap();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void displayMap() {
		for (int y = 0; y < passedMap.length ; y++) {
			for (int x = 0; x < passedMap[y].length ; x++) {
				System.out.print(passedMap[y][x] + " ");
			}
			System.out.println();
		}
		

		System.out.println("==============");
		System.out.println();
	}
	
}
