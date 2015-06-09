package com.om.threadWorld.Synchronizers.futureTask;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.om.threadWorld.CommonTools;
import com.om.threadWorld.Data;


public class LoadingTest 
{
	public static void main(String[] args) throws InterruptedException, ExecutionException 
	{
		LoadingService loadingService = new LoadingServiceImpl();
		FutureTask<Data<File>> futureTask = loadingService.loadFile("OM");
		System.out.println("Getting result");

		CommonTools.sleep(1000);
		
		System.out.println("File Name="+futureTask.get().getValue().getName());
		boolean cancel = futureTask.cancel(true);
		System.out.println("cancel="+ cancel);
	}
}
