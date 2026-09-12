package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ListAllBooksTestNegus {

    @Test(dataProvider = "listOfBooksTestData")
    public void listAllBooksTest(int expectedId, String expectedName,
                                 String expectedType, boolean expectedAvailable, int index) {
        RestAssured.baseURI = "https://simple-books-api.click";
        Response response = RestAssured.given().when().get("/books");
        int actualStatusCode = response.getStatusCode();
        int expectedStatusCode = 200;
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "Status code mismatch");

        int actualId = response.jsonPath().getInt("id[" + index + "]");
        Assert.assertEquals(actualId, expectedId, "id mismatch");

        String actualName = response.jsonPath().getString("name[" + index + "]");
        Assert.assertEquals(actualName, expectedName, "Name mismatch");

        String actualType = response.jsonPath().getString("type[" + index + "]");
        Assert.assertEquals(actualType, expectedType, "Type mismatch");

        boolean isAvailable = response.jsonPath().getBoolean("available[" + index + "]");
        Assert.assertEquals(isAvailable, expectedAvailable, "Available mismatch");
    }

    @DataProvider(name = "listOfBooksTestData")
    public Object[][] listOfBooksTestData(){
        return new Object[][]{
                {1, "The Russian", "fiction", true, 0},
                {3, "The Vanishing Half", "fiction", true, 1},
                {4, "The Midnight Library", "fiction", true, 2},
                {6, "Viscount Who Loved Me", "fiction", true, 3},
                {2, "Just as I Am", "non-fiction", false, 4},
                {5, "Untamed", "non-fiction", true, 5},
        };
    }
}
