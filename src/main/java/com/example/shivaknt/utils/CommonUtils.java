package com.example.shivaknt.utils;

public class CommonUtils {
	public static String getString(String str) {
		if(str != null && !str.isEmpty()) {
			return str.substring(0, str.length() < 50 ? str.length() -1 : 50);
		}
		return "";
	}
}
