package org.example.greptile.Model;


import lombok.*;

import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CommitResponse {

    private String sha;
    private String author;
    private String date;
    private String message;
    private List<FileChange> changes;
    private String aisummary;

    @Data
   public static class FileChange{
        private String filename;
        private String patch;
    }
}
