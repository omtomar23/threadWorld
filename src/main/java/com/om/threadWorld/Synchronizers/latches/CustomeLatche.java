package com.om.threadWorld.Synchronizers.latches;

public interface CustomeLatche
{
	void await()throws InterruptedException;
	
	void countDown();
}
