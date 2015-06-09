package com.om.threadWorld.escape.byInnerClass;

public class Test 
{
	public static void main(String[] args)
	{
		EventSource eventSource = new EventSource();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				new ThisEscape(eventSource);
			}
		}, "T1").start();
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				eventSource.notifyEvent(null);
			}
		}, "T2").start();
	}
}
