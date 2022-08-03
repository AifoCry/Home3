package testData;

import com.github.javafaker.Faker;
import dto.Category;
import dto.PetObject;
import dto.TagsItem;

import java.util.Collections;

public class PetData {

    static Faker faker = new Faker();

    public static PetObject newPet() {
        return PetObject.builder()
                .id(10)
                .photoUrls(Collections.singletonList(faker.internet().url()))
                .status("available")
                .name(faker.name().firstName())
                .category(new Category(faker.name().firstName(), faker.number().randomDigit()))
                .tags(Collections.singletonList(new TagsItem(faker.name().firstName(), faker.number().randomDigit())))
                .build();
    }

    public static PetObject modifyPet() {
        return PetObject.builder()
                .id(10)
                .photoUrls(Collections.singletonList(faker.internet().url()))
                .status("available")
                .name(faker.name().firstName())
                .category(new Category(faker.name().firstName(), faker.number().randomDigit()))
                .tags(Collections.singletonList(new TagsItem(faker.name().firstName(), faker.number().randomDigit())))
                .build();
    }
}
