package org.example.gravlltest.Model;


import lombok.*;

import java.util.List;

@Data
class FileChange{
private String filename;
private String patch;
}

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

}
