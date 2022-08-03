package services;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.lang3.StringUtils;

import static io.restassured.RestAssured.given;

abstract class PetStoreApiSpec {
    private String getBaseUrl(){
        return System.getProperty("base.url");
    }
    protected RequestSpecification requestSpecification;


    public PetStoreApiSpec() {
        requestSpecification = given()
                .baseUri(getBaseUrl())
                .basePath("/pet");
    }

}
