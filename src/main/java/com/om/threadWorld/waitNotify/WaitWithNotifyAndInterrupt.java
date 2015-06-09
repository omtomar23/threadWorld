package com.om.threadWorld.waitNotify;


/**
 * When a thread 'T1' is in waiting state and another thread 'T2' does call notify and interrupt method then this thread 'T1' 
 * then following this could be happen.
 * 1. Thread 'T1' comes out with Interrupted exception from its waiting pool and eligible to run again.
 * 	  Thread 'T1' method isInterrupted shall be return false.
 * 
 * 2. Thread 'T1' comes out without Interrupted exception from its waiting pool and eligible to run again.
 * 	  Thread 'T1' method isInterrupted shall be return true.
 * 
 * But if we call notifyAll first then interrupt then every time thread interrupt status set to 'TRUE'
 * vice versa not true, some time set some time throw exception
 */
public class WaitWithNotifyAndInterrupt
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
					await();
				}
				
				System.out.println(Thread.currentThread().getName() +" -send message");
			}
			System.out.println(Thread.currentThread().getName() +" interrupted status=" + t1.isInterrupted());
			System.out.println(Thread.currentThread().getName()+" stoped");
			
		}

		private void await() 
		{
			synchronized (mutex) 
			{
				try 
				{
					while(!isConnectionEstablish)
					{
						mutex.wait();	
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
			t1.interrupt();
			synchronized (mutex) 
			{
				System.out.println("NotifyAll called by "+Thread.currentThread().getName());
				isConnectionEstablish = true;
				mutex.notifyAll();
			}
			
		}
		
	}
	
	public static void main(String[] args) 
	{
		WaitWithNotifyAndInterrupt test = new WaitWithNotifyAndInterrupt();
		
		test.t1 = new Thread(test.new Task1(), "T1");
		test.t2 = new Thread(test.new Task2(), "T2");
		
		test.t1.start();
		test.t2.start();
		
	}
}

