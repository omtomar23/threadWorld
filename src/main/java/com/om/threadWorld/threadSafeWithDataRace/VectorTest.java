package com.om.threadWorld.threadSafeWithDataRace;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import com.om.threadWorld.CommonTools;

/**
 *This attempt at a put-if-absent operation has a race condition, even though both contains and add are atomic.
 * While synchronized methods can make individual operations atomic, additional locking is required when multiple
 * operations are combined into a compound action. 
 */
public class VectorTest implements Runnable
{
	private Vector<String> vector = new Vector<String>();
	private volatile boolean shouldBeRunAgain = true;
	
	public void putIfAbsent(String data)
	{
		if(!vector.contains(data))
		{
			vector.add(data);
		}
	}

	@Override
	public void run() 
	{
		AtomicInteger atomicInteger = new AtomicInteger();
		while(shouldBeRunAgain)
		{
			putIfAbsent(String.valueOf(atomicInteger.getAndIncrement()));
			CommonTools.sleep(100);
		}
	}
	
	public static void main(String[] args) throws InterruptedException 
	{
		VectorTest test = new VectorTest();
		new Thread(test, "T1").start();
		new Thread(test, "T2").start();
		
		synchronized (VectorTest.class) 
		{
			VectorTest.class.wait(1000);
		}
		
		test.shouldBeRunAgain = false;
		System.out.println(test.vector);
	}
	
}
