package testApis;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Apis {

    String basUrl = "https://reqres.in/";
    Response response;

    @Test
    public void listUsers() {
        response = given()
                .baseUri(basUrl)
                .when()
                .get("/api/users?page=2");
        Assert.assertEquals(200, response.statusCode());
        System.out.println("El respose del get es: " + response.getBody().asString());
        System.out.println("El page es: " + response.jsonPath().get("page"));

    }

    @Test
    public void create() {
        response = given()
                .baseUri(basUrl)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when()
                .post("/api/users");
        Assert.assertEquals(201, response.statusCode());
        System.out.println("El respose del post es: " + response.getBody().asString());
    }

    @Test
    public void upDate() {
        response = given()
                .baseUri(basUrl)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .put("/api/users/2");
        Assert.assertEquals(200, response.statusCode());
        System.out.println("El respose del update es: " + response.getBody().asString());

    }

    @Test
    public void delete() {
        response = given()
                .baseUri(basUrl)
                .when()
                .delete("/api/users/2");
        Assert.assertEquals(204, response.statusCode());
        System.out.println("El respose del delete es: " + response.getBody().asString());

    }
}
