package services;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

abstract class PetStoreApiSpec {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/";
    protected RequestSpecification requestSpecification;


    public PetStoreApiSpec() {
        requestSpecification = given()
                .baseUri(BASE_URI);
    }

}
