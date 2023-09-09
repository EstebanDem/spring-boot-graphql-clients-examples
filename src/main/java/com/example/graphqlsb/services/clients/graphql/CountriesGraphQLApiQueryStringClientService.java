package com.example.graphqlsb.services.clients.graphql;

import com.example.graphqlsb.models.clients.graphql.countriesql.CountriesGraphQLResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CountriesGraphQLApiQueryStringClientService {

    private static final String BASE_URL = "https://countries.trevorblades.com/graphql";
    private static final String QUERY =
            "{\"query\":\"query Query {" +
                    "  country(code: \\\"%s\\\") {" +
                    "    name" +
                    "    native" +
                    "    capital" +
                    "    emoji" +
                    "    currency" +
                    "    languages {" +
                    "      code" +
                    "      name" +
                    "    }" +
                    "  }" +
                    "}\"}";

    private final WebClient webClient;


    public CountriesGraphQLApiQueryStringClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(BASE_URL)
                .build();
    }

    public CountriesGraphQLResponse getCountryByCode(String countryCode) {
        return webClient.post()
                .body(BodyInserters.fromValue(String.format(QUERY, countryCode)))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(CountriesGraphQLResponse.class)
                .block();
    }


}
