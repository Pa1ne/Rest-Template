package com.example.resttemplate;


import com.example.resttemplate.entities.User;

import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Component;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;

import java.util.HashMap;
import java.util.Objects;
import java.util.Map;


@Component
public class Communication {
    private final HttpHeaders headers;
    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public Communication(HttpHeaders headers, RestTemplate restTemplate) {
        this.headers = headers;
        this.restTemplate = restTemplate;
        this.headers.set("Cookie",
                String.join(";", Objects.requireNonNull(restTemplate.headForHeaders(URL).get("Set-Cookie"))));
    }

    public ResponseEntity<String> insertUser() {
        User user = new User(3L, "James", "Brown", (byte) 31);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.postForEntity(URL, entity, String.class);
    }

    public ResponseEntity<String> updateUser() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 42);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class, 3);
    }

    public ResponseEntity<String> deleteUser() {
        Map<String, Long> uriVariables = new HashMap<>() {{
            put("id", 3L);
        }};
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(URL + "/{id}", HttpMethod.DELETE, entity, String.class, uriVariables);
    }
}
