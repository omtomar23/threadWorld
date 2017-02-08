package com.om.threadWorld.concurrency.lock;

public interface IReadWriteLock
{
	void readLock()throws InterruptedException;
	void releaseReadLock();
	void writeLock()throws InterruptedException;
	void releaseWriteLock();
}
