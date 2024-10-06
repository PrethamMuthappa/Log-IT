package org.example.gravlltest.Service;

import org.example.gravlltest.Model.CommitResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class GithubAPIService {

    private final RestTemplate restTemplate;
    private final String baseurl="https://api.github.com";
    private String owner="PrethamMuthappa";
    private String repo="SandSim";

    public GithubAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CommitResponse> FetchCommits() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/vnd.github.v3+json");
        String commitsUrl = String.format("%s/repos/%s/%s/commits?per_page=5", baseurl, owner, repo);
        ResponseEntity<List<Map<String,Object>>> responseEntity = restTemplate.exchange(
                commitsUrl,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<Map<String, Object>>>() {
                }
        );
        List<CommitResponse>commitResponses=new ArrayList<>();

        if(responseEntity.getBody()!=null){
            for(Map<String,Object> commit: responseEntity.getBody()){
                String sha= (String) commit.get("sha");
                CommitResponse responsedetails=fetchcommitDetails(sha);
                if(responsedetails!=null){
                    commitResponses.add(responsedetails);
                }
            }
        }
     return commitResponses;
    }

    private CommitResponse fetchcommitDetails(String sha) {
    }
}
