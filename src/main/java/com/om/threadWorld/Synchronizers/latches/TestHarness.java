package com.om.threadWorld.Synchronizers.latches;

import java.util.concurrent.CountDownLatch;

import com.om.threadWorld.CommonTools;

public class TestHarness 
{
    private long timeTasks(int nThreads, final Runnable task) throws InterruptedException 
    {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) 
        {
            Thread t = new Thread() 
            {
                public void run() 
                {
                    try
                    {
                        startGate.await();
                        try
                        {
                            task.run();
                        } 
                        finally
                        {
                            endGate.countDown();
                        }
                    }
                    catch (InterruptedException ignored)
                    { 
                    	
                    }
                }
            };
            t.start();
        }

        long start = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long end = System.currentTimeMillis();
        return end-start;
    }
    
    Runnable runnable = new Runnable() 
    {	
		@Override
		public void run() 
		{
			CommonTools.sleep(1000);
		}
	};
    
	private void start() throws InterruptedException
	{
		System.out.println(timeTasks(100, runnable)/1000);
	}
    public static void main(String[] args) throws InterruptedException 
    {
		new TestHarness().start();
	}
}