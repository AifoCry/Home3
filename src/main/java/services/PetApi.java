package services;

import static io.restassured.RestAssured.given;

import dto.PetObject;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class PetApi extends PetStoreApiSpec {

  public ValidatableResponse createPet(PetObject pet) {
    return given(requestSpecification)
        .with()
        .body(pet)
        .contentType(ContentType.JSON)
        .log().all()
        .post()
        .then()
        .log().all();
  }


  public ValidatableResponse getPetById(PetObject pet) {
    return given(requestSpecification)
        .with()
        .pathParam("petId", pet.getId())
        .contentType(ContentType.JSON)
        .log().all()
        .get("/{petId}")
        .then()
        .log().all();
  }

  public ValidatableResponse updatePetById(PetObject pet, String body) {
    return given(requestSpecification)
        .with()
        .pathParam("petId", pet.getId())
        .body(body)
        .contentType(ContentType.URLENC)
        .log().all()
        .post("/{petId}")
        .then()
        .log().all();
  }

  public ValidatableResponse deletePet(PetObject pet) {
    return given(requestSpecification)
        .with()
        .contentType(ContentType.URLENC)
        .pathParam("petId", pet.getId())
        .when()
        .log().all()
        .delete("/{petId}")
        .then()
        .log().all();
  }
}
