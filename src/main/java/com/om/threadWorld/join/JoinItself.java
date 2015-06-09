package com.om.threadWorld.join;

import com.om.threadWorld.BaseThread;


public class JoinItself extends BaseThread 
{
	JoinItself(String name)
	{
		super(name);
	}
	
	@Override
	public void run()
	{
		System.out.println(Thread.currentThread().getName());
		this.ajoin();
		System.out.println("------CONTROL WILL NOT COME HERE--------");
	}

	public static void main(String[] args) 
	{
		JoinItself task = new JoinItself("CheckerThread");
		task.start();
	}
}
