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

public class PolishController extends BaseTest {

	@Test
	public void test01PolishPolishId() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		 String encryptedCredentials = Constant.adminCredentials; 

		    //request.queryParam("payload", encryptedCredentials);
		 request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the POST request
		String jsonBody = "{\n" + "  \"desc\": \"string\",\n" + "  \"code\": \"string\",\n" + "  \"srNo\": 0,\n"
				+ "  \"polishId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"name\": \"string\",\n"
				+ "  \"count\": 0,\n" + "  \"createdDate\": \"string\"\n" + "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";
		String polishId = "cc4e6bcf-b131-11ef-a57f-c8d3ffbc6ac6";

		// Add path parameters dynamically
		// request.pathParam("userId", userId);
		// request.pathParam("auctionId", auctionId);
		request.pathParam("polishId", polishId);

		// Send the POST request
		Response response = request.put("/polish/{polishId}");

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
	public void test02Polish() {

		 // Set base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1";

        // Create JSON body for Polish creation
        String jsonBody = "{\n" +
                "  \"desc\": \"Polish Description\",\n" +
                "  \"code\": \"POL1234\",\n" +
                "  \"srNo\": 14,\n" +
                "  \"name\": \"Polish Name\",\n" +
                "  \"count\": 10,\n" +
                "  \"createdDate\": \"2025-04-18\"\n" +
                "}";

        // Send POST request to create new Polish
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + Constant.authToken)  // Add authorization token if needed
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .post("/polish");

        // Print response status and body for debugging
        System.out.println("Response Status: " + response.statusLine());
        System.out.println("Response Body: " + response.getBody().asString());

        // Check if response code is 200 or 201 (Created)
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Expected 200 OK or 201 Created, but got: " + statusCode);
    }

	@Test
	public void test03PolishExcel() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		 String encryptedCredentials = Constant.adminCredentials; 

		 request.body(encryptedCredentials);


		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to multipart/form-data
		request.header("Content-Type", "multipart/form-data");

		// Create the multipart body for the POST request
		File file = new File("C:\\WorkSpaces\\Swastik2425_Workspace\\Postsample\\Testing/polish_Masters_Excel (1).xlsx");

		// Add the file to the request
		request.multiPart("file", file);

		// Send the POST request
		Response response = request.post("/polish/excel");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString()); // Added response logging
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 201 (Created)
		if (statusCode == 200) {
			System.out.println("Request succeeded: File uploaded successfully.");
		} else if (statusCode == 201) {
			System.out.println("Request succeeded: Symmetry created.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		}

		// Assert that the status code is 200 (OK) or 201 (Created)
		Assert.assertTrue(statusCode == 200 || statusCode == 201,
				"Expected 200 OK or 201 Created, but got: " + statusCode);
	}

	@Test
	public void test04Polishes() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		 String encryptedCredentials = Constant.adminCredentials; 

		 request.body(encryptedCredentials);


		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		// request.pathParam("userId", userId)
		// .pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/polishes");

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
	    public void testDeletePolish() {
	        // Set base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1";

	        // Replace with the actual polishId you want to delete
	        String polishId = "39de2833-b575-4a11-ae09-28d34af9e1af";

	        // Send DELETE request to remove Polish entry
	        Response response = RestAssured.given()
	                .header("Authorization", "Bearer " + Constant.authToken)  // Authorization if required
	                .when()
	                .delete("/polish/" + polishId);

	        // Print response details
	        System.out.println("Response Status: " + response.getStatusLine());
	        System.out.println("Response Body: " + response.getBody().asString());

	        // Assert status code
	        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK");
	    }
	
}
