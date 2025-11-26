package user;

import utils.Constants;
import io.qameta.allure.Step;


import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Создание пользователя")
    public Response createUser(User user){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(Constants.CREATE_USER_API);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        return given()
                .header("authorization", "bearer "+ accessToken)
                .when()
                .delete(Constants.DELETE_USER_API);
    }
}