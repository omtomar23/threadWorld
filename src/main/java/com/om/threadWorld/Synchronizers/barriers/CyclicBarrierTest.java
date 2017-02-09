package com.om.threadWorld.Synchronizers.barriers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CyclicBarrierTest 
{
	public static void main(String[] args)
	{
		ScoreBoard board = new ScoreBoard();
		CustomCycleBarrier barrier = new CustomCycleBarrier(2, ()->{
			
			System.out.println("Reached...."+ board);
		});
		Thread t1 = new Thread(new CycleRider("A", barrier, board));
		Thread t2 = new Thread(new CycleRider("B", barrier, board));
		t1.start();
		t2.start();
		
	}
}

class ScoreBoard
{
	private Map<String, Long> map;
	public ScoreBoard()
	{
		map = new ConcurrentHashMap<String, Long>();
	}
	
	void updateScore(String id, long timeTaken)
	{
		map.put(id, timeTaken);
	}

	@Override
	public String toString() {
		return "ScoreBoard [map=" + map + "]";
	}
}

class CycleRider implements Runnable
{
	private String id;
	private CustomCycleBarrier barrier;
	private ScoreBoard scoreBoard;
	public CycleRider(String id, CustomCycleBarrier barrier, ScoreBoard scoreBoard)
	{
		this.id = id;
		this.barrier = barrier;
		this.scoreBoard = scoreBoard;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try 
			{
				int timeTaken = 1000;
				if(id.equals("A"))
				{
					timeTaken = 500;
				}
				Thread.sleep(timeTaken);
				scoreBoard.updateScore(id, timeTaken);
				barrier.await();
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "CycleRider [id=" + id + "]";
	}
}
