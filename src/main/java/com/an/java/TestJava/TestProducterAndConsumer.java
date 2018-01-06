package com.an.java.TestJava;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者与消费者问题：使用四个线程
 * 比如A生成一个,B消费一个
 * 实现  初始值为0，线程A实现加1，线程B实现减1,循环30次,初始值还是0
 * 
 * 若在判断时,用if判断,会出现假唤醒的行为.
 * 
 * 使用Lock时,
 * @author Administrator
 *
 */
public class TestProducterAndConsumer {
	public static void main(String[] args) {
		MyPC pc = new MyPC();
		new Thread(()->{
			for(int i=1;i<=30;i++) {
				try {
					Thread.sleep(300);
					pc.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"A").start(); ;
		
		new Thread(()->{
			for(int i=1;i<=30;i++) {
				try {
					Thread.sleep(300);
					pc.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"B").start(); ;
		
		new Thread(()->{
			for(int i=1;i<=30;i++) {
				try {
					Thread.sleep(300);
					pc.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"C").start(); ;
		
		new Thread(()->{
			for(int i=1;i<=30;i++) {
				try {
					Thread.sleep(300);
					pc.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"D").start(); ;
	}
}

class MyPC{
	int number = 0;
//	public synchronized void increment() throws Exception {
//		while(number != 0) {
//			this.wait();
//		}
//		number++;
//		System.out.println(Thread.currentThread().getName()+"\t"+number);
//		this.notifyAll();
//	}
//	
//	public synchronized void decrement() throws Exception {
//		while(number == 0) {
//			this.wait();
//		}
//		number--;
//		System.out.println(Thread.currentThread().getName()+"\t"+number);
//		this.notifyAll();
//	}
	
	//使用Lock
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public void increment() throws Exception {
		lock.lock();
		Condition condition = lock.newCondition();
		try {
			while(number != 0) {
				condition.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void decrement() throws Exception {
		lock.lock();
		try {
			while(number == 0) {
				condition.await();
			}
			number--;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
}



