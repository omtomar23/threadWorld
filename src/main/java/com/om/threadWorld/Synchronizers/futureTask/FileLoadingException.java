package com.om.threadWorld.Synchronizers.futureTask;

public class FileLoadingException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public FileLoadingException()
	{
		this("Problem in file Loading");
	}
	
	public FileLoadingException(String message)
	{
		super(message);
	}
}
