package org.example.gravlltest.Service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GithubAPIService {

    private final RestTemplate restTemplate;
    private final String baseurl="https://api.github.com";
    private String owner="PrethamMuthappa";
    private String repo="SandSim";

    public GithubAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object FetchCommits() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/vnd.github.v3+json");
        ResponseEntity responseEntity = restTemplate.exchange("", HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<Object>() {

        });
        return responseEntity.getBody();
    }
}
