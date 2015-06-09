package com.om.threadWorld;

/**
 * Base class which have command method
 */
public class BaseThread extends Thread
{	
	public BaseThread(String name) 
	{
		super(name);
	}

	protected void ajoin()
	{
		try 
		{
			join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	protected synchronized void await(long millis) 
	{
		try 
		{
			this.wait(millis);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void await(Object mutex) 
	{
		synchronized (mutex) 
		{
			try 
			{
				System.out.println(Thread.currentThread().getName() +" -going wating");
				mutex.wait();
				System.out.println(Thread.currentThread().getName() +" -wake up");
			} 
			catch (InterruptedException e) 
			{
				System.out.println(Thread.currentThread().getName() + " exception=");
				e.printStackTrace();
			}
		}
	}
}
