package com.utility;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class commonFunction {
	public static JsonPath jsonPath;
	public static <T> T getResponseValue(String requestbody,String requestkey) {
		jsonPath = new JsonPath(requestbody);
		return jsonPath.get(requestkey);
	}
	public static int getResponseStatus(Response res) {
		int sc =res.statusCode();
		return sc;
	}
}
