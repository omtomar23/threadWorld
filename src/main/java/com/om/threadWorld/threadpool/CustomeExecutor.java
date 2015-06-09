package com.om.threadWorld.threadpool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;

public class CustomeExecutor implements Executor
{
	private final static int DEFAULT_POOL_SIZE = 1;
	
	private final int poolSize;
	
	private Set<Worker> workers;
	
	private BlockingQueue<Runnable> jobs;
	
	private volatile ThreadFactory threadFactory;
	
	public CustomeExecutor()
	{
		this(DEFAULT_POOL_SIZE);
	}
	public CustomeExecutor(int poolSize)
	{
		if(poolSize <= 0 )
			throw new IllegalArgumentException("pool size ivvalid");
		this.poolSize = poolSize;
		workers = new HashSet<Worker>();
		jobs = new LinkedBlockingQueue<Runnable>();
		threadFactory = Executors.defaultThreadFactory();
	}
	
	@Override
	public void execute(Runnable command)
	{
		try 
		{
			jobs.put(command);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		if(workers.size() < poolSize)
		{
			Worker worker = new Worker();
			workers.add(worker);
			worker.start();
		}
	}
	
	class Worker implements Runnable
	{
		private volatile boolean stop = false;
		private Thread currentThread;
		private Worker()
		{
			currentThread = threadFactory.newThread(this);
		}
		
		@Override
		public void run() 
		{
			try 
			{
				while(!stop)
				{
					Runnable task = jobs.take();
					task.run();
				}
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		public void start()
		{
			currentThread.start();
		}
		
		public void stop()
		{
			this.stop = true;
			currentThread.interrupt();
		}
	}
	
	public void terminate()
	{
		for (Worker worker :workers) 
		{
			worker.stop();
		}
	}
}
