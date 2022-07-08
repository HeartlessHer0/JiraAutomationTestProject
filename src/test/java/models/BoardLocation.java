package models;

import lombok.Data;

@Data
public class BoardLocation {
    private int projectId;
    private String displayName;
    private String projectName;
    private String projectKey;
    private String projectTypeKey;
    private String avatarURI;
    private String name;
}
