package admin;

import java.io.File;

import org.testng.annotations.Test;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;


public class AutoBiddingController extends BaseTest {
	

	@Test(enabled = false)
	public void Test01AutoBidsAuctionId() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Specify the auctionId as a path parameter
		String auctionId = "693c945d-da0a-4e41-b93a-081e0f033662";
		request.pathParam("auctionId", auctionId);

		// Send the GET request
		Response response = request.get("/auto-bids/{auctionId}");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		switch (statusCode) {
		case 200:
			System.out.println("Request succeeded: Slot details retrieved successfully.");
			break;
		case 403:
			System.out.println("Forbidden: Access is denied.");
			break;
		case 404:
			System.out.println("Not Found: The specified resource was not found.");
			break;
		case 500:
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
			break;
		default:
			System.out.println("Unexpected status code: " + statusCode);
		}

		// Assert that the status code is 200 (OK)
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}
	
	@org.testng.annotations.Test
	
	public void Test02PutAutoBidExcel() {

	    // Set the base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    // Add Authorization header for Bearer Token Authentication
	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token); 

	    // Specify the auctionId as a path parameter
	    String auctionId = "693c945d-da0a-4e41-b93a-081e0f033662";
	    request.pathParam("auctionId", auctionId);

	    // Set Content-Type to multipart/form-data
	    request.contentType("multipart/form-data");
		File file = null;
		try {

			// Specify the file to upload (replace with your actual file path)
			String filePath = Constant.FILE_PATH + "/src/test/resources/AutoBid_Excel_Upload.xlsx";
			System.out.println("filePath: " + filePath);
			file = new File(filePath); // Update with
		} catch (Exception e) {
			
			// TODO: handle exception
			System.out.println("Exception due to " + e.toString());
		}

	    // Attach the Excel file
	    request.multiPart("file", file);

	    // Send the PUT request with file upload
	    Response response = request.post("/autoBid/excel/{auctionId}");

	    // Print the response status and body for debugging
	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());

	    int statusCode = response.getStatusCode();
	    System.out.println("Status Code: " + statusCode);

	    // Check response status
	    switch (statusCode) {
	        case 200:
	            System.out.println("Request succeeded: Excel file uploaded successfully.");
	            break;
	        case 400:
	            System.out.println("Bad Request: Please check the file format.");
	            break;
	        case 403:
	            System.out.println("Forbidden: Access is denied.");
	            break;
	        case 404:
	            System.out.println("Not Found: The specified resource was not found.");
	            break;
	        case 500:
	            System.out.println("Internal Server Error: The server encountered an unexpected condition.");
	            break;
	        default:
	            System.out.println("Unexpected status code: " + statusCode);
	    }

	    // Assert that the status code is 200 (OK)
	    Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}
	

}
