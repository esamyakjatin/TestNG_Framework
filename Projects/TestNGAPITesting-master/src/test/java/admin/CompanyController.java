package admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class CompanyController extends BaseTest {

	@Test
	public void test01PutCompnayId() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the PUT request

		String jsonBody = "{\n" + "  \"companyId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n"
				+ "  \"name\": \"string\",\n" + "  \"englishName\": \"string\",\n" + "  \"description\": \"string\",\n"
				+ "  \"address1\": \"string\",\n" + "  \"address2\": \"string\",\n" + "  \"address3\": \"string\",\n"
				+ "  \"code\": \"string\",\n" + "  \"srNo\": 0,\n" + "  \"bankAcName\": \"string\",\n"
				+ "  \"bankCode\": \"string\",\n" + "  \"bankName\": \"string\",\n"
				+ "  \"bankAcNumber\": \"string\",\n" + "  \"active\": true\n" + "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// String auctionId = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String companyId = "cb0ba36b-b131-11ef-a57f-c8d3ffbc6ac6";

		// request.pathParam("userId", userId);
		// request.pathParam("auctionId", auctionId);
		request.pathParam("companyId", companyId);

		// Send the PUT request
		Response response = request.put("/company/{companyId}");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		if (statusCode == 200) {
			System.out.println("Request succeeded: User updated successfully.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 404) {
			System.out.println("Not Found: The resource could not be found.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK)
		Assert.assertTrue(statusCode == 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void tset02GetCompnay() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// Send the GET request with query parameters
		Response response = request.get("/company");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString()); // Added response logging
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 403 (Forbidden)
		if (statusCode == 403) {
			System.out.println("Access denied: Invalid credentials or permissions.");
		} else if (statusCode == 200) {
			System.out.println("Request succeeded: Access granted.");
		}

		// Assert that the status code is 200 (OK), or change the expectation if 403 is
		// valid
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void test03PostCompany() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		 String encryptedCredentials = Constant.adminCredentials; 

		    
		    request.body(encryptedCredentials);
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		request.header("Content-Type", "application/json");

		String uniqueName = "Japan Auction House" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		
		// Create the JSON body for the POST request
		String jsonBody = "{\n" +
			    "  \"companyId\": \"839961ab-d237-11ef-b309-c8d3ffbc6ac6 \",\n" +
			    "  \"name\": \""+uniqueName+"\",\n" +
			    "  \"englishName\": \"India Auction House\",\n" +
			    "  \"description\": \"India Auction House\",\n" +
			    "  \"address1\": \"India\",\n" +
			    "  \"address2\": \"Japan\",\n" +
			    "  \"address3\": \"Japan\",\n" +
			    "  \"code\": \"JAH\",\n" +
			    "  \"srNo\": 1,\n" +
			    "  \"accountNumber\": \"408-9118269\",\n" +
			    "  \"bankCode\": \"MHCBJPJT\",\n" +
			    "  \"bankName\": \"MIZUHO BANK , KOFU BRANCH\",\n" +
			    "  \"bankAddress\": \"1-19-10 MARUNOUCHI, KOFU, YAMANASHI, JAPAN 4000031. TEL # 81 552371511\",\n" +
			    "  \"active\": 1\n" +
			    "}";


		// Add the JSON body to the request
		request.body(jsonBody);

		// Send the POST request
		Response response = request.post("/company");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString()); // Added response logging
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 201 (Created)
		if (statusCode == 200) {
			System.out.println("Request succeeded: Data inserted successfully.");
		} else if (statusCode == 201) {
			System.out.println("Request succeeded: Symmetry created.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK) or 201 (Created)
		Assert.assertTrue(statusCode == 200 || statusCode == 201,
				"Expected 200 OK or 201 Created, but got: " + statusCode);
	}
}

