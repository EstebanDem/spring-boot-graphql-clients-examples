package com.example.graphqlsb.models.clients.graphql.countriesql;

import com.example.graphqlsb.models.clients.graphql.GraphQLBaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class CountriesGraphQLResponse extends GraphQLBaseResponse<CountriesGraphQLData> {

    @JsonIgnore
    public Country getCountry() {
        return this.getData().getCountry();
    }
}
