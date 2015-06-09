package com.om.threadWorld.waitNotify.producerConsumer;

public class DataPacket 
{
	private String message;
	
	DataPacket(String message)
	{
		this.message = message;
	}
	
	public String getMessage() 
	{
		return message;
	}
	
	@Override
	public String toString() 
	{
		return message;
	}
	
}
