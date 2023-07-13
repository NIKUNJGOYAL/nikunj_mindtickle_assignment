package com.org.automation.data.petServiceData;

import org.testng.annotations.DataProvider;

import java.util.List;


public class PetServiceDP {

    @DataProvider(name = "createPetDP")
    public Object[][] createPetDP ()
    {
        return new Object[][] {
                {List.of(Long.valueOf(989776),"name","available"),List.of(0,"category"),List.of("img1","img2"),List.of(List.of(1,"n1"),List.of(1,"n1"))}
        };
    }

    @DataProvider(name = "updatePetDP")
    public Object[][] updatePetDP ()
    {
        return new Object[][] {
                {List.of(Long.valueOf(989776),"name","pending"),List.of(0,"category"),List.of("img1","img2"),List.of(List.of(1,"n1"),List.of(1,"n1"))}
        };
    }

    @DataProvider(name = "getPetsDP")
    public Object[][] getPetsDP ()
    {
        return new Object[][] {
                {"available"},
                {"pending"},
                {"sold"}
        };
    }
}
