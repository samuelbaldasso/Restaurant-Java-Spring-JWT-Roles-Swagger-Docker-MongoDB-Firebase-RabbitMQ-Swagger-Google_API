package com.sbaldass.combo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Document(collection = "restaurants")
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String location;
    private String description;

}
