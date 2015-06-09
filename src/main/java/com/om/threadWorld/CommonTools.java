package com.om.threadWorld;

import java.util.concurrent.ThreadFactory;

public class CommonTools
{
	private CommonTools(){}
	
	public static void sleep(long millis)
	{
		try 
		{
			Thread.sleep(millis);
		}
		catch (InterruptedException e) 
		{
			//Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}
	
	public static void doLongTask(long value)
	{
		int i = 0;
		while(++i< value)
		{}
	}
	
	public static ThreadFactory threadFactoryWithName(final String name)
    {
        return new ThreadFactory()
        {
            @Override
            public Thread newThread(Runnable r)
            {
                return new Thread(r, name);
            }
        };
    }
}
