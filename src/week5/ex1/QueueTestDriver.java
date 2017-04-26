package week5.ex1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class QueueTestDriver {

	public static void main(String[] args) {
		Queue queue = new LinkedList();
		Producer prod = new Producer(queue);
		Consumer cons = new Consumer(queue);
		prod.start();
		cons.start();
	}
	
}
