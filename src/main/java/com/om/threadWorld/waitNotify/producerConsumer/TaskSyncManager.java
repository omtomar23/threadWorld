package com.om.threadWorld.waitNotify.producerConsumer;

import java.util.concurrent.CopyOnWriteArrayList;

public class TaskSyncManager 
{
	private static final int MAX_NUMBER_OF_DATA_PACKETS_ALLOWED = 10;
	private CopyOnWriteArrayList<DataPacket> dataBuffer;
	
	private Object writerMutex;
	private Object rederMutex;
	
	TaskSyncManager()
	{
		dataBuffer = new CopyOnWriteArrayList<DataPacket>();
		writerMutex = new Object();
		rederMutex = new Object();
		
		new Thread( new WriterWatcher(), "WriterWatherThread").start();
		new Thread(new DataChangeListener(), "DataChangeListenerThread").start();
	}
	
	
	public void writeData(DataPacket dataPacket) throws InterruptedException
	{
		synchronized (writerMutex)
		{
			if(dataBuffer.size() >= MAX_NUMBER_OF_DATA_PACKETS_ALLOWED)
			{
				writerMutex.wait();
			}
			dataBuffer.add(dataPacket);
			System.out.println("Produce-"+ dataBuffer);
		}
	}
	
	class WriterWatcher implements Runnable
	{
		@Override
		public void run() 
		{
			while(true)
			{
				if(dataBuffer.size() < MAX_NUMBER_OF_DATA_PACKETS_ALLOWED / 2)
				{
					notifyWriterThread();
				}
				synchronized (this) 
				{
					try 
					{
						this.wait(10);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	private void notifyWriterThread()
	{
		synchronized (writerMutex) 
		{
			writerMutex.notifyAll();
		}
	}
	
	class DataChangeListener implements Runnable
	{
		@Override
		public void run() 
		{
			while(true)
			{
				if(dataBuffer.size() > 0)
				{
					notifyReaderThread();
				}
				synchronized (this) 
				{
					try 
					{
						this.wait(10);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	private void notifyReaderThread()
	{
		synchronized (rederMutex) 
		{
			rederMutex.notifyAll();
		}
	}
	
	public void readData() throws InterruptedException
	{
		synchronized (rederMutex)
		{
			if(dataBuffer.size() == 0)
			{
				rederMutex.wait();
			}
			
			String message = dataBuffer.remove(0).getMessage();
			System.out.println("Consume-"+ message);
		}
	}
}
