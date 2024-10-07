package org.example.greptile.Controller;

import org.example.greptile.Model.CommitResponse;
import org.example.greptile.Service.GithubAPIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class GithubAPIResponse {

    private final GithubAPIService githubAPIService;

    public GithubAPIResponse(GithubAPIService githubAPIService) {
        this.githubAPIService = githubAPIService;
    }

    @GetMapping("/")
    public ModelAndView loadForm() {
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/commit")
    public ModelAndView responseEntity(@RequestParam String owner, @RequestParam String repo, @RequestParam int n){
        ModelAndView modelAndView= new ModelAndView();
        List<CommitResponse> commitResponseList=githubAPIService.FetchCommits(owner,repo,n);
        modelAndView.addObject("commits", commitResponseList);
        modelAndView.addObject("repoOwner", owner);
        modelAndView.addObject("repoName", repo);
        modelAndView.setViewName("ChangeLogs");
        return modelAndView;
    }
}
