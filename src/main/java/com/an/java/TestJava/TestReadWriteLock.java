package com.an.java.TestJava;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *读写锁
 *一个线程写，100个线程读取
 */
public class TestReadWriteLock {

	public static void main(String[] args) {
		MyReadWriteLock rw = new MyReadWriteLock();
		for(int i=1;i<=5;i++) {
			new Thread(()-> {
				rw.write("Hello..");
			}, "WriteTask...").start();
		}
		
		for(int i=1;i<=10;i++) {
			new Thread(()->{
				rw.read();
			},String.valueOf(i)) .start();
		}
	}
}

class MyReadWriteLock{
	Object obj = null;
	ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
	
	public  void write(Object obj) {
		rw.writeLock().lock();
		try {
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} finally {
			rw.writeLock().unlock();
		}
	}
	
	public void read() {
		rw.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} finally {
			rw.readLock().unlock();
		}
	}
	
}
