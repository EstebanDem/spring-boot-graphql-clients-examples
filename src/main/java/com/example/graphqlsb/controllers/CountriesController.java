package com.example.graphqlsb.controllers;

import com.example.graphqlsb.models.clients.graphql.countriesql.CountriesGraphQLResponse;
import com.example.graphqlsb.services.clients.graphql.CountriesGraphQLApiQueryBuilderLibClientService;
import com.example.graphqlsb.services.clients.graphql.CountriesGraphQLApiQueryStringClientService;
import com.example.graphqlsb.services.clients.graphql.CountriesGraphQLApiReadFromFileClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CountriesController {

    private final CountriesGraphQLApiQueryStringClientService countriesGraphQLApiClientService;
    private final CountriesGraphQLApiReadFromFileClientService countriesGraphQLApiReadFromFileClientService;
    private final CountriesGraphQLApiQueryBuilderLibClientService countriesGraphQLApiQueryBuilderLibClientService;

    @GetMapping("/v1/countries/{countryCode}/capital")
    public ResponseEntity<String> getCountryCapital(
            @PathVariable String countryCode
    ) {
        CountriesGraphQLResponse response = countriesGraphQLApiClientService.getCountryByCode(countryCode);
        return ResponseEntity.ok()
                .body(response.getCountry().getCapital());
    }

    @GetMapping("/v1/countries/{countryCode}/currency")
    public ResponseEntity<String> getCountryCurrency(
            @PathVariable String countryCode
    ) {
        CountriesGraphQLResponse response = countriesGraphQLApiReadFromFileClientService.getCountryByCode(countryCode);
        return ResponseEntity.ok()
                .body(response.getCountry().getCurrency());
    }

    @GetMapping("/v1/countries/{countryCode}/emoji")
    public ResponseEntity<String> getCountryEmoji(
            @PathVariable String countryCode
    ) {
        CountriesGraphQLResponse response = countriesGraphQLApiQueryBuilderLibClientService.getCountryByCode(countryCode);
        return ResponseEntity.ok()
                .body(response.getCountry().getEmoji());
    }
}
