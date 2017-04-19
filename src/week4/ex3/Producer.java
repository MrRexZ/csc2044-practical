package week4.ex3;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {

	private Queue q;
	private ReentrantLock rLock;
	private int numbersGenerated;
	private final int maxQueueSize = 10;
	
	
	public Producer(Queue qRef, ReentrantLock rLockRef) {
		q=qRef;
		rLock =  rLockRef;
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				int generatedNum = ThreadLocalRandom.current().nextInt(1, 101);
			
				rLock.lock();
				try {
					if (q.size() == maxQueueSize) 
						System.out.println("A number is added into the queue");
					else {
						q.add(generatedNum);
						System.out.println("A number is added into the queue : " + generatedNum);
						numbersGenerated += 1;
					}
				}
				finally {
					rLock.unlock();
				}
				
				if (numbersGenerated == 5){
					numbersGenerated = 0;
					Thread.sleep(5000);
				}
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
