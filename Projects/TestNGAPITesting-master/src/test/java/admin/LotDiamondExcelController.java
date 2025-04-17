package admin;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

import java.io.FileOutputStream;
import java.io.IOException;

public class LotDiamondExcelController extends BaseTest {

	@Test(enabled=false)
	public void loginToSystem() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);


		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/generateJewelleryExcel/{userId}/{auctionId}");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		if (statusCode == 200) {
			System.out.println("Request succeeded: Access granted.");

			// Save the Excel file
			try {
				byte[] fileContent = response.getBody().asByteArray(); // Get the file as byte array
				String filePath = "C:\\Users\\ESAMYAK121\\Desktop\\Testing\\JewelleryExcel.xlsx"; // Set your file path
				FileOutputStream fileOutputStream = new FileOutputStream(filePath);
				fileOutputStream.write(fileContent);
				fileOutputStream.close();
				System.out.println("Excel file downloaded successfully at: " + filePath);
			} catch (IOException e) {
				System.out.println("Error saving the file: " + e.getMessage());
			}
		} else if (statusCode == 403) {
			System.out.println("Access denied: Invalid credentials or permissions.");
		}

		// Assert that the status code is 200 (OK)
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}
}
