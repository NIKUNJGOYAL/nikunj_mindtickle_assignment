package com.org.automation.models.petServicePojos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    Long id;
    Category category;
    String name;
    List<String> photoUrls;
    List<Tag> tags;
    String status;
}
