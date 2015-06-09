package com.om.threadWorld.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap is not thread-safe, so to ensure that two threads do not access the HashMap at the same time, 
 * Memoizer1 takes the conservative approach of synchronizing the entire compute method. 
 * This ensures thread safety but has an obvious scalability problem: only one thread at a time can execute compute at all. 
 * If another thread is busy computing a result, other threads calling compute may be blocked for a long time.
 * If multiple threads are queued up waiting to compute values not already computed, compute may actually take longer 
 * than it would have without memorization.
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer1<A, V> implements Computable<A, V>
{
    private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) 
    {
        this.c = c;
    }

    public synchronized V compute(A arg) throws InterruptedException 
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
