package week4.ex2;

public class BankAccount {
	private double saving=100;
	
	public void deposit(double value) {

		try {
			Thread.sleep(5);
			saving += value;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double getSaving() {
		return saving;
	}
}
