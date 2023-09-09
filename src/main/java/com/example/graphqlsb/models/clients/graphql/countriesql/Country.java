package com.example.graphqlsb.models.clients.graphql.countriesql;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private String name;
    @JsonProperty("native")
    private String nativeResponse;
    private String capital;
    private String emoji;
    private String currency;
    private List<Languages> languages;
}

