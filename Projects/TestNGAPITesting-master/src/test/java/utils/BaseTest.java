package utils;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

	@BeforeSuite
	public void beforeSuite() {

		System.out.println("BeforeSuite");

		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		String encryptedCredentials = Constant.adminCredentials; 

	    request.queryParam("payload", encryptedCredentials);

		request.header("Content-Type", "application/json"); // Add the Json to the body of the request
		request.body(encryptedCredentials);

		Response response = request.post("/auth/authenticate");
		System.out.println("The status received: " + response.statusLine());

		System.out.println("---------------Response---------------");
		System.out.println();
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		System.out.println(response.getBody().asString());

		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		Constant.authToken = (String) jsonObject.get("accessToken");

		Assert.assertEquals(statusCode, 200);
	}

	@AfterSuite
	public void afterSuite() {

		System.out.println("AfterSuite");
	}

}