package week5.ex2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class QueueTestDriver {

	public static void main(String[] args) {
		BlockingQueue queue = new LinkedBlockingQueue(10);
		Producer prod = new Producer(queue);
		Consumer cons = new Consumer(queue);
		prod.start();
		cons.start();
	}
	
}
