package com.om.threadWorld.queue;

import java.util.ArrayList;
import java.util.List;

public class CustomeBlockingQueue<E>
{
	private static final int DEFAULT_CAPACITY = 1;
	private final int capacity;
	private List<E> list;
	private Object mutex;
	private volatile int index = 0;
	
	public CustomeBlockingQueue()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public CustomeBlockingQueue(int capacity)
	{
		if(capacity <= 0 )
			throw new IllegalArgumentException(" size ivvalid");
		
		this.capacity = capacity;
		list = new ArrayList<E>(this.capacity);
		mutex = new Object();
	}
	
	public void add(E e) throws InterruptedException
	{
		synchronized (mutex) 
		{
			while(list.size()>= capacity)
			{
				mutex.wait();
			}
			list.add(index++,e);
			mutex.notifyAll();
		}
	}
	
	public E get() throws InterruptedException
	{
		synchronized (mutex) 
		{
			while(list.size() == 0)
			{
				mutex.wait();
			}
			E element = list.remove(--index);
			mutex.notifyAll();
			return element;
		}
	}
	
	public int size()
	{
		synchronized (mutex) 
		{
			return list.size();
		}
	}
}
