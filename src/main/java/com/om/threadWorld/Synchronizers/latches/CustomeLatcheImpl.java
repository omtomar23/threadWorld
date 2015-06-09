package com.om.threadWorld.Synchronizers.latches;

public class CustomeLatcheImpl implements CustomeLatche
{
	private final int count;
	private volatile int counter = 0;
	private Object mutex = new Object();
	
	public CustomeLatcheImpl(int count)
	{
		if(count < 0)
			throw new IllegalArgumentException();
		this.count = count;
	}

	@Override
	public void await() throws InterruptedException 
	{
		synchronized (mutex) 
		{
			if(!isLatcheExpired())
			{
				while(count > counter)
				{
					mutex.wait();
				}
			}
		}
	}

	private boolean isLatcheExpired()
	{
		return counter >= count;
	}
	
	@Override
	public void countDown() 
	{
		synchronized (mutex) 
		{
			if(count !=0)
				counter++;
			
			if(count == counter)
			{
				mutex.notifyAll();
			}
		}
	}

}
