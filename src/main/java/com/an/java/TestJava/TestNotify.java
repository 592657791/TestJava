package com.an.java.TestJava;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 此时不能用synchronized实现对对某一个线程的调度,所以要用lock
 * 用Condition c = lock.newCondition()来实现
 * 
 * 备注：多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * 
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......来10轮
 * 
 */
public class TestNotify {
	public static void main(String[] args) {
		ShareData sd = new ShareData();
		new Thread(()->{
			for(int i=1;i<=10;i++) {
				try {
					sd.print5(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"A").start();
		
		new Thread(()->{
			for(int i=1;i<=10;i++) {
				try {
					sd.print10(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"B").start();
		
		new Thread(()->{
			for(int i=1;i<=10;i++) {
				try {
					sd.print15(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"C").start();
	}
}

class ShareData{
	
	int number = 1;
	Lock lock = new ReentrantLock();
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	Condition c3 = lock.newCondition();
	
	public void print5(int totalLoop) throws Exception {
		lock.lock();
		try {
			if(number != 1) {
				c1.await();
			}
			for(int i=1;i<=5;i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+"loop:"+totalLoop);
			}
			number = 2;
			c2.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void print10(int totalLoop) throws Exception {
		lock.lock();
		try {
			if(number != 2) {
				c2.await();
			}
			for(int i=1;i<=10;i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+"loop:"+totalLoop);
			}
			number = 3;
			c3.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void print15(int totalLoop) throws Exception {
		lock.lock();
		try {
			if(number != 3) {
				c3.await();
			}
			for(int i=1;i<=15;i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+"loop:"+totalLoop);
			}
			number = 1;
			c1.signal();
		} finally {
			lock.unlock();
		}
	}
	
}
