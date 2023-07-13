package com.org.automation.data.userServiceData;

import org.testng.annotations.DataProvider;

import java.util.List;

public class UserServiceDP {

    @DataProvider(name = "createUsersDP")
    public Object[][] createUsersDP ()
    {
        return new Object[][] {
                {List.of(List.of(1,2),List.of("Ram Gupta","Shyam Gupta"),List.of("Ram","Shyam"),List.of("Gupta","Gupta")
                        ,List.of("ram@gmail.com","shyam@gmail.com"),List.of("qwerty","ytrwq")
                        ,List.of("9998887771","9991112221"),List.of(1,1))}
        };
    }

    @DataProvider(name = "updateUserDP")
    public Object[][] updateUserDP ()
    {
        return new Object[][] {
                {"Shyam Gupta",List.of(2,"Suresh Gupta","Suresh","Gupta","suresh@gmail.com","suriop","9990009991",1)}
        };
    }

    @DataProvider(name = "getUserDP")
    public Object[][] getUserDP ()
    {
        return new Object[][] {
                {"Suresh Gupta"}
        };
    }
}
