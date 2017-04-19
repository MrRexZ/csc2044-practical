package week4.ex2;

public class BankTestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankAccount bAcc = new BankAccount();
		Thread thread1 = new Thread(new Person(bAcc, "John"));
		Thread thread2 = new Thread(new Person(bAcc, "Joe"));
		Thread thread3 = new Thread(new Person(bAcc, "Rick"));
		thread1.start();
		thread2.start();
		thread3.start();
		
		
		try {
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finished execution");
	}

}
