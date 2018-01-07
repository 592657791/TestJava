package com.an.java.enums;

public enum CountryEnums {
	ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"韩"),SIX(6,"魏");

	private int key;
	private String value;
	
	private CountryEnums(int key,String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	//通过Enums的key值得到value值
	public static CountryEnums getCountryEnums(int key) {
		for (CountryEnums element : values()) {
			if(element.getKey() == key) {
				return element;
			}
		}
		return null;
	}
	
}
