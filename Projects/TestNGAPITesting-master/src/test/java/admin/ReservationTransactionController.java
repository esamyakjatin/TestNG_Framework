

//POST/api/v1/create/reservation
//PATCH/api/v1/cancel/{reservationTransactionId}
//GET/api/v1/get/{auctionId}/{userId}
//GET/api/v1/get/all/{auctionId}

package admin;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class  ReservationTransactionController extends BaseTest {

    @Test
    public void test01PostCreateReservation() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
       
        String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
        
        String token = Constant.authToken;
        
        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
        
        // Set the Content-Type header to application/json
        request.header("Content-Type", "application/json");  
        
        String jsonBody = "{\n" +
                "  \"auctionId\": \"2e7f6857-7e1f-4e44-a52e-9d7e6e08b3df\",\n" +
                "  \"auctionName\": \"Diamond202520250129092452\",\n" +
                "  \"noOfPersons\": 2,\n" +
                "  \"slotDate\": \"2025-02-10 00:00:00.0\",\n" +
                "  \"slotTime\": \"2:18 PM - 4:18 PM\",\n" +
                "  \"userId\": \"06836bbc-2cbe-4be5-bb90-429e80775664\",\n" +
                "  \"userNames\": \"user1, user2\",\n" +
                "  \"venueAddress\": \"Mumbai HO\"\n" +
                "}";



        // Add the JSON body to the request
        request.body(jsonBody);
        
        // Send the POST request
        Response response = request.post("/create/reservation");
        
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
    
    @Test
public void test03GetReservationAuctionIdUserId() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        
        String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

        
        String token = Constant.authToken;
        
        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
        
        // Optional: Set headers if required
        request.header("Content-Type", "application/json");
        
        String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
        String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3"; 
        
        // Add path parameters dynamically
       request.pathParam("userId", userId);
        request.pathParam("auctionId", auctionId);
        
        // Send the GET request with query parameters
        Response response = request.get("/get/{auctionId}/{userId}"); 
        
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

        // Assert that the status code is 200 (OK), or change the expectation if 403 is valid
        Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
    }
    
    @Test
public void test04GetReservationAll() {
        
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
        String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3"; 
        
        // Add path parameters dynamically
     //  request.pathParam("userId", userId);
        request.pathParam("auctionId", auctionId);
        
        // Send the GET request with query parameters
        Response response = request.get("/get/all/{auctionId}"); 
        
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

        // Assert that the status code is 200 (OK), or change the expectation if 403 is valid
        Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
    }
}


