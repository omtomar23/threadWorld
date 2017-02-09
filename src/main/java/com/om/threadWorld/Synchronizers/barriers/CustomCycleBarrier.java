package com.om.threadWorld.Synchronizers.barriers;


public class CustomCycleBarrier 
{
	private int parties;
	private Object mutex;
	public int count;
	private Runnable runnable;
	private Gen gen;
	public CustomCycleBarrier(int parties, Runnable runnable)
	{
		this.parties = parties;
		this.runnable = runnable;
		mutex = new Object();
		gen = new Gen();
	}
	
	class Gen
	{
		
	}
	
	private void checkBarrier(Gen localGen) throws InterruptedException
	{
		synchronized (mutex)
		{
			while(count < parties)
			{
				if(localGen != gen)
					break;
				mutex.wait();
			}
			
		}
	}
	
	public void await() throws InterruptedException
	{
		synchronized (mutex)
		{
			Gen localGen = gen;
			count++;
			if(count == parties)
			{
				runnable.run();
				mutex.notifyAll();
				count = 0;
				gen = new Gen();
			}
			checkBarrier(localGen);
		}
	}
}
