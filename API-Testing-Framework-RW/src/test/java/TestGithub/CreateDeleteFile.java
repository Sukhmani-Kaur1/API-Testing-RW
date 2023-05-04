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
import com.utility.FileCreate;
import com.utility.commonFunction;
import com.utility.playloadCoverting;

import freemarker.template.ObjectWrapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateDeleteFile {
	public static String token=Auth.getToken();
	public static String baseUri;
	public String res;
	public CreateRepo requestData = new CreateRepo();
	public FileCreate createfile = new FileCreate();
	ObjectMapper oMapper;
	public static String sha;

	@Test(priority = 0)
	public void creatingRepo() throws IOException {
		requestData.setDescription("this is the description kps");
		requestData.setName("somethingForFile");
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
	public void CreateFile() throws IOException {
		
		baseUri = CreateURL.getBaseUri("/repos/"+res+"/contents/SomeFile");
		String body =  playloadCoverting.generatingLoadString("CreateFile.json");
		Response response = RequestClass.PutRequest(baseUri,body, token);
//		System.out.println(response.asString());
		sha = commonFunction.getResponseValue(response.asString(), "content.sha");
		AssertJUnit.assertEquals(commonFunction.getResponseStatus(response), 201);
	}
	@Test(priority = 2)
	public void DeleteFile() throws IOException {
//		System.out.println(sha);
		createfile.setMessage("My delete message");
		createfile.setSha(sha);
		oMapper = new ObjectMapper();
	String data = 	oMapper.writerWithDefaultPrettyPrinter().writeValueAsString(createfile);
//	System.out.println(data);
//	System.out.println(createfile.toString());
		baseUri = CreateURL.getBaseUri("/repos/"+res+"/contents/SomeFile");
		Response response = RequestClass.DeleteRequest(baseUri,data, token);
//		System.out.println(response.asString());
		AssertJUnit.assertEquals(commonFunction.getResponseStatus(response), 200);
	}
	@Test(priority = 3)
	private  void deleteRepo() {
		baseUri = CreateURL.getBaseUri("/repos/"+res);
		Response response = RequestClass.DeleteRequest(baseUri, token);
//		System.out.println(response.asString());
		AssertJUnit.assertEquals(commonFunction.getResponseStatus(response), 204);
	}
	
}
