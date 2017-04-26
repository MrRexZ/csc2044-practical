package week5.ex1;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {

	private Queue q;
	private int numbersGenerated;
	private final int maxQueueSize = 10;

	public Producer(Queue qRef) {
		q = qRef;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				synchronized (q) {
					int generatedNum = ThreadLocalRandom.current().nextInt(1,
							101);

					while (q.size() == maxQueueSize) {
						System.out.println("Queue is full");
						q.wait();
					}
					q.add(generatedNum);
					q.notifyAll();

					System.out.println("A number " + generatedNum
							+ " is added into the queue : " + q.toString());
					numbersGenerated += 1;
				}

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
