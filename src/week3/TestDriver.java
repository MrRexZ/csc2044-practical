package week3;

public class TestDriver {

	public static void main(String[] args) {
		int[][] map = new int[8][8];
		DisplayMapTask displayMapTask = new DisplayMapTask(map);
		MoveRobotTask moveRobotTask = new MoveRobotTask(map);
		Thread displayMap = new Thread(displayMapTask);
		Thread moveRobot = new Thread(moveRobotTask);
		displayMap.start();
		moveRobot.start();
	}

}
