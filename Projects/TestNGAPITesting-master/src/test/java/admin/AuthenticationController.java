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

public class AuthenticationController extends BaseTest {

	@Test
	public void test01AuthRegister() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");
		
		String uniqueEmail = "harshit@gmail.com" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String uniqueName = "harshit" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		

		String jsonBody = "{\n" + "  \"firstName\": \"string\",\n" + "  \"lastName\": \"string\",\n"
				+ "  \"midName\": \"string\",\n" + "  \"email\": \""+uniqueEmail+"\",\n" + "  \"password\": \"string\",\n"
				+ "  \"userName\": \""+uniqueName+"\",\n" + "  \"phoneNumber\": \"string\",\n" + "  \"faxNo\": \"string\",\n"
				+ "  \"companyName\": \"string\",\n" + "  \"email2\": \"string\",\n" + "  \"role\": \"bidder\",\n"
				+ "  \"status\": \"ACTIVE\",\n" + "  \"referenceName\": \"string\",\n" + "  \"remark\": \"string\",\n"
				+ "  \"addressInfo\": {\n" + "    \"type\": \"string\",\n" + "    \"houseNo\": \"string\",\n"
				+ "    \"city\": \"string\",\n" + "    \"zipCode\": \"string\",\n" + "    \"state\": \"string\",\n"
				+ "    \"country\": \"string\",\n" + "    \"countryId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n"
				+ "  },\n" + "  \"active\": \"string\",\n" + "  \"kycStatus\": \"string\",\n"
				+ "  \"userInterest\": \"string\",\n" + "  \"ledgerType\": \"string\",\n"
				+ "  \"isSupplier\": \"string\",\n" + "  \"noOfBoxes\": \"string\",\n" + "  \"pendingBoxes\": 0,\n"
				+ "  \"auctionIds\": [\n" + "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n"
				+ "  \"validIdProofName\": \"string\",\n" + "  \"companyIdProof\": \"string\",\n"
				+ "  \"userId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"soldCommision\": 0,\n"
				+ "  \"unsoldCommision\": 0,\n" + "  \"unsoldCommCalculation\": \"string\"\n" + "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// Send the POST request
		Response response = request.post("/auth/register");

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

	@Test(enabled=false)
	public void test02AuthRegisterAll() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		
		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");
		
		String uniqueEmail2 = "turpti@gmail.com" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		String jsonBody = "[\n" + "  {\n" + "    \"firstName\": \"string\",\n" + "    \"lastName\": \"string\",\n"
				+ "    \"midName\": \"string\",\n" + "    \"email\": \""+uniqueEmail2+"\",\n" + "    \"password\": \"string\",\n"
				+ "    \"userName\": \"string\",\n" + "    \"phoneNumber\": \"string\",\n"
				+ "    \"faxNo\": \"string\",\n" + "    \"companyName\": \"string\",\n"
				+ "    \"email2\": \"string\",\n" + "    \"role\": \"bidder\",\n" + "    \"status\": \"ACTIVE\",\n"
				+ "    \"referenceName\": \"string\",\n" + "    \"remark\": \"string\",\n" + "    \"addressInfo\": {\n"
				+ "      \"type\": \"string\",\n" + "      \"houseNo\": \"string\",\n" + "      \"city\": \"string\",\n"
				+ "      \"zipCode\": \"string\",\n" + "      \"state\": \"string\",\n"
				+ "      \"country\": \"string\",\n" + "      \"countryId\": \"b5128b7b-d7cc-11ef-bc18-c8d3ffbc6ac6\"\n"
				+ "    },\n" + "    \"active\": \"string\",\n" + "    \"kycStatus\": \"string\",\n"
				+ "    \"userInterest\": \"string\",\n" + "    \"ledgerType\": \"string\",\n"
				+ "    \"isSupplier\": \"string\",\n" + "    \"noOfBoxes\": \"string\",\n"
				+ "    \"pendingBoxes\": 0,\n" + "    \"auctionIds\": [\n"
				+ "      \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "    ],\n"
				+ "    \"validIdProofName\": \"string\",\n" + "    \"companyIdProof\": \"string\",\n"
				+ "    \"userId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "    \"soldCommision\": 0,\n"
				+ "    \"unsoldCommision\": 0,\n" + "    \"unsoldCommCalculation\": \"string\"\n" + "  }\n" + "]";

		// Add the JSON body to the request
		request.body(jsonBody);

		// Send the POST request
		Response response = request.post("/auth/register/all");

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
