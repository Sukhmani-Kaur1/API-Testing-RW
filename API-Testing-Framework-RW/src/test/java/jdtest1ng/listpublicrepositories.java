package jdtest1ng;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restapibase.RequestClass;
import com.utility.Auth;
import com.utility.CreateURL;
import com.utility.commonFunction;

import io.restassured.response.Response;

public class listpublicrepositories {

	public static String endpoint = CreateURL.getBaseUri("/repositories");
	public static String bearer_token= Auth.getToken();
	public static Response response;
	
	@Test
	public static void  listrepouser() {
		response=RequestClass.GetRequest(endpoint, bearer_token);
		String responsebody=response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonFunction.getResponseStatus(response), 200);
		int x=commonFunction.getResponseStatus(response);
		System.out.println(x);
	}
}
