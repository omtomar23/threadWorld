package com.om.threadWorld.concurrency.lock;

public class IReadWriteLockImpl implements IReadWriteLock
{
	private int readers = 0;
	private int writers = 0;
	private int writeRequests = 0;
	
	public synchronized void readLock() throws InterruptedException 
	{
		while(writeRequests >0 || writers > 0)
		{
			wait();
		}

		readers--;
	}

	public synchronized void releaseReadLock() 
	{
		if(readers > 0)
		{
			readers--;
		}
		
		if(readers == 0)
		{
			notifyAll();
		}
	}

	public synchronized void writeLock() throws InterruptedException
	{
		writeRequests++;
		while(writers > 0 || readers >0)
		{
			wait();
		}
		writeRequests--;
		writers--;
	}

	public synchronized void releaseWriteLock()
	{
		if(writers > 0)
		{
			writers--;
		}
		
		if(writers == 0)
		{
			notifyAll();
		}
	}
}
