package com.om.threadWorld.queue;

import java.util.concurrent.BlockingQueue;

import com.om.threadWorld.CommonTools;
import com.om.threadWorld.Data;

public class Consumer implements Runnable
{
	private BlockingQueue<Data<Integer>> blockingQueue;
	
	public Consumer(BlockingQueue<Data<Integer>>  queue) 
	{
		this.blockingQueue = queue;
	}
	
	@Override
	public void run() 
	{
		while(true)
		{
			try 
			{
				System.out.println("Consume-"+blockingQueue.take().getValue());
				CommonTools.sleep(1000);
		
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
