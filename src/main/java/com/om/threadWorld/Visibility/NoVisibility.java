package com.om.threadWorld.Visibility;

/**
 *The main thread starts the reader thread and then sets 'number' to 42 and 'ready' to true. The reader thread spins until 
 *it sees 'ready' is true, and then prints out 'number'. While it may seem obvious that NoVisibility will print 42, 
 *it is in fact possible that it will print zero, or never terminate at all! Because it does not use adequate synchronization,
 * there is no guarantee that the values of 'ready' and 'number' written by the main thread will be visible to the reader thread.
 * 
 * To avoid these complex issues: always use the proper synchronization whenever data is shared across threads.
 */
public class NoVisibility
{
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread 
    {
        public void run()
        {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}

