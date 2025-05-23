package admin;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class LotDiamondCorrectionController extends BaseTest {

	  @Test
	    public void test01GetCorrectionStockNumber() {
	        
	        // Set the base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
	        RequestSpecification request = RestAssured.given();
	        
	        String encryptedCredentials = Constant.adminCredentials; 

			request.body(encryptedCredentials);
	        
	        String token = Constant.authToken;
	        
	        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
	        
	        // Optional: Set headers if required
	        request.header("Content-Type", "application/json");
	        
	     // String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
	        String stockNumber = "2"; 
	        String auctionId = "d7a28125-9ba3-4ee5-a737-b634b9c7cbc5"; 
	        
	        // Add path parameters dynamically
	     //  request.pathParam("userId", userId);
	        request.pathParam("stockNumber", stockNumber);
	        request.pathParam("auctionId", auctionId);
	        
	        
	        // Send the GET request with query parameters
	        Response response = request.get("/getCorrection/{stockNumber}/{auctionId}"); 
	        
	        if (response.getStatusCode() == 401) {
				System.out.println("Token expired. Please generate a new token.");
				Assert.fail("Request failed due to token expiration.");
			}
	        
	        // Print the response status and body for debugging
	        System.out.println("The status received: " + response.statusLine());
	        System.out.println("Response: " + response.getBody().asString());  // Added response logging
	        System.out.println("---------------Response Details---------------");
	        int statusCode = response.getStatusCode();
	        System.out.println("Status Code: " + statusCode);
	        
	        // Check if the status code is 200 (OK) or 403 (Forbidden)
	        if (statusCode == 403) {
	            System.out.println("Access denied: Invalid credentials or permissions.");
	        } else if (statusCode == 200) {
	            System.out.println("Request succeeded: Access granted.");
	        }

	        // Assert that the status code is 200 (OK), or change the expectation if 403 is valid
	        Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	    }
	  @Test
	  public void test02GetCorrectionAuctionId() {
	        
	        // Set the base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
	        RequestSpecification request = RestAssured.given();
	        
	        String encryptedCredentials = Constant.adminCredentials; 

			request.body(encryptedCredentials);
	        
	        String token = Constant.authToken;
	        
	        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
	        
	        // Optional: Set headers if required
	        request.header("Content-Type", "application/json");
	        
	     // String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
	      //  String stockNumber = "2"; 
	        String auctionId = "d7a28125-9ba3-4ee5-a737-b634b9c7cbc5"; 
	        
	        // Add path parameters dynamically
	     //  request.pathParam("userId", userId);
	        //request.pathParam("stockNumber", stockNumber);
	        request.pathParam("auctionId", auctionId);
	        
	        
	        // Send the GET request with query parameters
	        Response response = request.get("/getAll/Correction/{auctionId}"); 
	        
	        if (response.getStatusCode() == 401) {
				System.out.println("Token expired. Please generate a new token.");
				Assert.fail("Request failed due to token expiration.");
			}
	        
	        // Print the response status and body for debugging
	        System.out.println("The status received: " + response.statusLine());
	        System.out.println("Response: " + response.getBody().asString());  // Added response logging
	        System.out.println("---------------Response Details---------------");
	        int statusCode = response.getStatusCode();
	        System.out.println("Status Code: " + statusCode);
	        
	        // Check if the status code is 200 (OK) or 403 (Forbidden)
	        if (statusCode == 403) {
	            System.out.println("Access denied: Invalid credentials or permissions.");
	        } else if (statusCode == 200) {
	            System.out.println("Request succeeded: Access granted.");
	        }

	        // Assert that the status code is 200 (OK), or change the expectation if 403 is valid
	        Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	    }
}
