package com.om.threadWorld;

public class Problem2 
{
	private final Object mutex = new Object();
	private int id;
	String[][] DATA = {{"A","JAY"},
						{"B","MATA"}, 
						{"C","DI"}
					  };
	class Task implements Runnable
	{
		@Override
		public void run() 
		{
			while(true)
			{
				synchronized (mutex) 
				{
					while(!Thread.currentThread().getName().equals(DATA[id][0]))
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
					
					System.out.println(Thread.currentThread().getName()+" ="+DATA[id][1]);
					id++;
					if(id == DATA.length)
					{
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
		Problem2 problem1 = new Problem2();
		Task task = problem1.new Task();
		Thread t1 = new Thread(task, "A");
		Thread t2 = new Thread(task, "B");
		Thread t3 = new Thread(task, "C");
		t1.start();
		t2.start();
		t3.start();
	}
}
