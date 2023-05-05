package GitRepoTestNew;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.restapibase.RequestClass;
import com.utility.Auth;
import com.utility.CreateURL;
import com.utility.commonFunction;
import com.utility.playloadCoverting;

import io.restassured.response.Response;

public class GitRepoTestNew {
	public static String endpoint;
	public static String bearer_token = Auth.getToken();
	public static  Response response;
	public static String owner;
    public static int id; 
	
	@Test(priority = 0)
	public void createRepository() throws IOException{
		endpoint = CreateURL.getBaseUri("/user/repos");
		String request_payload = playloadCoverting.generatingLoadString("githubrepo.json");
		response = RequestClass.PostRequest(endpoint, request_payload, bearer_token);
//		 String responsebody = response.getBody().asString();
		 System.out.println(response.asString());
		 owner = commonFunction.getResponseValue(response.asString(), "full_name");
		 Assert.assertEquals(commonFunction.getResponseValue(request_payload, "name"), 
				 commonFunction.getResponseValue(response.asString(), "name"));
		    Assert.assertEquals(commonFunction.getResponseStatus(response), 201);
	}

	@Test(priority = 1)
	public void createAutolinkReference() throws IOException{
		endpoint = CreateURL.getBaseUri("/repos/"+owner+"/autolinks");
		String request_payload = playloadCoverting.generatingLoadString("autolink.json");
		response = RequestClass.PostRequest(endpoint, request_payload, bearer_token);
		 String responsebody = response.getBody().asString();
		 id = commonFunction.getResponseValue(responsebody, "id");
		 System.out.println(responsebody);
		 System.out.println(id);
//		 Assert.assertEquals(commonFunction.getResponseValue(request_payload, "name"), 
//				 commonFunction.getResponseValue(responsebody, "name"));
		    Assert.assertEquals(commonFunction.getResponseStatus(response), 201);
	}
	
	@Test(priority = 2)
	public void getAllRepoTopics() throws IOException{
		endpoint = CreateURL.getBaseUri("/repos/"+owner+"/topics");
//		String request_payload = playloadCoverting.generatingLoadString("githubrepo.json");
		response = RequestClass.GetRequest(endpoint, bearer_token);
		 String responsebody = response.getBody().asString();
//		 Assert.assertEquals(commonFunction.getResponseValue(request_payload, "name"), 
//				 commonFunction.getResponseValue(responsebody, "name"));
		    Assert.assertEquals(commonFunction.getResponseStatus(response), 200);
	}
	
	@Test(priority = 7)
	public void deleteRepos() throws IOException{
//
		endpoint = CreateURL.getBaseUri("/repos/"+owner);
//		String request_payload = playloadCoverting.generatingLoadString("githubrepo.json");
		response = RequestClass.DeleteRequest(endpoint, bearer_token);
		 String responsebody = response.getBody().asString();
//		 Assert.assertEquals(commonFunction.getResponseValue(request_payload, "name"), 
//				 commonFunction.getResponseValue(responsebody, "name"));
		    Assert.assertEquals(commonFunction.getResponseStatus(response), 204);
	}
//	
	@Test(priority = 3)
	public void getAutolinkReference() throws IOException{
		endpoint = CreateURL.getBaseUri("/repos/" +owner+ "/autolinks/"+id);
//		String request_payload = playloadCoverting.generatingLoadString("getautolink.json");
		response = RequestClass.GetRequest(endpoint, bearer_token);
		 String responsebody = response.getBody().asString();
//		 Assert.assertEquals(commonFunction.getResponseValue(request_payload, "name"), 
//				 commonFunction.getResponseValue(responsebody, "name"));
		    Assert.assertEquals(commonFunction.getResponseStatus(response), 200);
	}
	
	@Test(priority = 4)
	public void deleteFromAutolinkReference() throws IOException{
		endpoint = CreateURL.getBaseUri("/repos/"+owner+"/autolinks/"+id);
//		String request_payload = playloadCoverting.generatingLoadString("githubrepo.json");
		response = RequestClass.DeleteRequest(endpoint, bearer_token);
		 String responsebody = response.getBody().asString();
//		 Assert.assertEquals(commonFunction.getResponseValue(request_payload, "name"), 
//				 commonFunction.getResponseValue(responsebody, "name"));
		    Assert.assertEquals(commonFunction.getResponseStatus(response), 204);
	}
//	
	@Test(priority = 5)
	public void getaRepository() throws IOException{
		endpoint = CreateURL.getBaseUri("/repos/"+owner);
//		String request_payload = playloadCoverting.generatingLoadString("githubrepo.json");
		response = RequestClass.GetRequest(endpoint, bearer_token);
		 String responsebody = response.getBody().asString();
//		 Assert.assertEquals(commonFunction.getResponseValue(request_payload, "name"), 
//				 commonFunction.getResponseValue(responsebody, "name"));
		    Assert.assertEquals(commonFunction.getResponseStatus(response), 200);
	}
	
	@Test(priority = 6)
	public void replaceallRepoTopics() throws IOException{
		endpoint = CreateURL.getBaseUri("/repos/"+owner+"/topics");
		String request_payload = playloadCoverting.generatingLoadString("replacetopic.json");
		response = RequestClass.PutRequest(endpoint, request_payload, bearer_token);
		 String responsebody = response.getBody().asString();
		 Assert.assertEquals(commonFunction.getResponseValue(request_payload, "name"), 
				 commonFunction.getResponseValue(responsebody, "name"));
		    Assert.assertEquals(commonFunction.getResponseStatus(response), 200);
	}

}
