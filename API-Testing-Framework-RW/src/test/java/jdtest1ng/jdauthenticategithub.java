package jdtest1ng;



import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapibase.RequestClass;
import com.utility.Auth;
import com.utility.CreateURL;
import com.utility.FileCreate;
import com.utility.commonFunction;
import com.utility.createfile;
import com.utility.playloadCoverting;

import io.restassured.response.Response;
import resources.pojoclasses.createRepopojo;



public class jdauthenticategithub {

	public static String sha;
	public static ObjectMapper objectmapper;
	 @Test(priority=1)
	 public static void createRepository() throws IOException {
		  String endpoint = CreateURL.getBaseUri("/user/repos");
		   String bearer_token= Auth.getToken();
			 Response response;
		 String request_paLoad=playloadCoverting.generatingLoadString("jdcreateRepo.json");
		 response=RequestClass.PostRequest(endpoint, request_paLoad, bearer_token);
		 String responsebody=response.getBody().asString();
			System.out.println(responsebody);
         Assert.assertEquals(commonFunction.getResponseStatus(response), 201);
         int x=commonFunction.getResponseStatus(response);
         System.out.println(x);
	 }
	 @Test(priority=2)
		public static void  listpublicrepository() {
		  String endpoint = CreateURL.getBaseUri("/repositories");
			 String bearer_token= Auth.getToken();
			 Response response;
			response=RequestClass.GetRequest(endpoint, bearer_token);
			String responsebody=response.getBody().asString();
			System.out.println(responsebody);
			Assert.assertEquals(commonFunction.getResponseStatus(response), 200);
			int x=commonFunction.getResponseStatus(response);
			System.out.println(x);
		}
	  @Test(priority=3)
	 public static void  listrepolanguage() {
		  String endpoint = CreateURL.getBaseUri("/repos/Jaydip97/seleniumeframework/languages");
			 String bearer_token= Auth.getToken();
			 Response response;
			response=RequestClass.GetRequest(endpoint, bearer_token);
			String responsebody=response.getBody().asString();
			System.out.println(responsebody);
			Assert.assertEquals(commonFunction.getResponseStatus(response), 200);
			int x=commonFunction.getResponseStatus(response);
			System.out.println(x);
		}
	 @Test(priority=4)
		public static void  listrepousers() {
		   String endpoint = CreateURL.getBaseUri("/users/Jaydip97/repos");
			 String bearer_token= Auth.getToken();
			 Response response;
			response=RequestClass.GetRequest(endpoint, bearer_token);
			String responsebody=response.getBody().asString();
			System.out.println(responsebody);
			Assert.assertEquals(commonFunction.getResponseStatus(response), 200);
			int x=commonFunction.getResponseStatus(response);
			System.out.println(x);
		}
	  @Test(priority=5)
	 public static void  listrepotag() {
		     String endpoint = CreateURL.getBaseUri("/repos/Jaydip97/seleniumeframework/tags");
			 String bearer_token= Auth.getToken();
			 Response response;
			response=RequestClass.GetRequest(endpoint, bearer_token);
			String responsebody=response.getBody().asString();
			//System.out.println(responsebody);
			Assert.assertEquals(commonFunction.getResponseStatus(response), 200);
			int x=commonFunction.getResponseStatus(response);
			System.out.println(x);
		}
	  @Test(priority=6)
		 public static void createorupdatefile() throws IOException  {
		   String endpoint=CreateURL.getBaseUri("/repos/Jaydip97/jaydip111/contents/jaydip1");;
			 String bearer_token= Auth.getToken();
		 Response response;

			
			String request_paLoad=playloadCoverting.generatingLoadString("createorupdatefile.json");
			 response= RequestClass.PutRequest(endpoint, request_paLoad, bearer_token);
			 String responsebody=response.getBody().asString();
				System.out.println(responsebody);
				sha=commonFunction.getResponseValue(response.asString(),"content.sha");
	      Assert.assertEquals(commonFunction.getResponseStatus(response), 201);
	      int x=commonFunction.getResponseStatus(response);
	         System.out.println(x);
		}
//
//	  
	  @Test(priority=7)
	  
	  public  void DeleteFile() throws JsonProcessingException  {
		// System.out.println(sha);
		  FileCreate requestData=new FileCreate();
		  requestData.setMessage("My delete message");
		   requestData.setSha(sha);
		   objectmapper=new ObjectMapper();
			String data = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestData);
//			System.out.println(data);
//			System.out.println(createfile.toString());
		 //baseUri = CreateURL.getBaseUri("/repos/"+res+"/contents/SomeFile");
			String endpoint = CreateURL.getBaseUri("/repos/Jaydip97/jaydip111/contents/jaydip1");
			String bearer_token= Auth.getToken();
		 Response response = RequestClass.DeleteRequest(endpoint,data, bearer_token);
		// System.out.println(response.asString());
		 AssertJUnit.assertEquals(commonFunction.getResponseStatus(response), 200);
		 int x=commonFunction.getResponseStatus(response);
         System.out.println(x);
			}
//	  
	  @Test(priority=8)
		public  static void deleterepository() {
		   String endpoint = CreateURL.getBaseUri("/repos/Jaydip97/jaydip111");
			 String bearer_token= Auth.getToken();
			 Response response;
			response=RequestClass.DeleteRequest(endpoint, bearer_token);
			 String responsebody=response.getBody().asString();
				System.out.println(responsebody);
	   Assert.assertEquals(commonFunction.getResponseStatus(response), 204);
	   int x=commonFunction.getResponseStatus(response);
       System.out.println(x);
		}
	   
}

