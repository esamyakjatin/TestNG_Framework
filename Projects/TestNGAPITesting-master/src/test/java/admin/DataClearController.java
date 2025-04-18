package admin;
import org.testng.annotations.Test; // ✅ Correct TestNG import
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class DataClearController extends BaseTest {

	@Test
    public void testClearDataByAuctionId() {
        // Base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1";

        // Path variable
        String auctionId = "d7a28125-9ba3-4ee5-a737-b634b9c7cbc5"; // Replace with actual auctionId

        // Send PUT request to clear data
        Response response = RestAssured
            .given()
            .header("Authorization", "Bearer " + Constant.authToken) // Replace with actual token
            .put("/data-clear/" + auctionId);

        // Debug output
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:\n" + response.getBody().asPrettyString());

        // Assertions
        Assert.assertEquals(200, response.statusCode());

        String responseBody = response.getBody().asString().toLowerCase();
        Assert.assertTrue(responseBody.contains("data cleared") || responseBody.contains("success"));
    }
	
}
