package com.om.threadWorld.queue;

import java.util.concurrent.BlockingQueue;

import com.om.threadWorld.CommonTools;
import com.om.threadWorld.Data;

public class Producer implements Runnable
{
	private BlockingQueue<Data<Integer>> blockingQueue;

	public Producer(BlockingQueue<Data<Integer>>  queue) 
	{
		this.blockingQueue = queue;
	}
	
	@Override
	public void run() 
	{
		int  i = 0;
		while(true)
		{
			try
			{
				System.out.println("Produce-"+ ++i);
				blockingQueue.put(new Data<Integer>(i));
				CommonTools.sleep(1000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
