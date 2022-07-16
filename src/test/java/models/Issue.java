package models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Issue {
    private Fields fields;

    public Issue(Fields fields, String issueKey) {
        this.fields = fields;
        this.issueKey = issueKey;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Fields {
        @SerializedName("project")
        private ProjectKey projectKey;

        /**
         * <h3>REQUIRED</h3>
         * A brief description of the issue.
         */
        private String summary;

        @SerializedName("issuetype")
        private IssueType issueType;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectKey {
        /**
         * <h3>REQUIRED</h3>
         * The key of related project.
         */
        private String key;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IssueType {
        /**
         * <h3>REQUIRED</h3>
         * The issue type.
         * Valid values: task, bug, milestone
         */
        private String name;
    }


    /**
     * The ID of the created issue.
     */
    @SerializedName("id")
    private transient long issueID;

    /**
     * The key of the created issue.
     */
    @SerializedName("key")
    private transient String issueKey;

    /**
     * The URL of the created issue.
     */
    @SerializedName("self")
    private transient String issueSelf;


    public static class Builder {
        private Issue newIssue;

        public Builder() {
            newIssue = new Issue(new Fields(new ProjectKey(), "", new IssueType()), "");
        }

        public Issue.Builder key(String key) {
            newIssue.fields.projectKey.setKey(key);
            return this;
        }

        public Issue.Builder summary(String summary) {
            newIssue.fields.setSummary(summary);
            return this;
        }

        public Issue.Builder name(String name) {
            newIssue.fields.issueType.setName(name);
            return this;
        }

        public Issue.Builder issueKey(String issueKey) {
            newIssue.setIssueKey(issueKey);
            return this;
        }

        public Issue build() {
            return newIssue;
        }
    }
}