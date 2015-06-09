package com.om.threadWorld.waitNotify;


/**
 * When a thread 'T1' is in waiting state and another thread 'T2' does call interrupt method then this thread 'T1' 
 * then thread 'T1' comes out with Interrupted exception from its waiting pool and eligible to run again.
 * Thread 'T1' method isInterrupted would return false.
 */
public class WaitWithInterrupt
{
	private Thread t1;
	private Thread t2;
	private Object mutex = new Object();
	private volatile boolean isConnectionEstablish;
	
	class Task1 implements Runnable
	{
		@Override
		public void run() 
		{
			while(!t1.isInterrupted())
			{
				if(!isConnectionEstablish)
				{
					System.out.println(Thread.currentThread().getName() +" waiting for connection");
					await(0, false);
				}
				
				System.out.println(Thread.currentThread().getName() +" -send message");
				
				await(1000, true);
			}
			
			System.out.println("thread stop");
			
		}

		private void await(int millis, boolean withTime) 
		{
			synchronized (mutex) 
			{
				try 
				{
					while(!isConnectionEstablish || withTime)
					{
						mutex.wait(millis);	
						withTime = false;
					}
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
					System.out.println("isInterrupted="+Thread.currentThread().isInterrupted());
				}
				
			}
		}
		
	}
	
	class Task2 implements Runnable
	{
		@Override
		public void run() 
		{
			isConnectionEstablish = true;
			t1.interrupt();
		}
		
	}
	
	public static void main(String[] args) 
	{
		WaitWithInterrupt test = new WaitWithInterrupt();
		
		test.t1 = new Thread(test.new Task1(), "T1");
		test.t2 = new Thread(test.new Task2(), "T2");
		
		test.t1.start();
		test.t2.start();
		
	}
}
