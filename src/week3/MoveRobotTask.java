package week3;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MoveRobotTask implements Runnable {
	
	private int[][] map;
	private int robotPosX=0;
	private int robotPosY=0;
	private Tuple[] robotsLoc = new Tuple[3];
	private int counter;
	
	
	public void moveRobot(int deltaX, int deltaY) {
		synchronized(map) {
			if (robotsNotOutOfBounds(robotPosX + deltaX, robotPosY + deltaY) && robotsLoc != null) {
				performOperationsOnPreviousLoc();
				robotPosX += deltaX;
				robotPosY += deltaY;
				if (counter <= 2) {
					robotsLoc[counter] = new Tuple(robotPosX, robotPosY);
				}
				robotsLoc[counter % 3].changePos(robotPosX, robotPosY);
				counter++;
				map[robotPosY][robotPosX] = counter;
			}
		}
	}
	
	public void performOperationsOnPreviousLoc() {
		if (counter > 2) {
			Tuple existingCoor = robotsLoc[counter % 3];
			map[existingCoor.existingY][existingCoor.existingX] = 0;
		}
	}
	
	
	public int getCurIndexOfPos() {
		return counter % 3 ;
	}
	
	public MoveRobotTask(int[][] mapCoor) {
		map = mapCoor;
		map[robotPosY][robotPosX] = 0;
	}
	
	public boolean robotsNotOutOfBounds(int newPosX, int newPosY) {
		return newPosX >= 0 && newPosX < 8 
				&& newPosY >= 0 && newPosY < 8 && notExistingPath(newPosX,newPosY);
		
	}
	
	private boolean notExistingPath(int newPosX,int newPosY) {
		for (int i = 0 ; i < robotsLoc.length ; i++ ) {
			if (robotsLoc[i] != null){
				if (getCurIndexOfPos() != i && robotsLoc[i].existingX == newPosX && robotsLoc[i].existingY == newPosY) 
					return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				int direction = ThreadLocalRandom.current().nextInt(0, 4);
				Thread.sleep(600);
				switch (direction) {
					case 0:
						moveRobot(-1,0); //move left
						break;
					case 1:
						moveRobot(0,1); //move up
						break;
					case 2:
						moveRobot(1,0); //move right
						break;
					case 3:
						moveRobot(0,-1); //move down
						break;
				}
				
				//This line below is for single-threaded environment debugging.
				//displayMap();
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void displayMap() {
		for (int y = 0; y < map.length ; y++) {
			for (int x = 0; x < map[y].length ; x++) {
				System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println("==============");
		System.out.println();
	}
	
	
	//Helper class 
	class Tuple {
		int existingX;
		int existingY;
		public Tuple(int existingX, int existingY) {
			this.existingX = existingX;
			this.existingY = existingY;
		}
		
		public void changePos(int posX, int posY) {
			existingX = posX;
			existingY = posY;
		}
		
		
		
	}
}
