package com.org.automation.helpers.userServiceHelpers;

import com.org.automation.models.userServicePojos.request.User;
import com.org.automation.models.userServicePojos.response.CreateUpdateApiResponse;
import com.org.automation.service.UserService;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserApiHelper {

    UserService userService = new UserService();

    public Response createUsers(List<List<Object>> usersDataList)
    {
        List<User> createUsersReqBody = new ArrayList<>();
        for(int i=0;i<usersDataList.get(0).size();i++)
        {
            List<Object> dataList = new ArrayList<>();
            for(int j=0;j<usersDataList.size();j++)
            {
                dataList.add(usersDataList.get(j).get(i));
            }
            User user = setUserDetails(dataList);
            createUsersReqBody.add(user);
        }
        Response response = userService.createUsers(createUsersReqBody);
        return response;
    }

    public Response updateUser(Object usernameExisting,List<Object> userData)
    {
        User user = setUserDetails(userData);
        Response response = userService.updateUser(String.valueOf(usernameExisting),user);
        return response;
    }

    public User setUserDetails(List<Object> userData)
    {
        User user = new User();
        System.out.println(userData);
        user.setId((Integer) userData.get(0));
        user.setUsername(String.valueOf(userData.get(1)));
        user.setFirstname(String.valueOf(userData.get(2)));
        user.setLastname(String.valueOf(userData.get(3)));
        user.setEmail(String.valueOf(userData.get(4)));
        user.setPassword(String.valueOf(userData.get(5)));
        user.setPhone(String.valueOf(userData.get(6)));
        user.setUserStatus((Integer) userData.get(7));
        return user;
    }

    public Response getUserDetails(String username)
    {
        Response response = userService.getUserDetails(username);
        return response;
    }

    public User fetchUserDetails(Response response)
    {
        User user = response.getBody().as(User.class);
        return user;
    }

    public CreateUpdateApiResponse fetchCreateUpdateApiResponse(Response response)
    {
        CreateUpdateApiResponse createUpdateApiResponse = response.getBody().as(CreateUpdateApiResponse.class);
        return  createUpdateApiResponse;
    }
}
