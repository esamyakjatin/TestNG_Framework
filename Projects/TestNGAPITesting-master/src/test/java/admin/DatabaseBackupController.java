package admin;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;
public class DatabaseBackupController extends BaseTest{
 
    @Test
    public void test01PostStart() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

        String token = Constant.authToken;

           
        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
        
        // Optional: Set headers if required
        request.header("Content-Type", "application/json");
        
      // String userId="7828500F-5781-40D5-9E61-ADF2A09EB993";
       //String supplierId=" C2BEE3F2-5B36-4D81-AD16-F184574241F1";
       // String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3"; 
       
         	
        // Add path parameters dynamically
       // request.pathParam("supplierId", supplierId)
        //.pathParam("auctionId", auctionId);
        
        // Send the GET request with query parameters
        Response response = request.post("/start");
        
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
    @Test(enabled=false)
   public void test02GetStatus() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
        
        String token = Constant.authToken;

        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
        
        // Optional: Set headers if required
        request.header("Content-Type", "application/json");
        
      // String userId="7828500F-5781-40D5-9E61-ADF2A09EB993";
       //String supplierId=" C2BEE3F2-5B36-4D81-AD16-F184574241F1";
       // String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3"; 
       
         	
        // Add path parameters dynamically
       // request.pathParam("supplierId", supplierId)
        //.pathParam("auctionId", auctionId);
        
        // Send the GET request with query parameters
        Response response = request.get("/status");
        
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
 public void test03GetBackupurl() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
        
        String token = Constant.authToken;

        
        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
        
        // Optional: Set headers if required
        request.header("Content-Type", "application/json");
        
      // String userId="7828500F-5781-40D5-9E61-ADF2A09EB993";
       //String supplierId=" C2BEE3F2-5B36-4D81-AD16-F184574241F1";
       // String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3"; 
       
         	
        // Add path parameters dynamically
       // request.pathParam("supplierId", supplierId)
        //.pathParam("auctionId", auctionId);
        
        // Send the GET request with query parameters
        Response response = request.get("/backup-url");
        
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
