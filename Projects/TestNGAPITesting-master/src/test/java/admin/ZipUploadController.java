package admin;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ZipUploadController extends BaseTest {

    @Test
    public void test02LotImageLinksAuction() {
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1";
        RequestSpecification request = RestAssured.given();

        String encryptedCredentials = Constant.adminCredentials;
        request.body(encryptedCredentials);

        String token = Constant.authToken;
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");

        // Define parameters
        String lotNo = "12345";
        String imageLinks = "http://example.com/image1.jpg,http://example.com/image2.jpg";
        String auctionId = "506d70a1-1ee7-41cf-baf5-6dc31b481e1e";

        // Add path parameters dynamically
        request.pathParam("lotNo", lotNo);
        request.pathParam("imageLinks", imageLinks);
        request.pathParam("auctionId", auctionId);

        // Send the PUT request
        Response response = request.put("/lot/{lotNo}/{imageLinks}/{auctionId}");

        // Print response status and body for debugging
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response: " + response.getBody().asString());

        // Get and validate status code
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);

        if (statusCode == 200) {
            System.out.println("Request succeeded: Data updated successfully.");
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
    public void testUploadMedia() {
        // Base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1";

        // Replace with actual auctionId and path to your media file
        String auctionId = "04f73c96-410a-4e98-888f-15fcc6131b1d";
        File mediaFile = new File("C:\\TestNG_Framework\\Projects\\TestNGAPITesting-master\\src\\test\\resources/TestImage.jpg.jpeg");

        // Send POST request to upload media
        Response response = RestAssured.given()
                .multiPart("file", mediaFile)
                .header("Authorization", "Bearer " + Constant.authToken)  // if auth is needed
                .post("/upload/media/" + auctionId);

        // Debug print
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Assert success
        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK for media upload");
    }
}
