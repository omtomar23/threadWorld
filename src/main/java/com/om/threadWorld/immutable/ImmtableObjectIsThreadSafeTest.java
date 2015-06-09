package com.om.threadWorld.immutable;

import com.om.threadWorld.CommonTools;
 
public class ImmtableObjectIsThreadSafeTest 
{
	volatile String name = "";
	
	Runnable runnable1 = new Runnable()
	{
		@Override
		public void run() 
		{
			while(true)
			{
				System.out.println(Thread.currentThread().getName()+" -"+ name);
				CommonTools.sleep(100);
			}
		}
	};
	
	Runnable runnable2 = new Runnable()
	{
		@Override
		public void run() 
		{
			int i = 0;
			while(true)
			{
				name = "#"+String.valueOf(++i);
				System.out.println(Thread.currentThread().getName()+" -"+ name);
				CommonTools.sleep(100);
			}
		}
	};
	
	public static void main(String[] args) 
	{
		ImmtableObjectIsThreadSafeTest a = new ImmtableObjectIsThreadSafeTest();
		new Thread(a.runnable1, "Task1").start();
		new Thread(a.runnable2, "Task2").start();
	}
}
