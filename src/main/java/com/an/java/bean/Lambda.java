package com.an.java.bean;

@FunctionalInterface
public interface Lambda {
	
	public int div(int x,int y);
	
	default int add(int x,int y) {
		return x+y;
	}
	
	static int sub(int x,int y) {
		return x-y;
	}
}
