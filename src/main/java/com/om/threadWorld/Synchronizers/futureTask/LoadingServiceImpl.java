package com.om.threadWorld.Synchronizers.futureTask;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import com.om.threadWorld.CommonTools;
import com.om.threadWorld.Data;

public class LoadingServiceImpl implements LoadingService
{

	@Override
	public FutureTask<Data<File>> loadFile(String fileName) 
	{
		FutureTask<Data<File>> futureTask = new FutureTask<Data<File>>(new Callable<Data<File>>() 
		{		
			public Data<File> call() throws Exception
			{
				CommonTools.sleep(2000);
				//throw new FileLoadingException();
				System.out.println("runijng");
				return new Data<File>(new File("OM"));
			}
		});

		startLoading(futureTask);
		return futureTask;

	}

	private void startLoading(FutureTask<Data<File>> futureTask)
	{
		new Thread(futureTask, "Task-Executor").start();
	}
}
