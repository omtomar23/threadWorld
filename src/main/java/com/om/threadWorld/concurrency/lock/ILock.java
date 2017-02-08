package com.om.threadWorld.concurrency.lock;

public interface ILock 
{
	void lock()throws InterruptedException ;
	void unlock();
}
