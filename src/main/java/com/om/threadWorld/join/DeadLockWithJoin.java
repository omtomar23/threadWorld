package com.om.threadWorld.join;

import com.om.threadWorld.CommonTools;

/**
 * Task1 joins Task2 and Task2 joins Task1 so dead lock shall be arise because Task1 will not precede further until Task2 does not complete,
 * and Task2 will not precede further until Task1 does not complete.
 */
public class DeadLockWithJoin
{
	private static volatile boolean shutdownCalled = false;

	private static Thread thread1;
	private static Thread thread2;
	
	Runnable task1 = new Runnable()
	{
		@Override
		public void run() 
		{
			System.out.println(Thread.currentThread().getName()+ " -Enter");
			
			while(!shutdownCalled)
			{
				try 
				{
					thread2.join();
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				CommonTools.sleep(1000);
			}
			System.out.println(Thread.currentThread().getName()+ " -Leave");
		}
		
	};
	
	Runnable task2 = new Runnable()
	{
		@Override
		public void run() 
		{
			System.out.println(Thread.currentThread().getName()+ " -Enter");
			try 
			{
				thread1.join();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName()+ " -Leave");
		}
		
	};
	
	public static void main(String[] args) 
	{
		DeadLockWithJoin jobRunner = new DeadLockWithJoin();
		thread1 = new Thread(jobRunner.task1, "T1");
		thread2 = new Thread(jobRunner.task2, "T2");
		thread1.start();
		thread2.start();
	}

}
