package org.example.gravlltest.Controller;

import org.example.gravlltest.Service.GithubAPIService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubAPIResponse {

    private final GithubAPIService githubAPIService;

    public GithubAPIResponse(GithubAPIService githubAPIService) {
        this.githubAPIService = githubAPIService;
    }

    @GetMapping
    public ResponseEntity<?>responseEntity(){
        return new ResponseEntity<>(githubAPIService.FetchCommits(), HttpStatus.OK);
    }
}
