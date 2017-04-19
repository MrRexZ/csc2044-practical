package week4.ex2;

public class Person implements Runnable {

	private BankAccount account;
	private String pName;
	private int counter = 0;
	
	
	public Person(BankAccount accRef, String personName) {
		account = accRef;
		pName = personName;
	}
	
	public void run() {
		while (counter++ <50) {
		synchronized(account) {
			account.deposit(10);
			try {
				Thread.sleep(5);
				System.out.println("Hello ,"+  pName + ". The account currently contains : " + account.getSaving());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		
	}
	
}
