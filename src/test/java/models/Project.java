package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"leadAccountId", "description"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    /**
     * <h3>REQUIRED</h3>
     * Project keys must be unique and start with an uppercase letter
     * followed by one or more uppercase alphanumeric characters.
     * The maximum length is 10 characters.
     */
    private String key;

    /**
     * <h3>REQUIRED</h3>
     * The name of the project.
     */
    private String name;

    /**
     * A brief description of the project.
     */
    private String description;

    /**
     * <h3>REQUIRED</h3>
     * The account ID of the project lead. Either lead or leadAccountId
     * must be set when creating a project. Cannot be provided with lead.
     * Max length: 128
     */
    private String leadAccountId;

    /**
     * A link to information about this project, such as project documentation.
     */
    private transient String url;

    /**
     * The default assignee when creating issues for this project.
     * Valid values: PROJECT_LEAD, UNASSIGNED
     */
    //todo remove it? private transient String assigneeType;

    /**
     * <h3>REQUIRED</h3>
     * The project type, which defines the application-specific feature set.
     * If you don't specify the project template you have to specify the project type.
     * Valid values: software, service_desk, business
     */
    private String projectTypeKey;

    /**
     * The URL of the created project.
     */
    private transient String self;

    /**
     * The ID of the created project.
     */
    private transient long id;

    public static class Builder {
        private Project newProject;

        public Builder() {
            newProject = new Project();
        }

        public Project.Builder key(String key) {
            newProject.key = key;
            return this;
        }

        public Project.Builder name(String name) {
            newProject.name = name;
            return this;
        }

        public Project.Builder description(String description) {
            newProject.description = description;
            return this;
        }

        public Project.Builder leadAccountId(String leadAccountId) {
            newProject.leadAccountId = leadAccountId;
            return this;
        }

        public Project.Builder url(String url) {
            newProject.url = url;
            return this;
        }

        public Project.Builder projectTypeKey(String projectTypeKey) {
            newProject.projectTypeKey = projectTypeKey;
            return this;
        }

        public Project build() {
            return newProject;
        }
    }
}
