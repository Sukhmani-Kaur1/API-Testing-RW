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

public class deletefile {

	
	

		public static String endpoint = CreateURL.getBaseUri("/repos/Jaydip97/jaydip111/contents/jaydip1");
		public static String bearer_token= Auth.getToken();
		public static Response response;
		
		@Test
		public  static void deletfile() throws IOException {
			String request_paLoad=playloadCoverting.generatingLoadString("delete.json");
			 response= RequestClass.DeleteRequest(endpoint, request_paLoad, bearer_token);
			 String responsebody=response.getBody().asString();
				System.out.println(responsebody);
	      Assert.assertEquals(commonFunction.getResponseStatus(response), 200);
		}
}
