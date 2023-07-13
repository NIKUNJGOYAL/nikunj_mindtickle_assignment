package com.org.automation.helpers.petServiceHelpers;

import com.epam.ta.reportportal.ws.annotations.In;
import com.org.automation.models.petServicePojos.request.Category;
import com.org.automation.models.petServicePojos.request.Pet;
import com.org.automation.models.petServicePojos.request.Tag;
import com.org.automation.service.PetService;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PetsApiHelper {

    PetService petService = new PetService();

    public Response createPet(List<Object> petDetails,List<Object> categoryObj,List<String> images,List<List<Object>> tags)
    {
        Pet pet = setPetDetails(petDetails,categoryObj,images,tags);
        Response response = petService.createPet(pet);
        return response;
    }

    public Response updatePet(List<Object> petDetails,List<Object> categoryObj,List<String> images,List<List<Object>> tags)
    {
        Pet pet = setPetDetails(petDetails,categoryObj,images,tags);
        Response response = petService.updatePet(pet);
        return response;
    }

    public Response getPetsByStatus(String status)
    {
        Response response = petService.getPetsByStatus(status);
        return response;
    }

    public List<Pet> fetchPetsByStatus(Response response)
    {
        List<Pet> pets = response.getBody().jsonPath().getList(".",Pet.class);
        return pets;
    }

    public Pet setPetDetails(List<Object> petDetails,List<Object> categoryObj,List<String> images,List<List<Object>> tags)
    {
        Pet pet = new Pet();
        pet.setId((Long) petDetails.get(0));
        Category category = new Category();
        category.setId((Integer) categoryObj.get(0));
        category.setName(String.valueOf(categoryObj.get(0)));
        pet.setName(String.valueOf(petDetails.get(1)));
        pet.setPhotoUrls(images);
        List<Tag> tagsList = new ArrayList<>();
        for(int i=0;i<tags.size();i++)
        {
            Tag tag = new Tag();
            tag.setId((Integer) tags.get(i).get(0));
            tag.setName(String.valueOf(tags.get(i).get(1)));
            tagsList.add(tag);
        }
        pet.setTags(tagsList);
        pet.setStatus(String.valueOf(petDetails.get(2)));
        return pet;
    }

    public Pet createUpdatePetApiResponse(Response response) {

        Pet pet = response.getBody().as(Pet.class);
        return pet;
    }
}
