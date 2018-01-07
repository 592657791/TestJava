package com.an.java.TestJava;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class TestListSafe {
	public static void main(String[] args) throws InterruptedException {
		
		Map<String,String> map =new ConcurrentHashMap<String,String>();
		
		for(int i=1;i<=30;i++) {
			new Thread(()->{
				map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
				System.out.println(map);
			},String.valueOf(i)).start();
		}
	}

	private static void SafeSet() {
		Set<String> set = new CopyOnWriteArraySet<String>();
		for(int i=1;i<=30;i++) {
			new Thread(()->{
				set.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(set);
			},String.valueOf(i)).start();
		}
	}

	private static void SafeList() {
		List<String> list = new CopyOnWriteArrayList<>();
		for(int i=1;i<=30;i++) {
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0, 8));
				/*try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				System.out.println(list);
				
			},String.valueOf(i)) .start();
		}
		//Thread.sleep(500);
	}
}
