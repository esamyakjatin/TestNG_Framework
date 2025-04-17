package admin;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;
public class RefreshTokencontroller extends BaseTest{
	 @Test(enabled=false)
	    public void Test01GetAuthRefreshToken() {
	        
	        // Set the base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
	        RequestSpecification request = RestAssured.given();
	        
	        
	        String encryptedCredentials = Constant.adminCredentials; 

			request.body(encryptedCredentials);

	        
	        
	        // Add Authorization header for Bearer Token Authentication
	        String token = Constant.authToken;
	        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
	        
	       
	        // Send the POST request
	        Response response = request.get("/auth/refreshToken");
	        
	        // Print the response status and body for debugging
	        System.out.println("The status received: " + response.statusLine());
	        System.out.println("Response: " + response.getBody().asString());  // Added response logging
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
	        Assert.assertTrue(statusCode == 200 || statusCode == 201, "Expected 200 OK or 201 Created, but got: " + statusCode);
	    }
	}
