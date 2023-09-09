package com.example.graphqlsb.services.clients.graphql;

import com.example.graphqlsb.models.clients.graphql.countriesql.CountriesGraphQLResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.k0kubun.builder.query.graphql.GraphQLQueryBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class CountriesGraphQLApiQueryBuilderLibClientService {

    private static final String BASE_URL = "https://countries.trevorblades.com/graphql";
    private final ObjectMapper objectMapper;
    private final WebClient webClient;

    public CountriesGraphQLApiQueryBuilderLibClientService(ObjectMapper objectMapper, WebClient.Builder webClientBuilder) {
        this.objectMapper = objectMapper;
        this.webClient = webClientBuilder
                .baseUrl(BASE_URL)
                .build();
    }

    public CountriesGraphQLResponse getCountryByCode(String countryCode) {
        return webClient.post()
                .body(BodyInserters.fromValue(buildQuery(countryCode)))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(CountriesGraphQLResponse.class)
                .block();
    }

    private String buildQuery(String countryCode) {
        String query = GraphQLQueryBuilder.query()
                .object("country", Map.of("code", countryCode), GraphQLQueryBuilder.object()
                        .field("name")
                        .field("native")
                        .field("capital")
                        .field("emoji")
                        .field("currency")
                        .object("languages", GraphQLQueryBuilder.object()
                                .field("code")
                                .field("name")
                                .build()
                        ).build()
                ).build();

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("query", "query Query { " + query + " }");
        try {
            return objectMapper.writeValueAsString(queryMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //return "{\"query\":\"query Query {\\n  " + query.replace("\"", "\\\"").replace("\n", "\\n  ") + "\\n}\"}";
    }
}
