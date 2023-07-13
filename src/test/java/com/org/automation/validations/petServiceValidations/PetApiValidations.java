package com.org.automation.validations.petServiceValidations;

import com.org.automation.constants.petServiceConstants.PetApiConstants;
import com.org.automation.helpers.petServiceHelpers.PetsApiHelper;
import com.org.automation.models.petServicePojos.request.Pet;
import com.org.automation.utility.ExtendedSoftAssert;
import com.org.automation.utility.LogController;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PetApiValidations {

    LogController logger = new LogController(PetApiValidations.class);
    PetsApiHelper petsApiHelper = new PetsApiHelper();

    public void validateCreatePetApi(List<Object> expectedPetDetails,Pet createPetApiResponse ,ExtendedSoftAssert softAssert)
    {
        logger.info("validate newly created pet details");
        softAssert.assertEquals(createPetApiResponse.getId(),(Long) expectedPetDetails.get(0));
        softAssert.assertEquals(createPetApiResponse.getName(),String.valueOf(expectedPetDetails.get(1)));
        softAssert.assertEquals(createPetApiResponse.getStatus(),String.valueOf(expectedPetDetails.get(2)));
        Response response = petsApiHelper.getPetsByStatus(PetApiConstants.PET_STATUS_AVAILABLE);
        Assert.assertEquals(response.statusCode(),200,"validate get pets api status code");
        List<Pet> pets = petsApiHelper.fetchPetsByStatus(response);
        List<Long> petIds = new ArrayList<>();
        for(Pet pet : pets)
        {
            petIds.add(pet.getId());
        }
        softAssert.assertTrue(petIds.contains((Long) expectedPetDetails.get(0)),"validate newly created pet should be available");
    }

    public void validateUpdatePetApi(List<Object> expectedPetDetails,Pet updatePetApiResponse  ,ExtendedSoftAssert softAssert)
    {
        logger.info("validate updated pet details");
        softAssert.assertEquals(updatePetApiResponse.getId(),(Long) expectedPetDetails.get(0));
        softAssert.assertEquals(updatePetApiResponse.getName(),String.valueOf(expectedPetDetails.get(1)));
        softAssert.assertEquals(updatePetApiResponse.getStatus(),String.valueOf(expectedPetDetails.get(2)));
        Response response = petsApiHelper.getPetsByStatus(PetApiConstants.PET_STATUS_PENDING);
        Assert.assertEquals(response.statusCode(),200,"validate get pets api status code");
        List<Pet> pets = petsApiHelper.fetchPetsByStatus(response);
        List<Long> petIds = new ArrayList<>();
        for(Pet pet : pets)
        {
            petIds.add(pet.getId());
        }
        softAssert.assertTrue(petIds.contains((Long) expectedPetDetails.get(0)),"validate status of updated pet should be in pending");
    }

    public void validateGetPetsByStatusApi(String status,List<Pet> pets, ExtendedSoftAssert softAssert)
    {
        logger.info("validate pet details by status");
        HashSet<String> statusSet = new HashSet<>();
        for(Pet pet : pets)
        {
            statusSet.add(pet.getStatus());
        }
        softAssert.assertEquals(statusSet.size(),1,"validate all pets should have same status");
        softAssert.assertEquals(statusSet.iterator().next(),status,"validate status of all pets");
    }
}
