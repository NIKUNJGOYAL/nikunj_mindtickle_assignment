package com.org.automation.tests.userServiceTests;

import com.org.automation.data.userServiceData.UserServiceDP;
import com.org.automation.helpers.userServiceHelpers.UserApiHelper;
import com.org.automation.models.userServicePojos.request.User;
import com.org.automation.models.userServicePojos.response.CreateUpdateApiResponse;
import com.org.automation.utility.BaseHelper;
import com.org.automation.utility.ExtendedSoftAssert;
import com.org.automation.utility.LogController;
import com.org.automation.validations.userServiceValidations.UserApiValidations;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class UserServiceApiTests extends BaseHelper {
    private static final Logger log = LogManager.getLogger(UserServiceApiTests.class);
    LogController logger = new LogController(UserServiceApiTests.class);
    UserApiHelper userApiHelper;
    UserApiValidations userApiValidations;
    ExtendedSoftAssert softAssert;

    @BeforeClass(alwaysRun = true)
    public void beforeClass()
    {
        userApiHelper = new UserApiHelper();
        userApiValidations = new UserApiValidations();
        softAssert = new ExtendedSoftAssert();
    }

    @Test(groups = {"user"},priority = 1,description = "Create users", dataProvider = "createUsersDP", dataProviderClass = UserServiceDP.class)
    public void testCreateUsersApi(List<List<Object>> usersData) {
        logger.info("calling create users api");
        Response response = userApiHelper.createUsers(usersData);
        Assert.assertEquals(response.statusCode(),200,"validate create users api status code");
        CreateUpdateApiResponse createApiResponse = userApiHelper.fetchCreateUpdateApiResponse(response);
        userApiValidations.validateNewUsers(usersData.get(1),createApiResponse,softAssert);
        softAssert.assertAll();
    }

    @Test(groups = {"user"}, priority = 2,description = "update user", dataProvider = "updateUserDP", dataProviderClass = UserServiceDP.class)
    public void testUpdateUserApi(Object username,List<Object> usersData) {
        logger.info("calling update user api");
        Response response = userApiHelper.updateUser(username,usersData);
        Assert.assertEquals(response.statusCode(),200,"validate update user api status code");
        CreateUpdateApiResponse updateApiResponse = userApiHelper.fetchCreateUpdateApiResponse(response);
        userApiValidations.validateUpdateUserApi(usersData,updateApiResponse,softAssert);
        softAssert.assertAll();
    }

    @Test(groups = {"user"},  priority = 3,description = "get user details", dataProvider = "getUserDP", dataProviderClass = UserServiceDP.class)
    public void testGetUserApi(Object username) {
        logger.info("calling get user api");
        Response response = userApiHelper.getUserDetails(String.valueOf(username));
        User user = userApiHelper.fetchUserDetails(response);
        Assert.assertEquals(response.statusCode(),200,"validate get user api status code");
        userApiValidations.validateGetUserApi(user,username,softAssert);
        softAssert.assertAll();
    }
}
