package javaAutomatioCourse.restAssured.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import javaAutomatioCourse.restAssured.client.Client;
import javaAutomatioCourse.restAssured.courier.pojo.Courier;
import javaAutomatioCourse.restAssured.courier.pojo.CourierCredentials;

import java.util.Map;

/**
 * для операций с backend
 */
public class CourierClient extends Client {
    public static final String PATH = "/courier";

    @Step ("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return spec()
                .body(courier)
                .when().post(PATH)
                .then().log().all();
    }

    @Step ("Логин курьера")
    public ValidatableResponse loginCourier(CourierCredentials cred) {
        return spec()
                .body(cred)
                .when().post(PATH + "/login")
                .then().log().all();
    }

    @Step ("Удаление курьера")
    public ValidatableResponse deleteCourier(int id) {
        return spec()
                .body(Map.of("id", id))
                .when().delete(PATH + "/" + id)
                .then().log().all();
    }
}
