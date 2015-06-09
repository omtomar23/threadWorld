package com.om.threadWorld.cache;

import com.om.threadWorld.CommonTools;

public class CacheTest 
{
	private Memoizer<Integer, Long> memoizer;
	public CacheTest( Memoizer<Integer, Long> memoizer)
	{
		this.memoizer = memoizer;
	}
	
	Runnable runnable1 = new Runnable() 
	{
		@Override
		public void run() 
		{
			int i = 0;
			while(true)
			{
				try 
				{
					System.out.println(memoizer.compute(++i));
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				CommonTools.sleep(1000);
			}
		}
	};
	
	Runnable runnable2 = new Runnable() 
	{
		@Override
		public void run() 
		{
			int i = 0;
			while(true)
			{
				try 
				{
					System.out.println(memoizer.compute(++i));
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				CommonTools.sleep(1000);
			}
		}
	};
	
	Runnable runnable3 = new Runnable() 
	{
		@Override
		public void run() 
		{
			int i = 0;
			while(true)
			{
				try 
				{
					System.out.println(memoizer.compute(++i));
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				CommonTools.sleep(1000);
			}
		}
	};
	
	private void init()
	{
		new Thread(runnable1, "T1").start();
		new Thread(runnable2, "T2").start();
		new Thread(runnable3, "T3").start();
		
	}
	
	public static void main(String[] args) throws InterruptedException 
	{
		Computable<Integer, Long> expensiveFunction = new FactorialGenerator();
		Memoizer<Integer, Long> memoizer = new Memoizer<Integer, Long>(expensiveFunction);
		
		new CacheTest(memoizer).init();
		
		int i = 0;
		while(true)
		{
			try 
			{
				System.out.println(memoizer.compute(++i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			CommonTools.sleep(1000);
		}
		
	}
}
