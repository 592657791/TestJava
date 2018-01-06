package com.an.java.TestJava;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TestCallable {
	public static void main(String[] args) throws Exception {
		FutureTask<Integer> ft = new FutureTask<>(new MyThread());
		new Thread(ft,"A").start();
		
		//得到线程的返回值
		Integer result = ft.get();
		System.out.println(result);
	}
}

/**
 * 关于实现多线程的第三种方法:显现Callable接口
 * 与实现Runnable的区别：
 * 1.Callable有返回值,实现的方法为call,抛出异常
 * 2.得到返回值时,通过ft.get();
 * 3.这个多用于计算量比较耗时的线程,但又不阻塞主线程时.一般FutureTask多用于比较耗时的操作.
 * 4.一旦计算完成,就会取消计算
 * 
 * ---一次计算    用于耗时操作  get()放在最后
 * @author Administrator
 *
 */

class MyThread implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		System.out.println("------Test Callable------");
		return 666;	
	}
	
}
