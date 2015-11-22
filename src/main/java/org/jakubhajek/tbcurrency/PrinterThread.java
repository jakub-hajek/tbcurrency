package org.jakubhajek.tbcurrency;



/**
 * Thread that periodically prints State given in constructor
 * 
 * @author jakub.hajek
 *
 */
public class PrinterThread extends Thread {

	private final long period;
	private final org.jakubhajek.tbcurrency.State state;
	

	@Override
	public void run()  {
		while (true) {
			try {
				Thread.sleep(period);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			state.print(System.out);
		}
		
	}

	PrinterThread(long period, org.jakubhajek.tbcurrency.State state) {
		super();
		this.period = period;
		this.state = state;
	}



}
