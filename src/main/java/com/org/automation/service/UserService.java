package com.org.automation.service;

import com.org.automation.constants.userServiceConstants.UserApiConstants;
import com.org.automation.helpers.userServiceHelpers.UserApiHelper;
import com.org.automation.models.userServicePojos.request.User;
import com.org.automation.utils.EnvManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

public class UserService {


    public static final String USER_SERVICE_BASE_URL = EnvManager.fetchEnvProperties("user.service.baseUrl");

    public UserService()
    {
        RestAssured.baseURI = USER_SERVICE_BASE_URL;
    }

    public Response createUsers(List<User> createUsersReqBody)
    {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(createUsersReqBody)
                .when().post(UserApiConstants.createUsersEndPoint)
                .thenReturn();

        response.prettyPrint();
        return response;
    }

    public Response updateUser(String userName,User UpdateUsersReqBody)
    {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("userName", userName)
                .body(UpdateUsersReqBody)
                .when().put(UserApiConstants.updateUserByUserNameEndPoint)
                .thenReturn();

        response.prettyPrint();
        return response;
    }

    public Response getUserDetails(String userName)
    {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("userName", userName)
                .when().get(UserApiConstants.getUserByUserNameEndPoint)
                .thenReturn();

        response.prettyPrint();
        return response;
    }

}
