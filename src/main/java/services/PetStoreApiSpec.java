package services;

import static io.restassured.RestAssured.given;

import io.restassured.specification.RequestSpecification;

abstract class PetStoreApiSpec {
  private String getBaseUrl() {
    return System.getProperty("base.url");
  }

  protected RequestSpecification requestSpecification;


  public PetStoreApiSpec() {
    requestSpecification = given()
        .baseUri(getBaseUrl())
        .basePath("/pet");
  }

}
