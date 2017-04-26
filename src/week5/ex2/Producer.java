package week5.ex2;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {

	private BlockingQueue q;
	private int numbersGenerated;
	private final int maxQueueSize = 10;

	public Producer(BlockingQueue qRef) {
		q = qRef;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				int generatedNum = ThreadLocalRandom.current().nextInt(1, 101);
				q.put(generatedNum);
				System.out.println("A number " + generatedNum
						+ " is added into the queue : " + q.toString());
				numbersGenerated += 1;

				while (numbersGenerated == 5) {
					System.out.println("Generated 5 numbers. Waiting...");
					Thread.sleep(5000);
					numbersGenerated = 0;
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
