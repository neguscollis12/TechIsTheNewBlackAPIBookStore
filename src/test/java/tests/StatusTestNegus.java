package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusTestNegus {

    @Test
    public void statusTest(){
        RestAssured.baseURI = "https://simple-books-api.click";
        Response response = RestAssured.given().when().get("/status");

        int actualStatusCode = response.getStatusCode();
        int exceptedStatusCode = 200;
        Assert.assertEquals(actualStatusCode, exceptedStatusCode, "Status code mismatch");

        String actualMessage = response.jsonPath().getString("status");
        String exceptedMessage = "OK";
        Assert.assertEquals(actualMessage, exceptedMessage, "Message mismatch");


    }

}
