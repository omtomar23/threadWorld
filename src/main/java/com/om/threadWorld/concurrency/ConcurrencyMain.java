package com.om.threadWorld.concurrency;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrencyMain 
{
	static final int spread(int h) {
        return (h ^ (h >>> 16)) & 0x7fffffff;
    }
	public static void main(String[] args) throws InterruptedException 
	{
		ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<String, Integer>();
		chm.put("A", 1);
		chm.put("B", 2);
		chm.put("C", 3);
		chm.put("D", 4);
		System.out.println("A"+ " ,hashCode="+"A".hashCode()+" ,segmentIndex="+spread("A".hashCode()));
		System.out.println("B"+ " ,hashCode="+"B".hashCode()+" ,segmentIndex="+spread("B".hashCode()));
		System.out.println("C"+ " ,hashCode="+"C".hashCode()+" ,segmentIndex="+spread("C".hashCode()));
		System.out.println("D"+ " ,hashCode="+"D".hashCode()+" ,segmentIndex="+spread("D".hashCode()));
		for (Entry<String, Integer> entry : chm.entrySet())
		{
			
			chm.remove("B");
			Thread.sleep(1000);
			System.out.println(entry);
		}
	}
}
