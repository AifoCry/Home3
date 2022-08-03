package actions;

import dto.PetObject;
import io.restassured.module.jsv.JsonSchemaValidator;
import services.PetApi;
import testData.PetData;

import static org.hamcrest.Matchers.equalTo;

public class PetMethods {
  
  private final PetApi petApi;
  PetObject pet = PetData.newPet();
  PetObject modifyPet = PetData.modifyPet();

  public PetMethods() {
    this.petApi = new PetApi();
  }

  public PetMethods createPet() {
    petApi.createPet(pet)
            .body("status", equalTo(pet.getStatus()))
            .body("name",equalTo(pet.getName()))
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
    petApi.updatePetById(modifyPet, "name=" + "newName");
    petApi.getPetById(modifyPet).body("name",equalTo("newName"));
    return this;
  }
}
