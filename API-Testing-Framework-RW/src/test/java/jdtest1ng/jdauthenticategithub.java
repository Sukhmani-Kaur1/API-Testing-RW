package jdtest1ng;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restapibase.RequestClass;
import com.utility.Auth;
import com.utility.CreateURL;
import com.utility.commonFunction;
import com.utility.playloadCoverting;

import io.restassured.response.Response;



public class jdauthenticategithub {

	public static String endpoint = CreateURL.getBaseUri("/user/repos");
	public static String bearer_token= Auth.getToken();
	public static Response response;
	
	 @Test
	 public static void createRepository() throws IOException {
		 String request_paLoad=playloadCoverting.generatingLoadString("jdcreateRepo.json");
		 response=RequestClass.PostRequest(endpoint, request_paLoad, bearer_token);
		 String responsebody=response.getBody().asString();
			System.out.println(responsebody);
         Assert.assertEquals(commonFunction.getResponseStatus(response), 201);
         int x=commonFunction.getResponseStatus(response);
         System.out.println(x);
	 }
}
