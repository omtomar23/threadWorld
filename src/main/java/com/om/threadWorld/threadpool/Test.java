package com.om.threadWorld.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.om.threadWorld.Synchronizers.latches.CustomeLatche;
import com.om.threadWorld.Synchronizers.latches.CustomeLatcheImpl;

public class Test 
{
	public static void main(String[] args) throws InterruptedException 
	{
		//Executor executor = new CustomeExecutor(100);
		Executor executor = Executors.newFixedThreadPool(1);
		CustomeLatche latche = new CustomeLatcheImpl(2);
		AtomicInteger atomicInteger = new AtomicInteger();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1;   i++)
		{
			executor.execute(new Job(atomicInteger.getAndIncrement(),latche));
		}
		latche.await();
		System.out.println("Time taken="+(System.currentTimeMillis()-startTime));
		System.out.println("Going to shutdown");
		((ExecutorService)executor).shutdownNow();
		System.out.println("Shutdown done="+ ((ExecutorService)executor).isShutdown());
		System.out.println("Shutdown done1="+ ((ExecutorService)executor).isTerminated());
	}
}
