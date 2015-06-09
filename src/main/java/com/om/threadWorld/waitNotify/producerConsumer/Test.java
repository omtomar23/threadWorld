package com.om.threadWorld.waitNotify.producerConsumer;

public class Test
{
	public static void main(String[] args)
	{
		TaskSyncManager taskSyncManager = new TaskSyncManager();
		Producer producer = new Producer(taskSyncManager);
		Consumer consumer = new Consumer(taskSyncManager);
		
		Thread producerThread = new Thread(producer, "ProducerThread");
		Thread consumerThread = new Thread(consumer, "ConsumerThread");
		
		producerThread.start();
		consumerThread.start();
		
		
	}
}
