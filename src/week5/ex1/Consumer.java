package week5.ex1;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {

	private Queue q;
	private final int maxQueueSize = 10;

	public Consumer(Queue qRef) {
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
			synchronized (q) {
				while (q.size() == 0) {
					System.out.println("Queue is empty");
					try {
						System.out.println("Consumer is waiting...");
						q.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				int queueObj = (int) q.remove();
				q.notifyAll();
				System.out.println("Queue object " + queueObj + " removed : "
						+ q.toString());
			}
		}
	}
}
