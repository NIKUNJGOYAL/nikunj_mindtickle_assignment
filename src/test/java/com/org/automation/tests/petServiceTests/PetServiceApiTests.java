package com.org.automation.tests.petServiceTests;

import com.org.automation.data.petServiceData.PetServiceDP;
import com.org.automation.helpers.petServiceHelpers.PetsApiHelper;
import com.org.automation.models.petServicePojos.request.Pet;
import com.org.automation.utility.ExtendedSoftAssert;
import com.org.automation.utility.LogController;
import com.org.automation.validations.petServiceValidations.PetApiValidations;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class PetServiceApiTests {

    LogController logger = new LogController(PetServiceApiTests.class);
    PetsApiHelper petsApiHelper;
    PetApiValidations petApiValidations;
    ExtendedSoftAssert softAssert;

    @BeforeClass(alwaysRun = true)
    public void beforeClass()
    {
        petsApiHelper = new PetsApiHelper();
        petApiValidations = new PetApiValidations();
        softAssert = new ExtendedSoftAssert();
    }

    @Test(groups = {"pet"}, priority = 1,description = "Create pet", dataProvider = "createPetDP", dataProviderClass = PetServiceDP.class)
    public void testCreatePetsApi(List<Object> petDetails,List<Object> category,List<String> images,List<List<Object>> tags)
    {
        logger.info("calling create pet api");
        Response response = petsApiHelper.createPet(petDetails,category,images,tags);
        Assert.assertEquals(response.statusCode(),200,"validate create pet api status code");
////        File schema = new File("src/test/resources/createUserResponseSchema.json");
//        response.then().body(JsonSchemaValidator.matchesJsonSchema(schema));
        Pet createPetResponse = petsApiHelper.createUpdatePetApiResponse(response);
        petApiValidations.validateCreatePetApi(petDetails,createPetResponse,softAssert);
        softAssert.assertAll();
    }

    @Test(groups = {"pet"}, priority = 2,description = "Update pet", dataProvider = "updatePetDP", dataProviderClass = PetServiceDP.class)
    public void testUpdatePetsApi(List<Object> petDetails,List<Object> category,List<String> images,List<List<Object>> tags)
    {
        logger.info("calling update pet api");
        Response response = petsApiHelper.updatePet(petDetails,category,images,tags);
        Assert.assertEquals(response.statusCode(),200,"validate create pet api status code");
        Pet updatePetResponse = petsApiHelper.createUpdatePetApiResponse(response);
        petApiValidations.validateUpdatePetApi(petDetails,updatePetResponse,softAssert);
        softAssert.assertAll();
    }

    @Test(groups = {"pet"}, priority = 3,description = "Get pets by status", dataProvider = "getPetsDP", dataProviderClass = PetServiceDP.class)
    public void testGetPetsbyStatus(String status)
    {
        logger.info("calling get pets by status api");
        Response response = petsApiHelper.getPetsByStatus(status);
        Assert.assertEquals(response.statusCode(),200,"validate get pets api status code");
        List<Pet> pets = petsApiHelper.fetchPetsByStatus(response);
        petApiValidations.validateGetPetsByStatusApi(status,pets,softAssert);
        softAssert.assertAll();
    }
}
