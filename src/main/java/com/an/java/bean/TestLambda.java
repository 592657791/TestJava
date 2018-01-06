package com.an.java.bean;

public class TestLambda {
   public static void main(String[] args) {
	   
	   Lambda l = (x,y) -> {
		   return x/y;
	   };
	   
	   int add = l.add(1, 2);
	   System.out.println(add);
	   
	   int sub = Lambda.sub(10, 5);
	   System.out.println(sub);
   }
	
}
