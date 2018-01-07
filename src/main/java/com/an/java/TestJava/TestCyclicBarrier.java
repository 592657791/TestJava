package com.an.java.TestJava;

import java.util.concurrent.CyclicBarrier;

/**
 * 他会让一组线程到大屏障,直到最后一个线程到大屏障时,屏障才会打开,
 * 所有被屏障拦截的线程才会继续干活.
 * 集齐7颗龙珠就可以召唤神龙
 * @author Administrator
 */
public class TestCyclicBarrier {
	public static void main(String[] args) {
		
		//CyclicBarrier cyclicBarrier = new CyclicBarrier(7);
		
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()-> {
			System.out.println("*******集齐七龙珠,召唤神龙....");
		});
		
		for(int i=1;i<=7;i++) {
			int tempI = i;
			new Thread(()->{
				try {
					System.out.println(Thread.currentThread().getName()+"---收集龙珠"+tempI);
					cyclicBarrier.await();
				}catch (Exception e) {
					// TODO: handle exception
				}finally {
				}
			}, String.valueOf(i)).start();
		}
	}
}
