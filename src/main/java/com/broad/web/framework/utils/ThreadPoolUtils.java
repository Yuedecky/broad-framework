package com.broad.web.framework.utils;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ThreadPoolUtils {
	/**
	 * 异步线程池
	 */
	private static ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();  
	static{
		//线程池所使用的缓冲队列  
		executor.setQueueCapacity(20000);  
		//线程池维护线程的最少数量  
		executor.setCorePoolSize(20);  
		//线程池维护线程的最大数量  
		executor.setMaxPoolSize(1500);  
		//线程池维护线程所允许的空闲时间  
		executor.setKeepAliveSeconds(30000);  
		executor.initialize();  
	}

	private ThreadPoolUtils(){}
	
	public static ThreadPoolTaskExecutor getThreadPoolTaskExecutor(){
		return executor;
	}
	
	public static ThreadPoolTaskExecutor getThreadPoolTaskExecutor(int size){
		ThreadPoolTaskExecutor newExecutor = new ThreadPoolTaskExecutor();  
		//线程池所使用的缓冲队列  
		newExecutor.setQueueCapacity(20000);
		//线程池维护线程的最少数量  
		newExecutor.setCorePoolSize(size);
		//线程池维护线程的最大数量  
		newExecutor.setMaxPoolSize(1500);
		//线程池维护线程所允许的空闲时间  
		newExecutor.initialize();
		return newExecutor;
	}
}
