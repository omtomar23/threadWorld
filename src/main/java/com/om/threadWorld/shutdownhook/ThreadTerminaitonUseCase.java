package com.om.threadWorld.shutdownhook;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadTerminaitonUseCase
{
	public static void main(String[] args) 
	{
		Thread thread = new Thread(()->{
			throw new RuntimeException("Problem in task runner");
		}, "Task Runner");

		thread.setUncaughtExceptionHandler(new HouseCleaner());
		thread.start();
	}
}




class HouseCleaner implements UncaughtExceptionHandler
{
	@Override
	public void uncaughtException(Thread t, Throwable e)
	{
		System.out.println(t.getName()+ " ,Problem="+ e);
	}
}