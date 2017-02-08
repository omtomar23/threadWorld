package com.om.threadWorld.concurrency.lock;

public class ILockImpl implements ILock
{
	private Thread lockedBy = null;
	private int lockCount = 0;
	
	public synchronized void lock() throws InterruptedException 
	{
		while(lockCount > 0 && Thread.currentThread() != lockedBy)
		{
			wait();
		}
		lockCount++;
		lockedBy = Thread.currentThread();
	}

	public synchronized void unlock() 
	{
		if(lockCount > 0 && Thread.currentThread() == lockedBy)
		{
			lockCount--;
		}
		if(lockCount == 0)
		{
			lockedBy = null;
			notifyAll();
		}
	}
}
