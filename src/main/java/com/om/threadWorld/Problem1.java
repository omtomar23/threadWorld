package com.om.threadWorld;

public class Problem1 
{
	private Object mutex = new Object();
	private int id;
	private static int counter = 1;
	String[] names = {"A", "B","C"};
	
	class Task implements Runnable
	{
		@Override
		public void run() 
		{
			while(true)
			{
				synchronized (mutex) 
				{
					while(!Thread.currentThread().getName().equals(names[id]))
					{
						try 
						{
							mutex.wait();
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
					
					System.out.println(Thread.currentThread().getName()+" ="+counter);
					id++;
					if(id == names.length)
					{
						counter++;
						id = 0;
						System.out.println("---------------------------");
						asleep(1000);
					}
					mutex.notifyAll();
				}
			}
		}

		private void asleep(long mills) {
			try {
				Thread.sleep(mills);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) 
	{ 
		Problem1 problem1 = new Problem1();
		Task task = problem1.new Task();
		Thread t1 = new Thread(task, "A");
		Thread t2 = new Thread(task, "B");
		Thread t3 = new Thread(task, "C");
		t1.start();
		t2.start();
		t3.start();
	}
}
