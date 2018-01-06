package com.an.java.TestJava;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 三个窗口买票,共买30张票,不用lambda表达式
 * @author Administrator
 */
public class TestThread {
	public static void main(String[] args) {
		Ticket t = new Ticket();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<=40;i++)
				t.sale();
			}
		},"A").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<=40;i++)
				t.sale();
			}
		},"B").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<=40;i++)
				t.sale();
			}
		},"C").start();
	}
	
}

class Ticket{
	int ticket = 30;
	Lock lock = new ReentrantLock();
	public void sale() {
		lock.lock();
		try {
			if(ticket > 0) {
				System.out.println("窗口"+Thread.currentThread().getName()+"\t卖出第："+(ticket--)+"\t还剩："+ticket);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
}
