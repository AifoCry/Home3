
import actions.PetMethods;
import org.testng.annotations.Test;



public class PetTest {

    PetMethods petMethods = new PetMethods();


    /**
     * Создание пета
     * 1. Создаем обьект пет
     * 4. Отправляем POST-запрос
     * Проверки:
     * Приходит ответ со статусом 200 и c полями status и name равное полям в созданном обьекте
     * Удаляем пета
     */

    @Test
    public void createPet() {
        petMethods.createPet();
        petMethods.getPetById();
        petMethods.deletePet();
    }


    /**
     * Редактирование имени пета и проверка имени.
     * Шаги:
     * 2. Редактируем имя запросом Post c Id пета
     * 3. Получаем пета Get запросом по Id
     * Проверки:
     * Ответы 200 и contentType проверяются в responseSpecification
     * При редактирование нам возвращается Id в поле message
     * При получении проверяем новое имя.
     * При получении проверяем что статус и имя категории такое же как у общего обьекта в тесте.
     * Удаляем пета
     */

    @Test
    public void updatePet() {
        petMethods.createPet();
        petMethods.updatePetById();
        petMethods.deletePet();
        }
}

