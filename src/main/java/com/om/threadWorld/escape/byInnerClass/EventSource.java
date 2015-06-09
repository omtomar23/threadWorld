package com.om.threadWorld.escape.byInnerClass;

import com.om.threadWorld.CommonTools;

public class EventSource 
{
	private EventListener eventListener;
	public void registerListener(EventListener eventListener)
	{
		this.eventListener = eventListener;
	}
	
	public void notifyEvent(Event e)
	{
		System.out.println(Thread.currentThread().getName());
		CommonTools.doLongTask(999);
		if(eventListener != null)
			eventListener.onEvent(e);
	}
}
