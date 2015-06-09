package com.om.threadWorld.Synchronizers.barriers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.om.threadWorld.CommonTools;

public class Tour 
{
	private CyclicBarrier barrier;
	
	private static final String[] PLACE_NAMES = {"A", "B"};
	
	private static int placeIndex = 0;

	public Tour(CyclicBarrier barrier)
	{
		this.barrier = barrier;
	}
	
	private void await()
	{
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	Runnable runnable1 = new Runnable()
	{
		@Override
		public void run() 
		{ 
			System.out.println(Thread.currentThread().getName() + " has been reached to- "+ PLACE_NAMES[0]);
			await();
			
			CommonTools.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " has been reached to- "+ PLACE_NAMES[1]);
			placeIndex++;
			await();
			
		}
	};
	
	Runnable runnable2 = new Runnable()
	{
		@Override
		public void run() 
		{
			CommonTools.sleep(3000);
			System.out.println(Thread.currentThread().getName() + " has been reached to- "+ PLACE_NAMES[0]);
			await();
			
			CommonTools.sleep(2000);
			System.out.println(Thread.currentThread().getName() + " has been reached to- "+ PLACE_NAMES[1]);
			await();
		}
	};
	
	private void init()
	{
		new Thread(runnable1, "T1").start();
		new Thread(runnable2, "T2").start();
	}
	
	public static void main(String[] args)
	{
		CyclicBarrier  barrier = new CyclicBarrier(2, new Runnable() {

			@Override
			public void run() 
			{
				System.out.println("They have been meet to-"+ PLACE_NAMES[placeIndex]);			
			}
		});

		new Tour(barrier).init();
	}
}
