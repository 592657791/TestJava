package com.an.java.TestJava;

import java.util.concurrent.CountDownLatch;

import com.an.java.enums.CountryEnums;

/**
 * 让一些阻塞,直到另一些线程全部完成,完成一系列后才被唤醒
 * 
 *  CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 * @author Administrator
 *
 */
public class TestCountDownLatch {
	public static void main(String[] args) throws Exception {
		CountDownLatch countDownLatch = new CountDownLatch(6);
		for(int i=1;i<=6;i++) {
			new Thread(()->{
				countDownLatch.countDown();
				System.out.println(Thread.currentThread().getName()+"\t"+"国被灭 ");
			},CountryEnums.getCountryEnums(i).getValue()).start();
		}
		countDownLatch.await();
		System.out.println("--------秦国统一------");
	
	}
}
