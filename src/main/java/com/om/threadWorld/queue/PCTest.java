package com.om.threadWorld.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.om.threadWorld.Data;

public class PCTest 
{ 
	public static void main(String[] args) 
	{
		BlockingQueue<Data<Integer>> blockingQueue = new ArrayBlockingQueue<Data<Integer>>(1);
		System.out.println(blockingQueue.size());
		
		Producer producer = new Producer(blockingQueue);
		Consumer consumer = new Consumer(blockingQueue);
		
		new Thread(producer, "Producer-Thread").start();
		new Thread(consumer, "Consumer-Thread").start();
	}
}
