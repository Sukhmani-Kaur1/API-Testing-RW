package com.utility;

public class CreateURL {
	public final static String baseURL = "https://api.github.com";
	public static String getBaseUri() {
		return baseURL;
	}
	public static String getBaseUri(String resourse) {
		return baseURL+resourse;
	}
	
}
