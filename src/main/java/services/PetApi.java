package services;

import dto.PetObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class PetApi extends PetStoreApiSpec {
    private static final String PET = "/pet";
    private static final String GET_PET_BY_ID = "/pet/{petId}";

    public Response createPet(PetObject pet) {
        return given(requestSpecification)
                .with()
                .body(pet)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .post(PET);
    }


    public Response getPetById(Integer petId) {
        return given(requestSpecification)
                .with()
                .pathParam("petId", petId)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(GET_PET_BY_ID);
    }

    public Response updatePetById(Integer petId, String body) {
        return given(requestSpecification)
                .with()
                .pathParam("petId", petId)
                .body(body)
                .contentType(ContentType.URLENC)
                .log().all()
                .when()
                .post(GET_PET_BY_ID);
    }
}
