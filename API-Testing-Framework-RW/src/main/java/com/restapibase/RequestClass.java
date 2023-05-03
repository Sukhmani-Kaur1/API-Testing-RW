package com.restapibase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestClass {

	public static Response GetRequest(String uri) {
		RequestSpecification requestSpecification = RestAssured.given();
		Response response = requestSpecification.contentType(ContentType.JSON).when().get(uri);
		return response;

	}

	public static Response GetRequest(String uri, String bearer_token) {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestSpecification.contentType(ContentType.JSON).when().get(uri);
		return response;

	}

	public static Response GetRequest(String uri, String payLoad, String bearer_token) {
		RequestSpecification requestSpecification = RestAssured.given().body(payLoad);
		requestSpecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestSpecification.contentType(ContentType.JSON).when().get(uri);
		return response;

	}

	public static Response GetRequest(String uri, String payLoad, String bearer_token, String api) {
		RequestSpecification requestSpecification = RestAssured.given().body(payLoad);
		requestSpecification.queryParam("key", api);
		requestSpecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestSpecification.contentType(ContentType.JSON).when().get(uri);
		return response;

	}

	public static Response PostRequest(String uri, String bearer_token) {
		RequestSpecification requestSpecification = RestAssured.given();

		requestSpecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestSpecification.contentType(ContentType.JSON).when().post(uri);
		return response;
	}

	public static Response PostRequest(String uri, String payLoad, String bearer_token) {
		RequestSpecification requestSpecification = RestAssured.given()
				.header("Authorization", "Bearer " + bearer_token).body(payLoad);
		Response response = requestSpecification.contentType(ContentType.JSON).when().post(uri);
		return response;
	}

	public static Response PostRequest(String uri, String payLoad, String bearer_token, String api) {
		RequestSpecification requestSpecification = RestAssured.given()
				.header("Authorization", "Bearer " + bearer_token).body(payLoad);
		requestSpecification.queryParam("key", api);
		requestSpecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestSpecification.contentType(ContentType.JSON).when().post(uri);
		return response;
	}

	public static Response PutRequest(String uri, String bearer_token) {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestSpecification.contentType(ContentType.JSON).when().put(uri);
		return response;
	}

	public static Response PutRequest(String uri, String payLoad, String bearer_token) {
		RequestSpecification requestSpecification = RestAssured.given().body(payLoad);
		requestSpecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestSpecification.contentType(ContentType.JSON).when().put(uri);
		return response;
	}

	public static Response PutRequest(String uri, String payLoad, String bearer_token, String api) {
		RequestSpecification requestSpecification = RestAssured.given().body(payLoad);
		requestSpecification.queryParam("key", api);
		requestSpecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestSpecification.contentType(ContentType.JSON).when().put(uri);
		return response;
	}

	public static Response DeleteRequest(String uri) {
		RequestSpecification requestSpecification = RestAssured.given();
		Response response = requestSpecification.contentType(ContentType.JSON).when().delete(uri);
		return response;
	}

	public static Response DeleteRequest(String uri, String bearer_token) {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestSpecification.contentType(ContentType.JSON).when().delete(uri);
		return response;
	}

	public static Response DeleteRequest(String uri, String payLoad, String bearer_token) {
		RequestSpecification requestSpecification = RestAssured.given().body(payLoad);
		requestSpecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestSpecification.contentType(ContentType.JSON).when().delete(uri);
		return response;
	}

	public static Response DeleteRequest(String uri, String payLoad, String bearer_token, String api) {
		RequestSpecification requestSpecification = RestAssured.given().body(payLoad);
		requestSpecification.queryParam("key", api);
		requestSpecification.header("Authorization", "Bearer " + bearer_token);
		Response response = requestSpecification.contentType(ContentType.JSON).when().delete(uri);
		return response;
	}
}
