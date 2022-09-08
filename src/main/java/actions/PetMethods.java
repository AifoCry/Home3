package actions;

import static org.hamcrest.Matchers.equalTo;

import dto.PetObject;
import io.restassured.module.jsv.JsonSchemaValidator;
import services.PetApi;

public class PetMethods {

  private final PetObject pet;
  private final PetApi petApi;

  public PetMethods(PetObject pet) {
    this.pet = pet;
    this.petApi = new PetApi();
  }

  public PetMethods createPet() {
    petApi.createPet(pet)
        .body("status", equalTo(pet.getStatus()))
        .body("name", equalTo(pet.getName()))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/pet.json"));
    return this;
  }

  public PetMethods getPetById() {
    petApi.getPetById(pet)
        .statusCode(200);
    return this;
  }

  public PetMethods deletePet() {
    petApi.deletePet(pet).statusCode(200);
    petApi.getPetById(pet).statusCode(404);
    return this;
  }

  public PetMethods updatePetById() {
    petApi.updatePetById(pet, "name=" + "newName");
    petApi.getPetById(pet).body("name", equalTo("newName"));
    return this;
  }
}
