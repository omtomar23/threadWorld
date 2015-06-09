package com.om.threadWorld.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @param <A>
 * @param <V>
 */
public class Memoizer<A, V> implements Computable<A, V> 
{
	private final Map<A, FutureTask<V>> cache = new ConcurrentHashMap<A, FutureTask<V>>();
	private final Computable<A, V> c;

	public Memoizer(Computable<A, V> c)
	{ 
		this.c = c;
	}

	public V compute(A arg) throws InterruptedException 
	{
		FutureTask<V> result = cache.get(arg);
		if (result == null) 
		{
			FutureTask<V> futureTask = new FutureTask<V>( new Callable<V>() 
			{
				@Override
				public V call() throws Exception 
				{
					return c.compute(arg);
				}

			});
			result = cache.putIfAbsent(arg, futureTask);
			if(result == null)
			{
				result = futureTask;
				futureTask.run();
			}
		}
		try 
		{
            return result.get();
        } catch (CancellationException e)
		{
            cache.remove(arg, result);
            throw new RuntimeException(e.getMessage());
        } 
		catch (ExecutionException e)
		{
            throw new RuntimeException(e.getMessage());
        }

	}
}

