package com.om.threadWorld.list;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.om.threadWorld.CommonTools;

public class CopyOnWriteArrayListExample2 
{
	private List<String> list;

	private CopyOnWriteArrayListExample2()
	{
		list = new CopyOnWriteArrayList<String>();
	}

	private void addName(String name)
	{
		list.add(name);
	}
	
	private void printf()
	{
		System.out.println(list);
	}

	Runnable runnable1 = new Runnable() {

		@Override
		public void run() 
		{
			while(true)
			{
				addName("A");
				CommonTools.sleep(100);
			}
		}
	};

	Runnable runnable2 = new Runnable() {

		@Override
		public void run() 
		{
			while(true)
			{
				printf();
				CommonTools.sleep(10);
			}
		}
	};
	
	private void init()
	{
		new Thread(runnable1, "T1").start();
		new Thread(runnable2, "T2").start();
	}
	
	public static void main(String[] args) {
		new CopyOnWriteArrayListExample2().init();
	}
}
