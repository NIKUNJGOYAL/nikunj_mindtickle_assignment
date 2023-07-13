package com.org.automation.validations.userServiceValidations;

import com.org.automation.constants.userServiceConstants.UserApiConstants;
import com.org.automation.helpers.userServiceHelpers.UserApiHelper;
import com.org.automation.models.userServicePojos.request.User;
import com.org.automation.models.userServicePojos.response.CreateUpdateApiResponse;
import com.org.automation.utility.ExtendedSoftAssert;
import com.org.automation.utility.LogController;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;

public class UserApiValidations {

    LogController logger = new LogController(UserApiValidations.class);
    UserApiHelper userApiHelper = new UserApiHelper();

    public void validateNewUsers(List<Object> usernamesList,CreateUpdateApiResponse createApiResponse , ExtendedSoftAssert softAssert)
    {
        logger.info("validate new users being created");
        softAssert.assertEquals(createApiResponse.getCode(), UserApiConstants.STATUS_CODE_200,"validate status code in response");
        softAssert.assertEquals(createApiResponse.getType(),UserApiConstants.TYPE_UNKNOWN,"validate type in response");
        softAssert.assertEquals(createApiResponse.getMessage(),UserApiConstants.MESSAGE_OK,"validate message in response");
        for(int i=0;i<usernamesList.size();i++)
        {
            String userName = String.valueOf(usernamesList.get(i));
            Response response = userApiHelper.getUserDetails(userName);
            Assert.assertEquals(response.statusCode(),200,"validate get user api status code");
            User user = userApiHelper.fetchUserDetails(response);
            softAssert.assertEquals(user.getUsername(),userName,"validate new user by username");
        }
    }

    public void validateUpdateUserApi(List<Object> userData,CreateUpdateApiResponse updateApiResponse, ExtendedSoftAssert softAssert)
    {
        logger.info("validate updated user details");
        softAssert.assertEquals(updateApiResponse.getCode(), UserApiConstants.STATUS_CODE_200,"validate status code in response");
        softAssert.assertEquals(updateApiResponse.getType(),UserApiConstants.TYPE_UNKNOWN,"validate type in response");
        softAssert.assertEquals(updateApiResponse.getMessage(),userData.get(0)+"","validate message in response");
        String userName = String.valueOf(userData.get(1));
        Response response = userApiHelper.getUserDetails(userName);
        Assert.assertEquals(response.statusCode(),200,"validate get user api status code");
        User user = userApiHelper.fetchUserDetails(response);
        softAssert.assertEquals(user.getUsername(),userName,"validate updated user by username");
    }

    public void validateGetUserApi(User user , Object username, ExtendedSoftAssert softAssert)
    {
        logger.info("validate user details");
        softAssert.assertEquals(user.getUsername(),username,"validate username of the user");
    }
}
