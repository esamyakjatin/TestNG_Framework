package admin;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class AnnouncementController extends BaseTest {

	@Test
	public void test01PostBiddingAnnouncements() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

	   
	    request.body(encryptedCredentials);
		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the POST request
		String jsonBody = "{\n" + "  \"category\": \"string\",\n" + "  \"date\": \"string\",\n"
				+ "  \"title\": \"string\",\n" + "  \"message\": \"string\",\n" + "  \"module\": \"string\",\n"
				+ "  \"notice\": \"string\",\n" + "  \"lotId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n"
				+ "  \"lotNo\": \"string\"\n" + "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		request.pathParam("auctionId", auctionId);

		// Send the POST request
		Response response = request.post("/bidding-announcements/{auctionId}");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 201 (Created)
		switch (statusCode) {
		case 200:
			System.out.println("Request succeeded: Data inserted successfully.");
			break;
		case 201:
			System.out.println("Request succeeded: Symmetry created.");
			break;
		case 400:
			System.out.println("Bad Request: Invalid input.");
			break;
		case 403:
			System.out.println("Forbidden: Access is denied.");
			break;
		case 500:
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
			break;
		default:
			System.out.println("Unexpected status code: " + statusCode);
		}

		// Assert that the status code is 200 (OK) or 201 (Created)
		Assert.assertTrue(statusCode == 200 || statusCode == 201,
				"Expected 200 OK or 201 Created, but got: " + statusCode);
	}

	@Test
	public void test02PostBiddingAnnouncementsAll() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the POST request
		String jsonBody = "[{\n" + "  \"category\": \"string\",\n" + "  \"date\": \"string\",\n"
				+ "  \"title\": \"string\",\n" + "  \"message\": \"string\",\n" + "  \"module\": \"string\",\n"
				+ "  \"notice\": \"string\",\n" + "  \"lotId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n"
				+ "  \"lotNo\": \"string\"\n" + "}]";

		// Add the JSON body to the request
		request.body(jsonBody);

		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		request.pathParam("auctionId", auctionId);

		// Send the POST request
		Response response = request.post("/bidding-announcements-all/{auctionId}");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 201 (Created)
		switch (statusCode) {
		case 200:
			System.out.println("Request succeeded: Data inserted successfully.");
			break;
		case 201:
			System.out.println("Request succeeded: Symmetry created.");
			break;
		case 400:
			System.out.println("Bad Request: Invalid input.");
			break;
		case 403:
			System.out.println("Forbidden: Access is denied.");
			break;
		case 500:
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
			break;
		default:
			System.out.println("Unexpected status code: " + statusCode);
		}

		// Assert that the status code is 200 (OK) or 201 (Created)
		Assert.assertTrue(statusCode == 200 || statusCode == 201,
				"Expected 200 OK or 201 Created, but got: " + statusCode);
	}

	@Test
	public void test03GetAnnouncement() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		// request.pathParam("userId", userId);
		// request.pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/annoucement");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());
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
	public void test04Anouncement() {

	    // Set base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    // Set headers
	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);
	    request.header("Content-Type", "application/json");

	    // Correct JSON Body
	    String jsonBody = "{\n" +
	    	    "  \"category\": \"News\",\n" +
	    	    "  \"date\": \"2025-04-18 12:00:00.0\",\n" +
	    	    "  \"title\": \"Test Announcement\",\n" +
	    	    "  \"message\": \"<p>This is the test announcement</p>\",\n" +
	    	    "  \"module\": \"Test Announcement\",\n" +
	    	    "  \"notice\": null,\n" +
	    	    "  \"lotId\": null,\n" +
	    	    "  \"lotNo\": null,\n" +
	    	    "  \"annoucementId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" +
	    	"}";


	    request.body(jsonBody);

	    // POST request
	    Response response = request.post("/annoucement");

	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());
	    System.out.println("---------------Response Details---------------");

	    int statusCode = response.getStatusCode();

	    // Status Handling
	    switch (statusCode) {
	        case 200:
	        case 201:
	            System.out.println("Request succeeded: Announcement created.");
	            break;
	        case 400:
	            System.out.println("Bad Request: Invalid input.");
	            break;
	        case 403:
	            System.out.println("Forbidden: Access is denied.");
	            break;
	        case 500:
	            System.out.println("Internal Server Error: Something went wrong on the server.");
	            break;
	    }

	    // Final assertion
	    Assert.assertTrue(statusCode == 200 || statusCode == 201,
	            "Expected 200 OK or 201 Created, but got: " + statusCode);
	}
	
	@Test
	public void test05EditAnnouncement() {

	    // Set base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    // Set headers
	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);
	    request.header("Content-Type", "application/json");

	    // JSON Body for PUT request (editing announcement)
	    String jsonBody = "{\n" +
	            "  \"category\": \"Updated News\",\n" +
	            "  \"date\": \"2025-04-18 12:00:00.0\",\n" +
	            "  \"title\": \"Updated Announcement Title\",\n" +
	            "  \"message\": \"<p>This is the updated new test announcement.</p>\",\n" +
	            "  \"module\": \"Updated Module\",\n" +
	            "  \"notice\": \"Updated notice content.\",\n" +
	            "  \"lotId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
	            "  \"lotNo\": \"LOT-UPDATED-001\",\n" +
	            "  \"annoucementId\": \"a698a3fc-4faf-48e6-b727-1f2148188e57\"\n" + // ✅ Replace with your actual existing ID
	            "}";

	    request.body(jsonBody);

	    // Send PUT request
	    Response response = request.put("/update/news");

	    // Log response details
	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());
	    System.out.println("---------------Response Details---------------");

	    int statusCode = response.getStatusCode();

	    // Status Handling
	    switch (statusCode) {
	        case 200:
	        case 204:
	            System.out.println("Update succeeded.");
	            break;
	        case 400:
	            System.out.println("Bad Request: Invalid input.");
	            break;
	        case 403:
	            System.out.println("Forbidden: Access is denied.");
	            break;
	        case 404:
	            System.out.println("Not Found: Announcement not found.");
	            break;
	        case 500:
	            System.out.println("Internal Server Error: Something went wrong on the server.");
	            break;
	    }

	    // Final assertion
	    Assert.assertTrue(statusCode == 200 || statusCode == 204,
	            "Expected 200 OK or 204 No Content, but got: " + statusCode);
	}
	
	@Test
	public void test06DeleteAnnouncement() {

	    // Set base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    // Set headers
	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);
	    request.header("Content-Type", "application/json");

	    // Announcement ID to delete (make sure this exists in DB)
	    String annoucementId = "a698a3fc-4faf-48e6-b727-1f2148188e57";

	    // Perform PUT request to delete endpoint
	    Response response = request.put("/delete/news/" + annoucementId);

	    // Log response details
	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());
	    System.out.println("---------------Response Details---------------");

	    int statusCode = response.getStatusCode();

	    // Status Handling
	    switch (statusCode) {
	        case 200:
	        case 204:
	            System.out.println("Soft delete succeeded.");
	            break;
	        case 400:
	            System.out.println("Bad Request: Invalid ID.");
	            break;
	        case 403:
	            System.out.println("Forbidden: Access is denied.");
	            break;
	        case 404:
	            System.out.println("Not Found: Announcement not found.");
	            break;
	        case 500:
	            System.out.println("Internal Server Error: Something went wrong on the server.");
	            break;
	    }

	    // Final assertion
	    Assert.assertTrue(statusCode == 200 || statusCode == 204,
	            "Expected 200 OK or 204 No Content, but got: " + statusCode);
	}




	@Test
	public void test05MasterAnnouncement() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		// request.pathParam("userId", userId);
		// request.pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/master/annoucement");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());
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
	public void test06BiddingAnnouncementsAuctionIdUserId() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/bidding-announcements/{auctionId}/{userId}");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());
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
}
