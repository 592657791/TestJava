package com.an.java.TestJava;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 有一个银行,有五个受理窗口,接受15个客户办理业务
 * @author Administrator
 *
 */
public class TestThreadPool {
	public static void main(String[] args) throws Exception, ExecutionException {
		//TestThreadPool();
		//根据时间片调用
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
		ScheduledFuture<Integer> result = null;
		try {
			for(int i=1;i<=15;i++) {
				result = service.schedule(()->{
					System.out.print(Thread.currentThread().getName());
					return new Random().nextInt(30);
				}, 2, TimeUnit.SECONDS);
				System.out.println("-----result:"+result.get());
			}
		} finally {
			service.shutdown();
		}
		
	}

	private static void TestThreadPool() throws InterruptedException, ExecutionException {
		//创建五个的线程池
		//ExecutorService service = Executors.newFixedThreadPool(5);
		
		//创建单个线程池
		//ExecutorService service = Executors.newSingleThreadExecutor();
		
		//根据实际的情况自动创建线程池里的数目
		ExecutorService service = Executors.newCachedThreadPool();
		
		try {
			for(int i=1;i<=15;i++) {
				Integer result = service.submit(()->{
					System.out.print(Thread.currentThread().getName());
					return new Random().nextInt(30);
				}).get();
				
				System.out.println("-----result:"+result);
			}
		} finally {
			service.shutdown();
		}
	}
}


