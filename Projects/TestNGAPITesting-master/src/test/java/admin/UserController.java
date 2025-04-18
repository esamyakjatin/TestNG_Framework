package admin;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

class UserController extends BaseTest {

	@Test(description = "/api/v1/user/{userId}")
	public void test01GetUserDetails() {

		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";

		request.pathParam("userId", userId);

		Response response = request.get("/user/{userId}");

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
	public void UpdateUserUpload() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to multipart/form-data for file upload
		request.header("Content-Type", "multipart/form-data");

		String filePath = Constant.FILE_PATH + "/src/test/resources/user_data.xlsx";
		System.out.println("filePath: " + filePath);
		File file = new File(filePath); // Update with		

		// Add the file as part of the multipart request
		request.multiPart("file", file);

		// Send the POST request
		Response response = request.post("/update/users/upload");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString()); // Added response logging
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Assert that the status code is 200 (OK) or 201 (Created)
		Assert.assertTrue(statusCode == 200 || statusCode == 201,
				"Expected 200 OK or 201 Created, but got: " + statusCode);
	}

	@Test(enabled=false)
	public void test05UpdateUploadedUsers() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "b0f0bc59-9196-4ee0-b5b3-e2dadb1341c7";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		// .pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/user/supplier/{userId}");

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
	public void test06GetUserSupplierDetails() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		// .pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/user/kyc/{userId}");

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
	public void test07GetKycByUserId() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";
		String inventory = "";

		// Add path parameters dynamically
		request.pathParam("userId", userId).pathParam("auctionId", auctionId).pathParam("inventory", inventory);

		// Send the GET request with query parameters
		Response response = request.get("/user/account-summary/{userId}/{auctionId}/{inventory}");

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
	public void test08GetSupplierDetailsByAuctionId() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);


		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// String userId="7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		request// .pathParam("userId", userId)
				.pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/supplier/{auctionId}");

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
	public void test09GetAppliedUsersKyc() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// String userId="7828500F-5781-40D5-9E61-ADF2A09EB993";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		// request//.pathParam("userId", userId)
		// .pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/kyc/applied/users");

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
	public void test10GetInactiveUsers() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// String userId="7828500F-5781-40D5-9E61-ADF2A09EB993";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		// request//.pathParam("userId", userId)
		// .pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/inactive/users");

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

	@Test(enabled = false) // This api taking so much time so we have disabbled for now...
	public void test11GetAllSuppliers() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);


		// Add Authorization header for Bearer Token Authentication
		// Replace 'your_token_here' with the actual Bearer token you received
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// String userId="7828500F-5781-40D5-9E61-ADF2A09EB993";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		// request//.pathParam("userId", userId)
		// .pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/active/users");

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
	
	@Test(description = "/api/v1/all/suppliers")
	public void test12GetAllSuppliers() {

		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		request.header("Content-Type", "application/json");

		//String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";

		//request.pathParam("userId", userId);

		Response response = request.get("/all/suppliers");

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
	public void test13PutUserUserId() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

	
		String jsonBody = "{\n" +
				"  \"firstName\": \"admin\",\n" +
				"  \"lastName\": \"l\",\n" +
				"  \"midName\": \"m\",\n" +
				"  \"email\": \"admin@pmail.com\",\n" +
				"  \"password\": null,\n" +
				"  \"userName\": \"admin\",\n" +
				"  \"phoneNumber\": \"1234567895\",\n" +
				"  \"faxNo\": \"46515465\",\n" +
				"  \"companyName\": \"samyak\",\n" +
				"  \"email2\": null,\n" +
				"  \"role\": \"Auctioneer\",\n" +
				"  \"status\": \"ACTIVE\",\n" +
				"  \"referenceName\": \"samyak\",\n" +
				"  \"remark\": \"user added\",\n" +
				"  \"addressInfo\": {\n" +
				"    \"type\": \"office\",\n" +
				"    \"houseNo\": \"651156\",\n" +
				"    \"city\": \"mumbai\",\n" +
				"    \"zipCode\": \"66651\",\n" +
				"    \"state\": \"maharashtra\",\n" +
				"    \"country\": \"China\",\n" +
				"    \"countryId\": \"9b6548a1-a3fc-486f-bb69-c2aef8549b3c\"\n" +
				"  },\n" +
				"  \"active\": null,\n" +
				"  \"kycStatus\": \"Yes\",\n" +
				"  \"userInterest\": \"Both(Diamond & Jewellery)\",\n" +
				"  \"ledgerType\": \"No Ledger\",\n" +
				"  \"isSupplier\": \"Yes\",\n" +
				"  \"noOfBoxes\": null,\n" +
				"  \"pendingBoxes\": null,\n" +
				"  \"auctionIds\": null,\n" +
				"  \"validIdProofName\": null,\n" +
				"  \"companyIdProof\": null,\n" +
				"  \"userId\": \"7b6e4bdc-c310-4c0a-a587-29839da36de0\",\n" +
				"  \"soldCommision\": null,\n" +
				"  \"unsoldCommision\": null,\n" +
				"  \"unsoldCommCalculation\": null\n" +
				"}";

		// Add the JSON body to the request
		request.body(jsonBody);

	//	String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";
		String userId="7b6e4bdc-c310-4c0a-a587-29839da36de0";

	//	request.pathParam("auctionId", auctionId);
		request.pathParam("userId", userId);

		// Send the POST request
		Response response = request.put("/user/{userId}");

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
	public void test14DeleteUserUserId() {

	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    String token = Constant.authToken;

	    request
	        .header("Authorization", "Bearer " + token)
	        .header("Content-Type", "application/json");

	    String userId = "c288c726-9bc9-4c61-87f9-3deb0e49c9cc";
	    request.pathParam("userId", userId);

	    Response response = request.delete("/user/{userId}");

	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());

	    int statusCode = response.getStatusCode();
	    System.out.println("Status Code: " + statusCode);

	    switch (statusCode) {
	        case 200:
	            System.out.println("Request succeeded: User deleted.");
	            break;
	        case 204:
	            System.out.println("Request succeeded: No Content.");
	            break;
	        case 400:
	            System.out.println("Bad Request: Invalid input.");
	            break;
	        case 403:
	            System.out.println("Forbidden: Access is denied.");
	            break;
	        case 500:
	            System.out.println("Internal Server Error.");
	            break;
	        default:
	            System.out.println("Unexpected status code: " + statusCode);
	    }

	    Assert.assertTrue(
	        statusCode == 200 || statusCode == 204,
	        "Expected 200 OK or 204 No Content, but got: " + statusCode
	    );
	}

	
}
