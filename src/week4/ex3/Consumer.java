package week4.ex3;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {

	private Queue q;
	private ReentrantLock rLock;
	private final int maxQueueSize = 10;
	
	public Consumer(Queue qRef, ReentrantLock rLockRef) {
		q= qRef;
		rLock = rLockRef;
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(1200);
				rLock.lock();
				try {
					if (q.size() == 0) {
						System.out.println("Queue is empty");
					}
					else {
						int queueObj = (int) q.remove();
						System.out.println("Queue object removed : " + queueObj);
					}
				}
				finally {
					rLock.unlock();
				}
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
