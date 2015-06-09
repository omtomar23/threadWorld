package com.om.threadWorld.threadSafeWithDataRace;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Synchronize list is a thread safe class but this class create compound statement by take synchronize of list
 * which leads data race condition.
 */
public class SynListTest 
{
	private List<String> names = Collections.synchronizedList(new LinkedList<String>());
	
	public void add(String name)
	{
		names.add(name);
	}
	
	public String removeFirst() 
	{
		if (names.size() > 0)
		{
			return (String) names.remove(0);
		}
		else
		{
			return null;
		}
	}
	
	public static void main(String[] args)
	{
	    final SynListTest synListTest = new SynListTest();
	    synListTest.add("Ozymandias");
	    
	    class NameDropper extends Thread
	    {
	        public void run() 
	        {
	            String name = synListTest.removeFirst();
	            System.out.println(name);
	        }
	    }
	    Thread t1 = new NameDropper();
	    Thread t2 = new NameDropper();
	    t1.start();
	    t2.start();
	}
}
