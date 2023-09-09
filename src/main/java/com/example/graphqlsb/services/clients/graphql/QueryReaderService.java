package com.example.graphqlsb.services.clients.graphql;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class QueryReaderService {

    public String loadQueryFromFile(String queryPath) throws IOException {
        Resource resource = new ClassPathResource(queryPath);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
