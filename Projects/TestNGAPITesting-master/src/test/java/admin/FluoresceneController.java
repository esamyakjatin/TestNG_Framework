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

public class FluoresceneController extends BaseTest {

	@Test
	public void test01PutFluoresceneID() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		 String encryptedCredentials = Constant.adminCredentials; 

		 request.body(encryptedCredentials);


		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the PUT request

		String jsonBody = "{\n" + "  \"desc\": \"string\",\n" + "  \"code\": \"string\",\n" + "  \"srNo\": 0,\n"
				+ "  \"labId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"name\": \"string\",\n"
				+ "  \"count\": 0,\n" + "  \"createdDate\": \"2024-12-13T09:21:05.197Z\"\n" + "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// String auctionId = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String fluoresceneId = "cc45493a-b131-11ef-a57f-c8d3ffbc6ac6";

		// request.pathParam("userId", userId);
		// request.pathParam("auctionId", auctionId);
		request.pathParam("fluoresceneId", fluoresceneId);

		// Send the PUT request
		Response response = request.put("/fluorescene/{fluoresceneId}");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		if (statusCode == 200) {
			System.out.println("Request succeeded: User updated successfully.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 404) {
			System.out.println("Not Found: The resource could not be found.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK)
		Assert.assertTrue(statusCode == 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void test02PostFluorescene() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();
		 String encryptedCredentials = Constant.adminCredentials; 

		 request.body(encryptedCredentials);


		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		String uniqueName = "india" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		
		// Create the JSON body for the POST request
		String jsonBody = "{\n" +

				"  \"desc\": \"-\",\n" + "  \"code\": \"5\",\n" + "  \"srNo\": 0,\n"
				+ "  \"fluoId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"name\": \""+uniqueName+"\",\n"
				+ "  \"count\": 0,\n" + "  \"createDate\": \"string\"\n" + // Removed the trailing comma here
				"}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// Send the POST request
		Response response = request.post("/fluorescene");

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
	public void tset03PostFluoresceneExcel() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		 String encryptedCredentials = Constant.adminCredentials; 

		 request.body(encryptedCredentials);


		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to multipart/form-data for file upload
		request.header("Content-Type", "multipart/form-data");

		// Specify the file to upload (replace with your actual file path)
		File file = new File("C:\\WorkSpaces\\Swastik2425_Workspace\\Postsample\\Testing/flourescene_Masters_Excel.xlsx"); // Update with
																											// actual
																											// file path

		// Add the file as part of the multipart request
		request.multiPart("file", file);

		// Send the POST request
		Response response = request.post("/fluorescene/excel");

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
	public void test04GetFluorescenes() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		 String encryptedCredentials = Constant.adminCredentials; 

		 request.body(encryptedCredentials);


		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// Send the GET request with query parameters
		Response response = request.get("/fluorescenes");

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
    public void testDeleteFluorescene() {
        // Base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1";

        // Path variable
        String fluoresceneId = "123e4567-e89b-12d3-a456-426614174000"; // Replace with a valid fluoresceneId to delete

        // Send DELETE request
        Response response = RestAssured
            .given()
            .header("Authorization", "Bearer " + Constant.authToken) // Replace with actual auth token
            .delete("/fluorescene/" + fluoresceneId);

        // Debug output
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:\n" + response.getBody().asPrettyString());

        // Assertions
        Assert.assertEquals(200, response.statusCode()); // Check for 200 OK status
        Assert.assertTrue(response.getBody().asString().contains("Fluorescene deleted successfully"));

        // Additional assertion: Check if the fluorescene no longer exists (GET request for example)
        Response checkResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + Constant.authToken)
            .get("/fluorescene/" + fluoresceneId);
        
        Assert.assertEquals(404, checkResponse.statusCode());
    }
}
