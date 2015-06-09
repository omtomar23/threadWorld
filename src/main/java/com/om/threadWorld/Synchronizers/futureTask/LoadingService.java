package com.om.threadWorld.Synchronizers.futureTask;

import java.io.File;
import java.util.concurrent.FutureTask;

import com.om.threadWorld.Data;

public interface LoadingService 
{
	FutureTask<Data<File>> loadFile(String fileName);
}
