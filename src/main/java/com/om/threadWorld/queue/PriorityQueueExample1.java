package com.om.threadWorld.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.om.threadWorld.CommonTools;

public class PriorityQueueExample1 
{
	private PriorityQueue<PriorityTask> tasks;
	private ExecutorService executorService;

	private PriorityQueueExample1()
	{
		tasks = new PriorityQueue<PriorityTask>(5, new TaskComparator());
		executorService = Executors.newFixedThreadPool(1, CommonTools.threadFactoryWithName("Task Runner Thread"));
	}
	
	private void executeTask()
	{
		executorService.execute(new Runnable() 
		{
			@Override
			public void run()
			{
				System.out.println("Job Ids="+tasks);
				PriorityTask priorityTask = tasks.remove();
				priorityTask.getTask().run();
			}
		});
	}
	
	Runnable runnable1 = new Runnable() {

		@Override
		public void run() 
		{
			int i = 100;
			while(true)
			{
				i = i-1;
				Job job = new Job(i);
				tasks.add( new PriorityTask(job.getJobRank(), job));
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
				CommonTools.sleep(1000);
				executeTask();
			}
		}
	};
	
	private void init()
	{
		new Thread(runnable1, "T1").start();
		new Thread(runnable2, "T2").start();
	}
	
	public static void main(String[] args) {
		new PriorityQueueExample1().init();
	}
	
	

}

class PriorityTask
{
	private final int rank;
	private final Runnable task;
	
	public PriorityTask(int rank, Runnable task)
	{
		this.rank = rank;
		this.task = task;
	}
	
	public int getRank() 
	{
		return rank;
	}
	
	public Runnable getTask() 
	{
		return task;
	}
	
	@Override
	public String toString() 
	{
		return String.valueOf(rank);
	}
}

class TaskComparator implements Comparator<PriorityTask>
{
	@Override
	public int compare(PriorityTask task1, PriorityTask task2) 
	{
		return Integer.compare(task1.getRank(), task2.getRank());
	}
}

class Job implements Runnable
{
	private final int jobRank;
	
	public Job(int rank)
	{
		this.jobRank = rank;
	}
	public int getJobRank() 
	{
		return jobRank;
	}

	@Override
	public void run() 
	{
		System.out.println("Running Job Id="+ jobRank);
	}
}
