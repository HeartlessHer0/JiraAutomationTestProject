package configuration;

public class Endpoints {
    public static final String GET_EVENTS = "/rest/api/2/events";
    public static final String CREATE_ISSUE = "/rest/api/2/issue";
    public static final String GET_CREATE_ISSUE_METADATA = "/rest/api/2/issue/createmeta";
    public static final String GET_ISSUE = "/rest/api/2/issue/{issueIdOrKey}";
    public static final String EDIT_ISSUE = "/rest/api/2/issue/{issueIdOrKey}";
    public static final String DELETE_ISSUE = "/rest/api/2/issue/{issueIdOrKey}";
    public static final String ASSIGN_ISSUE = "/rest/api/2/issue/{issueIdOrKey}/assignee";
    public static final String GET_CHANGELOGS = "/rest/api/2/issue/{issueIdOrKey}/changelog";
    public static final String GET_CHANGELOGS_BY_IDS = "/rest/api/2/issue/{issueIdOrKey}/changelog/list";
    public static final String GET_EDIT_ISSUE_METADATA = "/rest/api/2/issue/{issueIdOrKey}/editmeta";

    public static final String GET_ATTACHMENT_CONTENT = "/rest/api/2/attachment/content/{id}";
    public static final String GET_JIRA_ATTACHMENT_SETTINGS = "/rest/api/2/attachment/meta";
    public static final String DELETE_ATTACHMENT = "/rest/api/2/attachment/{id}";
    public static final String GET_ALL_METADATA_FOR_AN_EXPANDED_ATTACHMENT = "/rest/api/2/attachment/{id}/expand/human";
    public static final String ADD_ATTACHMENT = "/rest/api/2/issue/{issueIdOrKey}/attachments";

    public static final String GET_ALL_PROJECTS = "/rest/api/2/project";
    public static final String CREATE_PROJECT = "/rest/api/2/project";
    public static final String GET_PROJECT = "/rest/api/2/project/{projectIdOrKey}";
    public static final String UPDATE_PROJECT = "/rest/api/2/project/{projectIdOrKey}";
    public static final String DELETE_PROJECT = "/rest/api/2/project/{projectIdOrKey}";
    public static final String ARCHIVE_PROJECT = "/rest/api/2/project/{projectIdOrKey}/archive";
    public static final String RESTORE_DELETED_PROJECT = "/rest/api/2/project/{projectIdOrKey}/restore";
    public static final String GET_ALL_STATUSES_FOR_PROJECT = "/rest/api/2/project/{projectIdOrKey}/statuses";

    public static final String GET_USER = "/rest/api/2/user";
    public static final String CREATE_USER = "/rest/api/2/user";
    public static final String DELETE_USER = "/rest/api/2/user";
    public static final String GET_ACCOUNT_IDS_FOR_USERS = "/rest/api/2/user/bulk/migration";
    public static final String GET_USER_EMAIL = "/rest/api/2/user/email";
    public static final String GET_ALL_USERS_DEFAULT = "/rest/api/2/users";
}
