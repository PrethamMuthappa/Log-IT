package org.example.greptile.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.greptile.Controller.OpenAIController;
import org.example.greptile.Model.CommitResponse;
import org.example.greptile.Model.OpenAIModels.ChatRequest;
import org.example.greptile.Model.OpenAIModels.Chatresponse;
import org.example.greptile.Model.OpenAIModels.Content;
import org.example.greptile.Model.OpenAIModels.Message;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class GithubAPIService {

    private final RestTemplate restTemplate;
    private final String baseurl="https://api.github.com";
 //   private String owner="PrethamMuthappa";
   // private String repo="user-activity";
    private final OpenAIController openAIController;

    public GithubAPIService(RestTemplate restTemplate, OpenAIController openAIController) {
        this.restTemplate = restTemplate;
        this.openAIController = openAIController;
    }

    public List<CommitResponse> FetchCommits(String owner, String repo, int n) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/vnd.github.v3+json");
        String commitsUrl = String.format("%s/repos/%s/%s/commits?per_page=%d", baseurl, owner, repo,n);
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
                CommitResponse responsedetails=fetchcommitDetails(sha,owner,repo);
                if(responsedetails!=null){
                    commitResponses.add(responsedetails);
                }
            }
        }
     return commitResponses;
    }

    private CommitResponse fetchcommitDetails(String sha,String owner, String repo) {
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
        ChatRequest chatrequest = new ChatRequest();
        response.setSha((String) responseBody.get("sha"));

        Map<String, Object> commit = (Map<String, Object>) responseBody.get("commit");
        Map<String, Object> author = (Map<String, Object>) commit.get("author");
        response.setAuthor((String) author.get("name"));
        response.setDate((String) author.get("date"));
        response.setMessage((String) commit.get("message"));

        //


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

        //this is to set llm changes
        chatrequest.setModel("meta-llama/Llama-Guard-3-11B-Vision-Turbo");
        chatrequest.setMax_tokens(512);
        chatrequest.setStream(false);
        chatrequest.setTemperature(0.7);
        chatrequest.setRepetition_penalty(1);
        chatrequest.setTruncate(130560);
        chatrequest.setTop_p(0.7);
        chatrequest.setTop_k(50);
        chatrequest.setStop(Arrays.asList("<|eot_id|>", "<|eom_id|>"));

        List<Message> messages=new ArrayList<>();
        Message message =new Message();
        message.setRole("user");

        List<Content>contents=new ArrayList<>();
        Content content =new Content();
        content.setType("text");
        content.setText("Create a changelog for the following code patch: " + changes.get(0).getPatch());
        contents.add(content);
        message.setContent(changes.toString());
        messages.add(message);
        chatrequest.setMessages(messages);

        response.setChanges(changes);

        Chatresponse ai=openAIController.generateChat(chatrequest);
        response.setAisummary(ai.getChoices().getFirst().getMessage().getContent());
//        openAIController.generateChat(chatrequest);
        log.info(openAIController.generateChat(chatrequest).toString());
        return response;

    }
}
