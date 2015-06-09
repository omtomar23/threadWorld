package com.om.threadWorld.waitNotify;

import com.om.threadWorld.BaseThread;
import com.om.threadWorld.CommonTools;

/**
 * When two thread is on waiting state following action perform
 * 1. Interrupt on thread
 * 2. Notify call
 * One thread comes out by interruption and anther thread comes out by notify, then both will run again.
 * If this behavior not handle the some dire consequences could be occur.
 */
public class InterruptNotifyProblem extends BaseThread
{
	private Thread t1;
	private Thread t2;
	private Object mutex = new Object();
	private volatile boolean isIntrupted = false;
	
	InterruptNotifyProblem()
	{
		super("");
	}
	
	class Task implements Runnable
	{
		@Override
		public void run() 
		{
			System.out.println(Thread.currentThread().getName() +" -start");
			
			while(!Thread.currentThread().isInterrupted())
			{
				process();
				
				//Thread 'T1' goes first time in waiting state , after interrupt or notify then, we force it should no go again
				if(Thread.currentThread().getName().equals("T1") && !isIntrupted)
					await(mutex);
				
				CommonTools.sleep(1000);
			}
			
			System.out.println(Thread.currentThread().getName() +" -stop");
			
		}
	}
	
	private void process()
	{
		synchronized (mutex) 
		{
			System.out.println(Thread.currentThread().getName() +" -execute");
		}
	}
	
	public static void main(String[] args) 
	{
		InterruptNotifyProblem notifyProblem = new InterruptNotifyProblem();
		
		Task task = notifyProblem.new Task();
		
		notifyProblem.t1 = new Thread(task, "T1");
		notifyProblem.t2 = new Thread(task, "T2");
		
		notifyProblem.t1.start();
		notifyProblem.t2.start();
		
		//Wait main thread be 3 seconds.
		notifyProblem.await(3000);
		
		//
		notifyProblem.t1.interrupt();
		notifyProblem.isIntrupted = true;
		
		synchronized (notifyProblem.mutex) 
		{
			notifyProblem.mutex.notify();
		}
		
	}
}
