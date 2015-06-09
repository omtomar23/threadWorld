package com.om.threadWorld.threadpool;

import com.om.threadWorld.CommonTools;
import com.om.threadWorld.Synchronizers.latches.CustomeLatche;

public class Job implements Runnable
{
	private int jobId;
	private CustomeLatche latche;
	
	public Job(int jobId, CustomeLatche latche)
	{
		this.jobId = jobId;
		this.latche = latche;
	}
	
	@Override
	public void run() 
	{
		while(true)
		{
			System.out.println(this + " run by-"+Thread.currentThread().getName());
			CommonTools.sleep(10);
			latche.countDown();		
		}
	}
	
	@Override
	public String toString()
	{
		return "Job="+ jobId;
	}
}
