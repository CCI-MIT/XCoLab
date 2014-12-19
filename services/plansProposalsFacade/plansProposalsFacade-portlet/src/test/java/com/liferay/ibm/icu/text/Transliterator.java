package com.liferay.ibm.icu.text;


public class Transliterator {
	private static Transliterator instance = new Transliterator();
	
	public static Transliterator getInstance(String s) {
		return instance;
	}
	
	public String transform(String source) {
		return source;
	}
	
	


}
