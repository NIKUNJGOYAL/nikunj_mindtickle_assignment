package com.org.automation.service;

import com.org.automation.constants.petServiceConstants.PetApiConstants;
import com.org.automation.helpers.petServiceHelpers.PetsApiHelper;
import com.org.automation.models.petServicePojos.request.Pet;
import com.org.automation.utils.EnvManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class PetService {

    public static final String PET_SERVICE_BASE_URL = EnvManager.fetchEnvProperties("pet.service.baseUrl");

    public PetService()
    {
        RestAssured.baseURI = PET_SERVICE_BASE_URL;
    }

    public Response createPet(Pet createPetReqBody)
    {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(createPetReqBody)
                .when().post(PetApiConstants.createPetEndPoint)
                .thenReturn();

        response.prettyPrint();
        return response;
    }

    public Response updatePet(Pet updatePetReqBody)
    {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(updatePetReqBody)
                .when().put(PetApiConstants.updatePetStatusEndPoint)
                .thenReturn();

        response.prettyPrint();
        return response;
    }

    public Response getPetsByStatus(String status)
    {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("status", status)
                .when().get(PetApiConstants.getPetByStatusEndPoint)
                .thenReturn();

        response.prettyPrint();
        return response;
    }
}
