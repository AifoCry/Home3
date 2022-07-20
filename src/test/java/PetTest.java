import dto.*;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.PetApi;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;

public class PetTest {

    ResponseSpecification responseSpecification = null;
    Integer PetId = 888;
    String newPatName = "NewLonelyCat";
    PetApi petApi = new PetApi();

    PetObject pet = PetObject.builder()
            .id(PetId)
            .photoUrls(Collections.singletonList("urls1"))
            .status("available")
            .category(new Category("Test", 1))
            .tags(Collections.singletonList(new TagsItem("Test", 1)))
            .build();

    @BeforeClass

    public void setupResponseSpecification() {
        responseSpecification = RestAssured.expect();
        responseSpecification.contentType(ContentType.JSON);
        responseSpecification.statusCode(200);
    }


    /**
     * Создание пета
     * 1. Создаем обьект пет
     * 4. Отправляем POST-запрос
     * Проверки:
     * Приходит ответ со статусом 200 и c полями status и name равное полям в созданном обьекте
     * Проверяется в responseSpecification ответ и contentType
     */

    @Test
    public void createPet() {
        pet.setName("LonelyCat");

        petApi.createPet(pet)
                .then()
                .spec(responseSpecification)
                .log().all()
                .body("status", equalTo(pet.getStatus()))
                .body("name",equalTo(pet.getName()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/pet.json"));
    }


    /**
     * Редактирование имени пета и проверка имени.
     * Шаги:
     * 1. Редактируем имя запросом Post c Id пета
     * 2. Получаем пета Get запросом по Id
     * Проверки:
     * Ответы 200 и contentType проверяются в responseSpecification
     * При редактирование нам возвращается Id в поле message
     * При получении проверяем новое имя.
     * При получении проверяем что статус и имя категории такое же как у общего обьекта в тесте.
     * Сравниваем два обьекта пет
     */

    @Test
    public void editPetAndCheckData() {
        pet.setName(newPatName);
        petApi.updatePetById(PetId, "name=" + newPatName)
                .then()
                .spec(responseSpecification)
                .log().all();

        PetObject petWithNewName = petApi.getPetById(PetId).then()
                .spec(responseSpecification)
                .log().all()
                .extract()
                .body().as(PetObject.class);
        Assertions.assertEquals(newPatName, petWithNewName.getName());
        Assertions.assertEquals(pet.getStatus(), petWithNewName.getStatus());
        Assertions.assertEquals(pet.getCategory().getName(), petWithNewName.getCategory().getName());
        Assertions.assertEquals(pet, petWithNewName);
        }
}

