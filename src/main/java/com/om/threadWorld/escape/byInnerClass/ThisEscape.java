package com.om.threadWorld.escape.byInnerClass;

import com.om.threadWorld.CommonTools;

public class ThisEscape
{
	private String name;
	
    public ThisEscape(EventSource source) 
    {
    	System.out.println(Thread.currentThread().getName());
        source.registerListener(
            new EventListener()
            { 
                public void onEvent(Event e) 
                {
                    doSomething(e);
                }
            });
        //Tasking some time to perform some heavily task. 
        CommonTools.doLongTask(9999);
        this.name = "Om";
    }
    
    private void doSomething(Event e)
    {
    	System.out.println("name="+this.name + " ,ref="+this);
    }
    
    @Override
    public String toString() {
    	return name;
    }
}


