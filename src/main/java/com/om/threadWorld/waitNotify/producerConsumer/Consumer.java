package com.om.threadWorld.waitNotify.producerConsumer;

import com.om.threadWorld.CommonTools;

public class Consumer implements Runnable
{
	private TaskSyncManager taskSyncManager;
	
	public Consumer(TaskSyncManager taskSyncManager)
	{
		this.taskSyncManager = taskSyncManager;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				taskSyncManager.readData();
				CommonTools.sleep(100);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

};
