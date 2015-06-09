package com.om.threadWorld.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Memoizer2 certainly has better concurrent behavior than Memoizer1: 
 * multiple threads can actually use it concurrently. But it still has some defects as a cachethere 
 * is a window of vulnerability in which two threads calling compute at the same time could end up computing the same value. 
 * The problem with Memoizer2 is that if one thread starts an expensive computation, other threads are not aware that the 
 * computation is in progress and so may start the same computation
 * @param <A>
 * @param <V>
 */
public class Memoizer2<A, V> implements Computable<A, V> 
{
	private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
	private final Computable<A, V> c;

	public Memoizer2(Computable<A, V> c)
	{ 
		this.c = c;
	}

	public V compute(A arg) throws InterruptedException 
	{
		V result = cache.get(arg);
		if (result == null) 
		{
			result = c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}
}

