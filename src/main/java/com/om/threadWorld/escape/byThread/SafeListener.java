package com.om.threadWorld.escape.byThread;

import com.om.threadWorld.escape.byInnerClass.Event;
import com.om.threadWorld.escape.byInnerClass.EventListener;
import com.om.threadWorld.escape.byInnerClass.EventSource;

public class SafeListener 
{
    private final EventListener listener;

    private SafeListener()
    {
        listener = new EventListener() {
            public void onEvent(Event e) {
                //doSomething(e);
            }
        };
    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }
}

