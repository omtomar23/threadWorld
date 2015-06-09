package com.om.threadWorld.cache;


public class FactorialGenerator implements Computable<Integer, Long> 
{
    public Long compute(Integer num) 
    {
    	System.out.println("compute-Enter/Leave ,num="+ num);
        return fact(num);
    }
    
    private long fact(int num)
    {
    	if(num <= 1)
    		return num;
    	
    	return num > 1 ? num*fact(num-1) : fact(num);
    }
}
