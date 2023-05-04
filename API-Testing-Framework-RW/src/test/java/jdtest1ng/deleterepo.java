package jdtest1ng;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restapibase.RequestClass;
import com.utility.Auth;
import com.utility.CreateURL;
import com.utility.commonFunction;

import io.restassured.response.Response;

public class deleterepo {

	public static String endpoint = CreateURL.getBaseUri("/repos/Jaydip97/jaydip111");
	public static String bearer_token= Auth.getToken();
	public static Response response;
	
	@Test
	public  static void deleterepository() {
		response=RequestClass.DeleteRequest(endpoint, bearer_token);
		 String responsebody=response.getBody().asString();
			System.out.println(responsebody);
   Assert.assertEquals(commonFunction.getResponseStatus(response), 204);
	}
}
