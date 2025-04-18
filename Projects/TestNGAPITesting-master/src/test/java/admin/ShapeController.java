package admin;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class ShapeController extends BaseTest {

	@Test
	public void testcreateSymmetry01() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		 String encryptedCredentials = Constant.adminCredentials; 

		    //request.queryParam("payload", encryptedCredentials);
		 request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token);

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the POST request
		String jsonBody = "{\n" + "  \"desc\": \"string\",\n" + "  \"code\": \"string\",\n" + "  \"srNo\": 0,\n"
				+ "  \"symmetryId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"name\": \"string\",\n"
				+ "  \"count\": 0,\n" + "  \"createdDate\": \"string\"\n" + "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";
		String symmetryId = "cc5e97fe-b131-11ef-a57f-c8d3ffbc6ac6";

		// Add path parameters dynamically
		// request.pathParam("userId", userId);
		// request.pathParam("auctionId", auctionId);
		request.pathParam("symmetryId", symmetryId);

		// Send the POST request
		Response response = request.put("/symmetry/{symmetryId}");

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

	@Test
	public void testcreateSymmetry02() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		 String encryptedCredentials = Constant.adminCredentials; 

		 request.body(encryptedCredentials);


		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token);
		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		String uniqueName = "Ravi" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		
		// Create the JSON body for the POST request
		String jsonBody = "{\n" + "  \"desc\": \"Ravi\",\n" + "  \"code\": \"ghi\",\n" + "  \"srNo\": 7,\n"
				+ "  \"name\": \""+uniqueName+"\"\n" + // Removed the trailing comma here
				"}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// Send the POST request
		Response response = request.post("/symmetry");

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

	@Test
	public void testcreateSymmetry03() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		// Add Authorization header for Bearer Token Authentication
		 String encryptedCredentials = Constant.adminCredentials; 

		 request.body(encryptedCredentials);


		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token);

		// Set the Content-Type header to multipart/form-data for file upload
		request.header("Content-Type", "multipart/form-data");

		// Specify the file to upload (replace with your actual file path)
		File file = new File("C:\\WorkSpaces\\Swastik2425_Workspace\\Postsample\\Testing/01.xlsx"); // Update with actual
																								// file path

		// Add the file as part of the multipart request
		request.multiPart("file", file);

		// Send the POST request
		Response response = request.post("/symmetry/excel");

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

	@Test
	public void testcreateSymmetry04() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		// Add Authorization header for Bearer Token Authentication
		 String encryptedCredentials = Constant.adminCredentials; 

		 request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token);

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// String userId="7828500F-5781-40D5-9E61-ADF2A09EB993";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		// request//.pathParam("userId", userId)
		// .pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/symmetries");

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
	public void test08UpdateShape() {

	    // Set base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    // Set headers
	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);
	    request.header("Content-Type", "application/json");

	    // Shape ID to update
	    String shapeId = "584f6f69-d00e-11ef-9169-06f17fc4baf3"; // Replace with a real shape UUID

	    // Request JSON body
	    String jsonBody = "{\n" +
	            "  \"desc\": \"Updated shape description\",\n" +
	            "  \"code\": \"SHAPE001\",\n" +
	            "  \"srNo\": 1,\n" +
	            "  \"shapeId\": \"" + shapeId + "\",\n" +
	            "  \"name\": \"Round Brilliant\",\n" +
	            "  \"count\": 10,\n" +
	            "  \"createdDate\": \"2025-04-18\"\n" +
	            "}";

	    // Attach request body
	    request.body(jsonBody);

	    // Perform PUT request
	    Response response = request.put("/shape/" + shapeId);

	    // Log response
	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());
	    System.out.println("---------------Response Details---------------");

	    int statusCode = response.getStatusCode();

	    // Status handling
	    switch (statusCode) {
	        case 200:
	        case 204:
	            System.out.println("Update succeeded.");
	            break;
	        case 400:
	            System.out.println("Bad Request.");
	            break;
	        case 403:
	            System.out.println("Forbidden: Access denied.");
	            break;
	        case 404:
	            System.out.println("Shape not found.");
	            break;
	        case 500:
	            System.out.println("Internal server error.");
	            break;
	    }

	    // Final assertion
	    Assert.assertTrue(statusCode == 200 || statusCode == 204,
	            "Expected 200 OK or 204 No Content, but got: " + statusCode);
	}
	
	@Test
	public void test09DeleteShape() {

	    // Set base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    // Set headers
	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);

	    // Shape ID to delete
	    String shapeId = "3fa85f64-5717-4562-b3fc-2c963f66afa6"; // Replace with actual UUID to delete

	    // Perform DELETE request
	    Response response = request.delete("/shape/" + shapeId);

	    // Log response
	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());
	    System.out.println("---------------Response Details---------------");

	    int statusCode = response.getStatusCode();

	    // Status handling
	    switch (statusCode) {
	        case 200:
	        case 204:
	            System.out.println("Delete succeeded.");
	            break;
	        case 400:
	            System.out.println("Bad Request.");
	            break;
	        case 403:
	            System.out.println("Forbidden: Access denied.");
	            break;
	        case 404:
	            System.out.println("Shape not found.");
	            break;
	        case 500:
	            System.out.println("Internal server error.");
	            break;
	    }

	    // Final assertion
	    Assert.assertTrue(statusCode == 200 || statusCode == 204,
	            "Expected 200 OK or 204 No Content, but got: " + statusCode);
	}

	@Test
	public void test10CreateShape() {

	    // Set base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    // Set headers
	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);
	    request.header("Content-Type", "application/json");

	    // JSON body (excluding shapeId, which is generated by backend)
	    String jsonBody = "{\n" +
	            "  \"desc\": \"Round EED Brilliant\",\n" +
	            "  \"code\": \"RB\",\n" +
	            "  \"srNo\": 18,\n" +
	            "  \"name\": \"Round\",\n" +
	            "  \"count\": 0,\n" +
	            "  \"createdDate\": \"2025-04-18\"\n" +
	            "}";

	    request.body(jsonBody);

	    // POST request
	    Response response = request.post("/shape");

	    // Log response
	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());
	    System.out.println("---------------Response Details---------------");

	    int statusCode = response.getStatusCode();

	    // Status handling
	    switch (statusCode) {
	        case 200:
	        case 201:
	            System.out.println("Shape created successfully.");
	            break;
	        case 400:
	            System.out.println("Bad Request: Invalid input.");
	            break;
	        case 403:
	            System.out.println("Forbidden: Access denied.");
	            break;
	        case 500:
	            System.out.println("Internal Server Error.");
	            break;
	    }

	    // Final assertion
	    Assert.assertTrue(statusCode == 200 || statusCode == 201,
	            "Expected 200 OK or 201 Created, but got: " + statusCode);
	}
	
	@Test
	public void test11UploadShapeExcel() {

	    // Set base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    // Set headers
	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);
	    request.contentType("multipart/form-data");

	    // Path to your Excel file (update the path as needed)
	    File file = new File("C:\\TestNG_Framework\\Projects\\TestNGAPITesting-master\\src\\test\\resources/Shape_Excel.xlsx"); // <-- your test file

	    // Attach file
	    request.multiPart("file", file);

	    // POST request
	    Response response = request.post("/shape/excel");

	    // Log response
	    System.out.println("Status Line: " + response.statusLine());
	    System.out.println("Response Body: " + response.getBody().asString());
	    System.out.println("---------------Response Details---------------");

	    int statusCode = response.getStatusCode();

	    // Status Handling
	    switch (statusCode) {
	        case 200:
	        case 201:
	            System.out.println("Excel uploaded successfully.");
	            break;
	        case 400:
	            System.out.println("Bad Request: Check Excel format.");
	            break;
	        case 500:
	            System.out.println("Server Error: File parsing failed.");
	            break;
	    }

	    // Final assertion
	    Assert.assertTrue(statusCode == 200 || statusCode == 201,
	            "Expected 200 OK or 201 Created, but got: " + statusCode);
	}
	
	@Test
    public void testGetShapes() {
        // Set base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1";

        // Create GET request
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + Constant.authToken)  // Add authorization token if required
                .get("/shapes");

        // Print response for debugging
        System.out.println("Response Status: " + response.statusLine());
        System.out.println("Response Body: " + response.getBody().asString());

        // Check if response code is 200 (OK)
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got: " + statusCode);
    }



}
