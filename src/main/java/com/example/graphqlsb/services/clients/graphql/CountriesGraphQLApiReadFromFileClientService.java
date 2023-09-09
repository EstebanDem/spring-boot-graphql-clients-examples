package com.example.graphqlsb.services.clients.graphql;

import com.example.graphqlsb.models.clients.graphql.countriesql.CountriesGraphQLResponse;
import com.example.graphqlsb.services.clients.graphql.QueryReaderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Map;

@Service
public class CountriesGraphQLApiReadFromFileClientService {

    private static final String BASE_URL = "https://countries.trevorblades.com/graphql";
    private final QueryReaderService queryReaderService;
    private final WebClient webClient;

    public CountriesGraphQLApiReadFromFileClientService(QueryReaderService queryReaderService, WebClient.Builder webClientBuilder) {
        this.queryReaderService = queryReaderService;
        this.webClient = webClientBuilder
                .baseUrl(BASE_URL)
                .build();
    }

    public CountriesGraphQLResponse getCountryByCode(String countryCode) {
        try {
            String query = queryReaderService.loadQueryFromFile("graphqlqueries/countries/CountryByCodeQuery.graphql");
            Map<String, Object> variables = Map.of("countryCode", countryCode);
            Map<String, Object> requestBody = Map.of(
                    "query", query,
                    "variables", variables
            );
            return webClient.post()
                    .body(BodyInserters.fromValue(requestBody))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .retrieve()
                    .bodyToMono(CountriesGraphQLResponse.class)
                    .block();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
