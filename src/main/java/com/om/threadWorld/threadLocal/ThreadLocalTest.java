package com.om.threadWorld.threadLocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest 
{
	private static final AtomicInteger nextId = new AtomicInteger(0);
	private ThreadLocal<Integer> threadLocal;
	
	private ThreadLocalTest()
	{
		threadLocal = new ThreadLocal<Integer>()
		{
			protected Integer initialValue()
			{
				return nextId.getAndIncrement();
			}
		};
	}
	
	Runnable runnable1 = new Runnable() {
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+ " ,value="+threadLocal.get());
			System.out.println(Thread.currentThread().getName()+ " ,value="+threadLocal.get());
		}
	};
	
Runnable runnable2 = new Runnable() {
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+ " ,value="+threadLocal.get());
			System.out.println(Thread.currentThread().getName()+ " ,value="+threadLocal.get());
		}
	};
	
	private void init()
	{
		new Thread(runnable1, "T1").start();
		new Thread(runnable1, "T2").start();
	}
	
	public static void main(String[] args) 
	{
		new ThreadLocalTest().init();
	}
}
