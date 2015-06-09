package com.om.threadWorld.Synchronizers.semaphores;

import java.util.concurrent.Semaphore;

import com.om.threadWorld.CommonTools;

public class BinarySemaphoreTest 
{
	private Semaphore semaphore;
	
	BinarySemaphoreTest(Semaphore semaphore)
	{
		this.semaphore = semaphore;
	}
	
	Runnable runnable1 = new Runnable() 
	{
		@Override
		public void run() 
		{
			try {
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName());
				CommonTools.sleep(1000);
				semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	Runnable runnable2 = new Runnable() 
	{
		@Override
		public void run() 
		{
			try {
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName());
				CommonTools.sleep(1000);
				semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	private void init()
	{
		new Thread(runnable1, "T1").start();
		new Thread(runnable2, "T2").start();
	}
	public static void main(String[] args) 
	{
		Semaphore semaphore = new Semaphore(1);
		
		new BinarySemaphoreTest(semaphore).init();
	}
}
