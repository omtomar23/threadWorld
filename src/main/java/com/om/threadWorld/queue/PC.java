package com.om.threadWorld.queue;

import com.om.threadWorld.CommonTools;
import com.om.threadWorld.Data;

public class PC
{
	public static void main(String[] args) 
	{
		CustomeBlockingQueue<Data<Integer>> blockingQueue = new CustomeBlockingQueue<Data<Integer>>(2);
		
		Provider producer = new Provider(blockingQueue);
		Tacker consumer = new Tacker(blockingQueue);
		
		new Thread(producer, "Producer-Thread").start();
		new Thread(consumer, "Consumer-Thread").start();
	}
}

class Provider implements Runnable
{
	private CustomeBlockingQueue<Data<Integer>> blockingQueue;

	public Provider(CustomeBlockingQueue<Data<Integer>>  queue) 
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
				blockingQueue.add(new Data<Integer>(i));
				CommonTools.sleep(100);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}

class Tacker implements Runnable
{
	private CustomeBlockingQueue<Data<Integer>> blockingQueue;

	public Tacker(CustomeBlockingQueue<Data<Integer>>  queue) 
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
				System.out.println("Consume-"+blockingQueue.get().getValue());
				CommonTools.sleep(1000);

			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}