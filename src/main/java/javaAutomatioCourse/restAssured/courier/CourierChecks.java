package javaAutomatioCourse.restAssured.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * для проверок
 */
public class CourierChecks {
    @Step("Проверка, что курьер создан")
    public void createdSuccessful(ValidatableResponse createResponse) {
        boolean created = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED) //201
                .extract()
                .path("ok");
        assertTrue(created);
    }

    @Step("Проверка, что курьер залогинен")
    public int loggedSuccessful(ValidatableResponse loginResponse) {
        int id = loginResponse
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        return id;
    }

    @Step("Проверка, что курьер удален")
    public void deletedSuccessful(ValidatableResponse response) {
        boolean delete = response
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("ok");
        assertTrue(delete);
    }
}
