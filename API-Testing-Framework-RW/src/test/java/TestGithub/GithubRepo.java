package TestGithub;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import javax.sound.midi.Soundbank;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapibase.RequestClass;
import com.utility.Auth;
import com.utility.CreateRepo;
import com.utility.CreateURL;
import com.utility.commonFunction;
import com.utility.playloadCoverting;

import freemarker.template.ObjectWrapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GithubRepo {
	public static String token=Auth.getToken();
	public static String baseUri;
	public String res;
	public CreateRepo requestData = new CreateRepo();
	ObjectMapper oMapper;

	@Test(priority = 0)
	public void creatingRepo() throws IOException {
		requestData.setDescription("this is the description kps");
		requestData.setName("something");
		oMapper = new ObjectMapper();
	String data = 	oMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestData);
//	System.out.println(data);
		baseUri =  CreateURL.getBaseUri("/user/repos");
		String body =  playloadCoverting.generatingLoadString("CreateRepo.json");
	Response response =	RequestClass.PostRequest(baseUri, data, token);
	System.out.println(response.asString());
	AssertJUnit.assertEquals(commonFunction.getResponseValue(response.asString(), "name"),
			commonFunction.getResponseValue(data, "name"));
	AssertJUnit.assertEquals(commonFunction.getResponseStatus(response), 201);
	res = commonFunction.getResponseValue(response.asString(), "full_name");
	}
	@Test(priority = 1)
	public void GetTheRepo() throws IOException {
		baseUri = CreateURL.getBaseUri("/repos/"+res);
		Response response = RequestClass.GetRequest(baseUri, token);
//		System.out.println(response.asString());
		AssertJUnit.assertEquals(commonFunction.getResponseStatus(response), 200);
	}
	@Test(priority = 2)
	public void UpdateTheRepo() throws IOException {
		requestData.setDescription("this is the description kps sk sk");
		requestData.setName("somethingHello");
		oMapper = new ObjectMapper();
	String data = 	oMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestData);
		baseUri = CreateURL.getBaseUri("/repos/"+res);
		Response response = RequestClass.PatchRequest(baseUri,data, token);
//		System.out.println(response.asString());
		res = commonFunction.getResponseValue(response.asString(), "full_name");
		AssertJUnit.assertEquals(commonFunction.getResponseStatus(response), 200);
	}
	@Test(priority = 3)
	public void CreateFile() throws IOException {
		
		baseUri = CreateURL.getBaseUri("/repos/"+res+"/contents/SomeFile");
		String body =  playloadCoverting.generatingLoadString("CreateFile.json");
		Response response = RequestClass.PutRequest(baseUri,body, token);
//		System.out.println(response.asString());
		AssertJUnit.assertEquals(commonFunction.getResponseStatus(response), 201);
	}
	@Test(priority = 4)
	public void CreateRepoFork() throws IOException {

		baseUri = CreateURL.getBaseUri("/repos/"+res+"/forks");
		String body =  playloadCoverting.generatingLoadString("CreateFork.json");
		Response response = RequestClass.PostRequest(baseUri,body, token);
//		System.out.println(response.asString());
		AssertJUnit.assertEquals(commonFunction.getResponseStatus(response), 202);
	}
	@Test(priority = 5)
	public void ListFork() throws IOException {

		baseUri = CreateURL.getBaseUri("/repos/"+res+"/forks");
		Response response = RequestClass.GetRequest(baseUri, token);
//		System.out.println(response.asString());
		AssertJUnit.assertEquals(commonFunction.getResponseStatus(response), 200);
	}
	@Test(priority = 6)
	private  void deleteRepo() {
		baseUri = CreateURL.getBaseUri("/repos/"+res);
		Response response = RequestClass.DeleteRequest(baseUri, token);
//		System.out.println(response.asString());
		AssertJUnit.assertEquals(commonFunction.getResponseStatus(response), 204);
	}
	
}
