package com.om.threadWorld.yield;

public class YieldTest 
{
	Runnable runnable1 = new Runnable() 
	{
		@Override
		public void run() 
		{
			while(true)
			{
				System.out.println(Thread.currentThread().getName());
				Thread.yield();
			}
		}
	};
	
	Runnable runnable2 = new Runnable() 
	{
		@Override
		public void run() 
		{
			while(true)
			{
				System.out.println(Thread.currentThread().getName());
				Thread.yield();
			}
		}
	};
	
	public static void main(String[] args) 
	{
		
		YieldTest yieldTest = new YieldTest();
		
		new Thread(yieldTest.runnable1, "T1").start();
		new Thread(yieldTest.runnable2, "T2").start();
		
	}
}
