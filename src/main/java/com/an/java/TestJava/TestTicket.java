package com.an.java.TestJava;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 三个窗口买票,共买30张票,用lambda表达式
 * @author Administrator
 */
public class TestTicket {
	public static void main(String[] args) {
		Ticket1 t = new Ticket1();
		new Thread(()->{
			for(int i=1;i<=30;i++) {
				t.sell();
			}
		},"A").start();; 
		
		new Thread(()->{
			for(int i=1;i<=40;i++) {
				t.sell();
			}
		},"B").start();
		
		new Thread(()->{
			for(int i=1;i<=40;i++) {
				t.sell();
			}
		},"C").start(); 
	}
}

class Ticket1{
	int ticketNum = 30;
	Lock lock = new ReentrantLock();
	public void sell() {
		lock.lock();
		try {
			Thread.sleep(1000);
			if(ticketNum > 0) {
				System.out.println("窗口"+Thread.currentThread().getName()+"\t卖出第："+(ticketNum--)+"\t还剩："+ticketNum);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
