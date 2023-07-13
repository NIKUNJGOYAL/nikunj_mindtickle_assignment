package com.org.automation.models.userServicePojos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    Integer id;
    String username;
    String firstname;
    String lastname;
    String email;
    String password;
    String phone;
    Integer userStatus;
}
