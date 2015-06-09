package com.om.threadWorld.waitNotify.producerConsumer;

import com.om.threadWorld.CommonTools;

public class Producer implements Runnable
{
	private TaskSyncManager taskSyncManager;
	
	public Producer(TaskSyncManager taskSyncManager)
	{
		this.taskSyncManager = taskSyncManager;
	}

	@Override
	public void run() 
	{
		int i = 0;
		while(true)
		{
			try 
			{
				taskSyncManager.writeData(new DataPacket(String.valueOf(++i)));
				CommonTools.sleep(100);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
}
