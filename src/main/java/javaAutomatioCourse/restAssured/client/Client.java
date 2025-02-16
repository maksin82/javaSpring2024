package javaAutomatioCourse.restAssured.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Client {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    public static final String BASE_PATH = "/api/v1";


    public RequestSpecification spec() {
        return RestAssured.given().log().all()
                //  .header("Content-Type", "application/json") hardcode
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath(BASE_PATH);
    }
}
