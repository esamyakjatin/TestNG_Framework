package admin;

import java.io.FileOutputStream;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class LotController extends BaseTest {

	@Test
	public void test01LotidAmount() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);


		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		request.header("Content-Type", "application/json");

		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";
		String lotId = "ece5d964-d27b-434c-839e-3437977b0b0c";
		double amount = 50;

		// Add path parameters dynamically
		// request.pathParam("userId", userId);
		// request.pathParam("auctionId", auctionId);
		request.pathParam("lotId", lotId);
		request.pathParam("amount", amount);

		// Send the POST request
		Response response = request.put("/lot/supplier-offer/{lotId}/{amount}");

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
	public void test06DownloadLotExcel() {
	    // Set the base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

	    // Add Bearer token in Authorization header
	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);

	    // Add Content-Type header
	    request.header("Content-Type", "application/json");

	    // Add path parameter
	    String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";
	    request.pathParam("auctionId", auctionId);

	    // Send GET request
	    Response response = request.get("/download-lot-excel/{auctionId}");

	    // Handle response
	    System.out.println("The status received: " + response.statusLine());

	    // Check status code
	    int statusCode = response.getStatusCode();
	    if (statusCode == 401) {
	        System.out.println("Token expired. Please generate a new token.");
	        Assert.fail("Request failed due to token expiration.");
	    } else if (statusCode == 403) {
	        System.out.println("Access denied: Invalid credentials or permissions.");
	    } else if (statusCode == 200) {
	        System.out.println("Request succeeded: Access granted.");
	        try {
	            // Specify the file path where the Excel file should be saved
	            String filePath = "C:\\Users\\ESAMYAK121\\Desktop\\Testing\\lot-details.xlsx";

	            // Write the response body to the file
	            FileOutputStream fileOutputStream = new FileOutputStream(new String(filePath));
	            fileOutputStream.write(response.getBody().asByteArray());
	            fileOutputStream.close();

	            System.out.println("Excel file downloaded successfully at: " + filePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	            Assert.fail("Failed to save the Excel file: " + e.getMessage());
	        }
	    }

	    // Assert the status code
	    Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void test07BiddersPdf() {
	    // Set the base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

	    // Add Bearer token in Authorization header
	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);

	    // Add Content-Type header
	    request.header("Content-Type", "application/json");

	    // Add path parameter
	    String auctionId = "693c945d-da0a-4e41-b93a-081e0f033662";
	    request.pathParam("auctionId", auctionId);

	    // Log request details for debugging
	    request.log().all();

	    // Send GET request
	    Response response = request.get("/bidders/pdf/{auctionId}");

	    // Log response details for debugging
	    response.then().log().all();

	    // Print response details
	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());

	    // Handle different status codes
	    int statusCode = response.getStatusCode();
	    switch (statusCode) {
	        case 200:
	            System.out.println("Request succeeded: Access granted.");
	            break;
	        case 401:
	            System.out.println("Token expired. Please generate a new token.");
	            Assert.fail("Request failed due to token expiration.");
	            break;
	        case 403:
	            System.out.println("Access denied: Invalid credentials or permissions.");
	            Assert.fail("Access denied.");
	            break;
	        case 500:
	            System.out.println("Internal Server Error: Please check server logs.");
	            Assert.fail("Internal Server Error occurred.");
	            break;
	        default:
	            System.out.println("Unexpected status code: " + statusCode);
	            Assert.fail("Unexpected response status: " + statusCode);
	    }

	    // Assert the status code (expected 200)
	    Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}
}