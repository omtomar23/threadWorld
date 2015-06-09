package com.om.threadWorld.join;


public class LoginManager 
{
	private volatile boolean isValidUser = false;
	
	private static Thread thread1;
	private static Thread thread2;
	
	Runnable loginTask = new Runnable()
	{
		@Override
		public void run() 
		{
			System.out.println(Thread.currentThread().getName()+ " -Enter isValidUser="+ isValidUser);

			try 
			{
				thread2.join();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName()+ " -Leave isValidUser="+ isValidUser);
		}
		
	};
	
	Runnable authenticateUser = new Runnable()
	{
		@Override
		public void run() 
		{
			System.out.println(Thread.currentThread().getName()+ " -Enter");
			isValidUser = true;
			System.out.println(Thread.currentThread().getName()+ " -Leave isValidUser="+ isValidUser);
		}
		
	};
	
	public static void main(String[] args) 
	{
		LoginManager loginManager = new LoginManager();
		thread1 = new Thread(loginManager.loginTask, "Login Thread");
		thread2 = new Thread(loginManager.authenticateUser, "Authentication Thread");
		thread1.start();
		thread2.start();
	}


}
