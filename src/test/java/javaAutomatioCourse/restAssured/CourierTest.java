package javaAutomatioCourse.restAssured;

import io.restassured.response.ValidatableResponse;
import javaAutomatioCourse.restAssured.courier.CourierChecks;
import javaAutomatioCourse.restAssured.courier.CourierClient;
import javaAutomatioCourse.restAssured.courier.pojo.Courier;
import javaAutomatioCourse.restAssured.courier.pojo.CourierCredentials;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CourierTest {
    /**
     * если нет POJO:
     * String courier = "{\"login\":\"Marks\", \"password\":\"P@ssw0rd\", \"firstName\":\"Markovich\"}"; создаем Map
     * var courier = Map.of("login","Marks","password","P@ssw0rd", "firstName","Markovich");
     * var courier = new Courier("Marks", "P@ssw0rd", "Markovich"); из этой строки создаем хклпер для генерации курьера
     */
    private final CourierClient client = new CourierClient();//создаем класс, чтобы его методы были динамические и можно было передавать разные BASE_URL
    private final CourierChecks check = new CourierChecks();
    private int courierId;

    @AfterEach
    public void deleteCourier() {
        if (courierId != 0) {
            ValidatableResponse response = client.deleteCourier(courierId);
            check.deletedSuccessful(response);
        }
    }

    @Test
    @DisplayName("Проверка логина курьера")
    void courier() {
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessful(createResponse);

        var cred = CourierCredentials.from(courier);    //если классы похожи, надо их связать и создавать POJO использую класс
        ValidatableResponse loginResponse = client.loginCourier(cred);
        courierId = check.loggedSuccessful(loginResponse);

        assertNotEquals(0, courierId);
    }
}
/**
 * в тесте должна остаться только бизнеслогика или user story, остальное желательно разделить на создание и валидацию ответа
 * var cred = CourierCredentials.from(courier);    //если классы похожи, надо их связать
 * int id = given().log().all()
 * .header("Content-Type", "application/json")
 * .baseUri("https://qa-scooter.praktikum-services.ru/")
 * .body(cred)
 * .when().post("/api/v1/courier/login")
 * .then().log().all()
 * .assertThat()
 * .statusCode(200)
 * .extract()
 * .path("id");
 * <p>
 * assertNotEquals(0, id);
 **/
