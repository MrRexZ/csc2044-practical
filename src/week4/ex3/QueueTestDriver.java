package week4.ex3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class QueueTestDriver {

	public static void main(String[] args) {
		Queue queue = new LinkedList();
		ReentrantLock rLock = new ReentrantLock();
		Producer prod = new Producer(queue, rLock);
		Consumer cons = new Consumer(queue, rLock);
		prod.start();
		cons.start();
	}
}
