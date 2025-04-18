package admin;

import org.testng.annotations.Test; // ✅ Correct TestNG import
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;


public class NoticeController extends BaseTest{
	
	@Test
	public void test01PutUpdateNotice() {

	    // Set the base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();
	    
	    // Use stored encrypted credentials and token
	    String encryptedCredentials = Constant.adminCredentials;
	    request.body(encryptedCredentials);

	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);
	    request.header("Content-Type", "application/json");

	    // JSON body for updating notice
	    String jsonBody = "{\n" +
	            "  \"noticeId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
	            "  \"notice\": \"Important update: new auction rules apply from next week.\"\n" +
	            "}";

	    request.body(jsonBody);

	    // Send the PUT request
	    Response response = request.put("/update/notice/");

	    // Handle token expiration
	    if (response.getStatusCode() == 401) {
	        System.out.println("Token expired. Please generate a new token.");
	        Assert.fail("Request failed due to token expiration.");
	    }

	    // Print the response details
	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());
	    System.out.println("---------------Response Details---------------");
	    int statusCode = response.getStatusCode();
	    System.out.println("Status Code: " + statusCode);

	    if (statusCode == 403) {
	        System.out.println("Access denied: Invalid credentials or permissions.");
	    } else if (statusCode == 200) {
	        System.out.println("Request succeeded: Notice updated successfully.");
	    }

	    // Assert success
	    Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}

	 @Test
	    public void test02PutActivateDeactivateNotice() {

	        // Set base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1";
	        RequestSpecification request = RestAssured.given();

	        // Auth and headers
	        String token = Constant.authToken;
	        request.header("Authorization", "Bearer " + token);
	        request.header("Content-Type", "application/json");

	        // Path variable
	        String noticeId = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
	        request.pathParam("noticeId", noticeId);

	        // Send PUT request
	        Response response = request.put("/activate/deactivate/notice/{noticeId}");

	        // Handle token expiration
	        if (response.getStatusCode() == 401) {
	            System.out.println("Token expired. Please generate a new token.");
	            Assert.fail("Request failed due to token expiration.");
	        }

	        // Print response
	        System.out.println("The status received: " + response.statusLine());
	        System.out.println("Response: " + response.getBody().asString());
	        System.out.println("---------------Response Details---------------");

	        int statusCode = response.getStatusCode();
	        System.out.println("Status Code: " + statusCode);

	        if (statusCode == 403) {
	            System.out.println("Access denied: Invalid credentials or permissions.");
	        } else if (statusCode == 200) {
	            System.out.println("Request succeeded: Notice activated/deactivated successfully.");
	        }

	        Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	    }
	 
	 @Test
	    public void test03PostCreateNotice() {

	        // Set base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1";
	        RequestSpecification request = RestAssured.given();

	        // Auth and headers
	        String token = Constant.authToken;
	        request.header("Authorization", "Bearer " + token);
	        request.header("Content-Type", "application/json");

	        // JSON body for creating notice
	        String jsonBody = "{\n" +
	                "  \"notice\": \"New auction will be held on 25th April at 10 AM.\"\n" +
	                "}";

	        // Set request body
	        request.body(jsonBody);

	        // Send POST request
	        Response response = request.post("/create/notice");

	        // Handle token expiration
	        if (response.getStatusCode() == 401) {
	            System.out.println("Token expired. Please generate a new token.");
	            Assert.fail("Request failed due to token expiration.");
	        }

	        // Print response details
	        System.out.println("The status received: " + response.statusLine());
	        System.out.println("Response: " + response.getBody().asString());
	        System.out.println("---------------Response Details---------------");

	        int statusCode = response.getStatusCode();
	        System.out.println("Status Code: " + statusCode);

	        if (statusCode == 403) {
	            System.out.println("Access denied: Invalid credentials or permissions.");
	        } else if (statusCode == 201 || statusCode == 200) {
	            System.out.println("Request succeeded: Notice created successfully.");
	        }

	        // Assert based on expected status
	        Assert.assertTrue(statusCode == 201 || statusCode == 200,
	                "Expected 200 or 201, but got: " + statusCode);
	    }

	 
	 @Test
	    public void test04GetAllNotices() {

	        // Set base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1";
	        RequestSpecification request = RestAssured.given();

	        // Auth headers
	        String token = Constant.authToken;
	        request.header("Authorization", "Bearer " + token);
	        request.header("Content-Type", "application/json");

	        // Send GET request (no body for GET)
	        Response response = request.get("/getAll/Notices");

	        // Handle token expiration
	        if (response.getStatusCode() == 401) {
	            System.out.println("Token expired. Please generate a new token.");
	            Assert.fail("Request failed due to token expiration.");
	        }

	        // Print response details
	        System.out.println("The status received: " + response.statusLine());
	        System.out.println("Response: " + response.getBody().asString());
	        System.out.println("---------------Response Details---------------");

	        int statusCode = response.getStatusCode();
	        System.out.println("Status Code: " + statusCode);

	        if (statusCode == 403) {
	            System.out.println("Access denied: Invalid credentials or permissions.");
	        } else if (statusCode == 200) {
	            System.out.println("Request succeeded: Notices retrieved successfully.");
	        }

	        // Assert success
	        Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	    }
	 
	 @Test
	    public void test05GetActiveNotice() {

	        // Set base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1";
	        RequestSpecification request = RestAssured.given();

	        // Auth headers
	        String token = Constant.authToken;
	        request.header("Authorization", "Bearer " + token);
	        request.header("Content-Type", "application/json");

	        // Send GET request (no body required)
	        Response response = request.get("/get/activeNotice");

	        // Handle token expiration
	        if (response.getStatusCode() == 401) {
	            System.out.println("Token expired. Please generate a new token.");
	            Assert.fail("Request failed due to token expiration.");
	        }

	        // Print response details
	        System.out.println("The status received: " + response.statusLine());
	        System.out.println("Response: " + response.getBody().asString());
	        System.out.println("---------------Response Details---------------");

	        int statusCode = response.getStatusCode();
	        System.out.println("Status Code: " + statusCode);

	        if (statusCode == 403) {
	            System.out.println("Access denied: Invalid credentials or permissions.");
	        } else if (statusCode == 200) {
	            System.out.println("Request succeeded: Active notice retrieved successfully.");
	        }

	        // Assert success
	        Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	    }
}
