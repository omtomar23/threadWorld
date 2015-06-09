package com.om.threadWorld.Synchronizers.latches;

import com.om.threadWorld.CommonTools;

public class PopulationCounterManager 
{
	private CustomeLatche latche;
	private volatile long totalPopulation;
	
	public PopulationCounterManager(CustomeLatche latche)
	{
		this.latche = latche;
	}
	
	public long getTotalPopulation()
	{
		return totalPopulation;
	}
	
	Runnable runnable1 = new Runnable() 
	{
		
		@Override
		public void run() 
		{
			totalPopulation += 10;
			CommonTools.sleep(1000);
			latche.countDown();
		}
	};
	
	Runnable runnable2 = new Runnable() 
	{
		@Override
		public void run() 
		{
			totalPopulation += 50;
			CommonTools.sleep(2000);
			latche.countDown();
		}
	};
	
	private void init()
	{
		new Thread(runnable1, "T1").start();
		new Thread(runnable2, "T2").start();
	}
	public static void main(String[] args) throws InterruptedException 
	{
		CustomeLatche latche = new CustomeLatcheImpl(2);
		PopulationCounterManager populationCounterManager = new PopulationCounterManager(latche);
		System.out.println("Population counting start...");
		populationCounterManager.init();
		latche.await();
		System.out.println("Total population="+populationCounterManager.getTotalPopulation());
		
	}
}
