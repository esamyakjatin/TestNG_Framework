package admin;

import java.io.File;
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

		    //request.queryParam("payload", encryptedCredentials);
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

	    // Add query parameters
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
	            String filePath = "C:\\WorkSpaces\\Swastik2425_Workspace\\Postsample\\Testing/lot-details.xlsx";

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

		// Add query parameters
		 String encryptedCredentials = Constant.adminCredentials; 

		 request.body(encryptedCredentials);



		// Add Bearer token in Authorization header
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token);

		// Add Content-Type header
		request.header("Content-Type", "application/json");
		
		// Set the Content-Type header to multipart/form-data for file upload
	    request.header("Content-Type", "multipart/form-data");  
	    
	    // Specify the file to upload (replace with your actual file path)
	    File file = new File("C:\\WorkSpaces\\Swastik2425_Workspace\\Postsample\\Testing/Unnamed1.pdf");  // Update with actual file path
	    
	    // Add the file as part of the multipart request
	    request.multiPart("file", file);

		// Add path parameter
		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";
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
	
	 @Test
	    public void testRejectOffer() {
	        // Base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1";

	        // Replace with actual lotId and status
	        String lotId = "82d2ebdf-01a3-49c2-9a26-6b3e5181ce1b";
	        String negotiationStatus = "REJECTED";  // Or any valid status your backend supports

	        // PUT request to reject offer
	        Response response = RestAssured.given()
	                .header("Authorization", "Bearer " + Constant.authToken) // Add if token is needed
	                .put("/lot/reject-offer/" + lotId + "/" + negotiationStatus);

	        // Log and assert
	        System.out.println("Status Code: " + response.getStatusCode());
	        System.out.println("Response Body: " + response.getBody().asString());

	        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK on rejecting offer");
	    }
	 
	 @Test
	    public void testBidderOffer() {
	        // Set base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1";

	        // Replace with actual values
	        String lotId = "82d2ebdf-01a3-49c2-9a26-6b3e5181ce1b";  // Example UUID
	        double amount = 12500.50;  // Example amount

	        // Send PUT request
	        Response response = RestAssured.given()
	                .header("Authorization", "Bearer " + Constant.authToken) // Add if needed
	                .put("/lot/bidder-offer/" + lotId + "/" + amount);

	        // Debugging output
	        System.out.println("Status Code: " + response.getStatusCode());
	        System.out.println("Response Body: " + response.getBody().asString());

	        // Assertion
	        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK for bidder offer");
	    }
	 
	 @Test
	 public void testBoxWiseCount() {
		    // Base URI
		    RestAssured.baseURI = "http://localhost:8080/api/v1";

		    // Path variables
		    String boxNo = "BOX001";
		    String supplierId = "7b6e4bdc-c310-4c0a-a587-29839da36de0";
		    String auctionId = "d7a28125-9ba3-4ee5-a737-b634b9c7cbc5";

		    // Send GET request
		    Response response = RestAssured
		        .given()
		        .header("Authorization", "Bearer " + Constant.authToken)
		        .get("/lot/boxWiseCount/" + boxNo + "/" + supplierId + "/" + auctionId);

		    // Debug output
		    System.out.println("Status Code: " + response.statusCode());
		    System.out.println("Response Body:\n" + response.getBody().asPrettyString());

		    // Parse response body as integer
		    String responseString = response.getBody().asString().trim();
		    int count = Integer.parseInt(responseString);

		    // Assertions
		    Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK");
		    Assert.assertTrue(count >= 0, "Count should be non-negative");
		}
	 @Test
	    public void testGetGemStones() {
	        // Base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1";

	        // Send GET request to fetch all gem stones
	        Response response = RestAssured
	            .given()
	            .header("Authorization", "Bearer " + Constant.authToken) // Replace with actual auth token
	            .get("/gem-stones");

	        // Debug output
	        System.out.println("Status Code: " + response.statusCode());
	        System.out.println("Response Body:\n" + response.getBody().asPrettyString());

	        // Assertions
	        Assert.assertEquals(200, response.statusCode()); // Check for 200 OK status
	        Assert.assertTrue(response.getBody().asString().trim().length() > 0); // Ensure response is not empty

	        // Parse response body to ensure it is a valid JSON array
	        try {
	            // Assuming that the response is a JSON array
	            String responseBody = response.getBody().asString().trim();
	            Assert.assertTrue(responseBody.startsWith("[") && responseBody.endsWith("]"));
	        } catch (Exception e) {
	            Assert.fail("Failed to parse response body: " + e.getMessage());
	        }
	 }
	 
	 
	 
}