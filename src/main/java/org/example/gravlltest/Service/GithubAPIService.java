package org.example.gravlltest.Service;

import org.example.gravlltest.Controller.OpenAIController;
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
    private final OpenAIController openAIController;

    public GithubAPIService(RestTemplate restTemplate, OpenAIController openAIController) {
        this.restTemplate = restTemplate;
        this.openAIController = openAIController;
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
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github.v3+json");

        String detailUrl = String.format("%s/repos/%s/%s/commits/%s", baseurl, owner, repo, sha);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                detailUrl,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        if (response.getBody() != null) {
            return parseCommitResponse(response.getBody());
        }
        return null;
    }

    private CommitResponse parseCommitResponse(Map<String, Object> responseBody) {
        CommitResponse response = new CommitResponse();
        response.setSha((String) responseBody.get("sha"));

        Map<String, Object> commit = (Map<String, Object>) responseBody.get("commit");
        Map<String, Object> author = (Map<String, Object>) commit.get("author");
        response.setAuthor((String) author.get("name"));
        response.setDate((String) author.get("date"));
        response.setMessage((String) commit.get("message"));

        List<CommitResponse.FileChange> changes = new ArrayList<>();
        List<Map<String, Object>> files = (List<Map<String, Object>>) responseBody.get("files");
        if (files != null) {
            for (Map<String, Object> file : files) {
                CommitResponse.FileChange change = new CommitResponse.FileChange();
                change.setFilename((String) file.get("filename"));
                change.setPatch((String) file.get("patch"));
                changes.add(change);
            }
        }
        response.setChanges(changes);
        return response;

    }
}
