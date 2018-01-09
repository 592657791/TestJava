package com.an.java.single;

import junit.framework.Test;

/**
 * 单例模式
 * @author Administrator
 *
 */
public class TestSingleTon {
	
	private  static volatile TestSingleTon instance = null;
	
	public static void main(String[] args) {
		for(int i=0;i<=300;i++) {
			new Thread(()->{
				TestSingleTon.getInstance();
			}).start();
		}
	}
	
	private TestSingleTon() {
		System.out.println("----------OK");
	}
	
	public static TestSingleTon getInstance() {
		if(instance == null) {
			synchronized (TestSingleTon.class) {
				if(instance == null) {
					instance = new TestSingleTon();
				}
			}
		}
		return instance;
	}
}
