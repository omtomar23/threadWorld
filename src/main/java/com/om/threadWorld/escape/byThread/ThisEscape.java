package com.om.threadWorld.escape.byThread;

import com.om.threadWorld.CommonTools;

public class ThisEscape 
{
	private String name;
		
	public ThisEscape()
	{
		System.out.println(Thread.currentThread().getName());
		new Thread(runnable, "T1").start();
		CommonTools.doLongTask(99);
		name = "Om";
	}
	
	Runnable runnable = new Runnable() {
		public void run() 
		{
			System.out.println(Thread.currentThread().getName());
			System.out.println(name);
		}
	};
	
	public static void main(String[] args)
	{
		new ThisEscape();
	}
}
