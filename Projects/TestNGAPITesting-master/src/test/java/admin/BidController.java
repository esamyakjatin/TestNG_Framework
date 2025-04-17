package admin;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class BidController extends BaseTest {
	@Test
	public void Test01PutBidUpdate() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		// Add query parameters for GET request (username, password, lotDiamondInfo)

		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the PUT request

		String jsonBody = "{\n" + "  \"lotId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n"
				+ "  \"auctionId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n"
				+ "  \"userId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"currencyBidAmount\": 0,\n"
				+ "  \"bidDateTime\": \"2024-12-12T07:27:06.973Z\",\n" + "  \"close\": true,\n" + "  \"active\": true\n"
				+ "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// String auctionId = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";

		// request//.pathParam("userId", userId)
		// .pathParam("auctionId", auctionId);

		String proxyBidId = "aa11b4c1-86bb-4843-9628-1a45d61cb7bf";
	    String proxyBidAmount = "100000";

	    request.pathParam("proxyBidId", proxyBidId);
	    request.pathParam("proxyBidAmount", proxyBidAmount);
		
		// Send the PUT request
		Response response = request.put("/bid/update/{proxyBidId}/{proxyBidAmount}");

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
	public void Test02PutBidUpdateJewellery() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		
		// Add query parameters for GET request (username, password, lotDiamondInfo)

		// Add Authorization header for Bearer Token Authentication
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the PUT request

		String jsonBody = "{\n" + "  \"lotId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n"
				+ "  \"auctionId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n"
				+ "  \"userId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"currencyBidAmount\": 0,\n"
				+ "  \"bidDateTime\": \"2024-12-12T07:27:06.973Z\",\n" + "  \"close\": true,\n" + "  \"active\": true\n"
				+ "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// String auctionId = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";

		// request//.pathParam("userId", userId)
		// .pathParam("auctionId", auctionId);

		// Send the PUT request
		Response response = request.put("/bid/update/jewellery");

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
	public void Test03PostMakeWinners() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		
		// Add Authorization header for Bearer Token Authentication
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json (even though no body is sent,
		// it's good practice)
		request.header("Content-Type", "application/json");

		// Define the auctionId path parameter
		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		request.pathParam("auctionId", auctionId);

		// Send the POST request to the specified endpoint
		Response response = request.post("/make-winners/{auctionId}");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 201 (Created)
		if (statusCode == 200) {
			System.out.println("Request succeeded: Winners made successfully.");
		} else if (statusCode == 201) {
			System.out.println("Request succeeded: Winners created.");
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

	@Test
	public void Test04GetBiddersInfo() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		
		// Add query parameters for GET request (username, password)

		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// String userId="7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "d68848a2-67a3-453f-85af-2a0aaa14b9e2";
		// String countryId = "435a09ab-ad6e-11ef-8d40-c8d3ffbc6ac6";
//  String supplierId = "88be816b-c3e9-432b-9111-45af8ba70bfb"; 
		// String boxNo = "BOX1";

		// Add path parameters dynamically
		// request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);
		// request.pathParam("countryId", countryId);
		// request.pathParam("supplierId", supplierId);
		// request.pathParam("boxNo", boxNo);

		// Send the GET request with query parameters
		Response response = request.get("/biders/info/{auctionId}");

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

	@Test(enabled=false)
	public void Test06GetBidCount() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "d68848a2-67a3-453f-85af-2a0aaa14b9e2";
		// String countryId = "435a09ab-ad6e-11ef-8d40-c8d3ffbc6ac6";
//  String supplierId = "88be816b-c3e9-432b-9111-45af8ba70bfb"; 
		// String boxNo = "BOX1";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);
		// request.pathParam("countryId", countryId);
		// request.pathParam("supplierId", supplierId);
		// request.pathParam("boxNo", boxNo);

		// Send the GET request with query parameters
		Response response = request.get("/bids/{userId}/{auctionId}");

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

	@Test(enabled=false)
	public void Test08GetMyBid() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		
		// Add query parameters for GET request (username, password)

		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "d68848a2-67a3-453f-85af-2a0aaa14b9e2";
		// String countryId = "435a09ab-ad6e-11ef-8d40-c8d3ffbc6ac6";
//  String supplierId = "88be816b-c3e9-432b-9111-45af8ba70bfb"; 
		// String boxNo = "BOX1";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);
		// request.pathParam("countryId", countryId);
		// request.pathParam("supplierId", supplierId);
		// request.pathParam("boxNo", boxNo);

		// Send the GET request with query parameters
		Response response = request.get("/bid/getWonBidsAndLostBid/{userId}/{auctionId}");

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

	@Test (enabled=false)
	public void Test09GetLoseCount() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		
		// Add query parameters for GET request (username, password)

		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "d68848a2-67a3-453f-85af-2a0aaa14b9e2";
		// String countryId = "435a09ab-ad6e-11ef-8d40-c8d3ffbc6ac6";
//  String supplierId = "88be816b-c3e9-432b-9111-45af8ba70bfb"; 
		// String boxNo = "BOX1";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);
		// request.pathParam("countryId", countryId);
		// request.pathParam("supplierId", supplierId);
		// request.pathParam("boxNo", boxNo);

		// Send the GET request with query parameters
		Response response = request.get("/api/v1/bid/getLoseCount/{userId}/{auctionId}");

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
	public void Test11GetAllCounts() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		
		// Add query parameters for GET request (username, password)

		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "d68848a2-67a3-453f-85af-2a0aaa14b9e2";
		// String countryId = "435a09ab-ad6e-11ef-8d40-c8d3ffbc6ac6";
//  String supplierId = "88be816b-c3e9-432b-9111-45af8ba70bfb"; 
		// String boxNo = "BOX1";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);
		// request.pathParam("countryId", countryId);
		// request.pathParam("supplierId", supplierId);
		// request.pathParam("boxNo", boxNo);

		// Send the GET request with query parameters
		Response response = request.get("/bid/getAllCounts/{userId}/{auctionId}");

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
	public void Test12GetBidHistory() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		
		// Add query parameters for GET request (username, password)

		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		request.header("Content-Type", "application/json");

		// String userId="7828500F-5781-40D5-9E61-ADF2A09EB993";
		String lotId = "6bdbbe90-30d3-4c55-80f8-e1ce2bdb9c88";
		// String countryId = "435a09ab-ad6e-11ef-8d40-c8d3ffbc6ac6";
//  String supplierId = "88be816b-c3e9-432b-9111-45af8ba70bfb"; 
		// String boxNo = "BOX1";

		// Add path parameters dynamically
		// request.pathParam("userId", userId);
		request.pathParam("lotId", lotId);
		// request.pathParam("countryId", countryId);
		// request.pathParam("supplierId", supplierId);
		// request.pathParam("boxNo", boxNo);

		// Send the GET request with query parameters
		Response response = request.get("/bid-history/{lotId}");

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
	public void Test13GetBidDelete() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		
		// Add query parameters for GET request (username, password, lotDiamondInfo)

		// Add Authorization header for Bearer Token Authentication
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Parameters for the DELETE request
		String auctionId = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String lotId = "ca731c4d-b682-464a-8592-fac7c4963b42";

		request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);
		request.pathParam("lotId", lotId);
		// Send the DELETE request
		Response response = request.delete("/bid/delete/{userId}/{auctionId}/{lotId}");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK), 201 (Created), or 204 (No Content)
		if (statusCode == 200) {
			System.out.println("Request succeeded: Resource deleted successfully.");
		} else if (statusCode == 201) {
			System.out.println("Request succeeded: Resource created successfully.");
		} else if (statusCode == 204) {
			System.out.println("Request succeeded: Resource deleted with no content returned.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 404) {
			System.out.println("Not Found: The resource could not be found.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200, 201, or 204 (OK, Created, No Content)
		Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 204,
				"Expected 200 OK, 201 Created, or 204 No Content, but got: " + statusCode);
	}
}