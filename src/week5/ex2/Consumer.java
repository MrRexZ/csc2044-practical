package week5.ex2;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {

	private BlockingQueue q;
	private final int maxQueueSize = 10;

	public Consumer(BlockingQueue qRef) {
		q = qRef;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (q.size() == 0)
				System.out.println("Queue is empty");
			while (q.size() == 0) {
			}
			
			int queueObj = 0;
			try {
				queueObj = (int) q.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Queue object " + queueObj + " removed : "
					+ q.toString());
		}
	}
}
