package configuration;

public class Endpoints {
    public static final String GET_EVENTS = "/rest/api/latest/events";
    public static final String CREATE_ISSUE = "/rest/api/latest/issue";
    public static final String GET_CREATE_ISSUE_METADATA = "/rest/api/latest/issue/createmeta";
    public static final String GET_ISSUE = "/rest/api/latest/issue/{issueIdOrKey}";
    public static final String EDIT_ISSUE = "/rest/api/latest/issue/{issueIdOrKey}";
    public static final String DELETE_ISSUE = "/rest/api/latest/issue/{issueIdOrKey}";
    public static final String ASSIGN_ISSUE = "/rest/api/latest/issue/{issueIdOrKey}/assignee";
    public static final String GET_CHANGELOGS = "/rest/api/latest/issue/{issueIdOrKey}/changelog";
    public static final String GET_CHANGELOGS_BY_IDS = "/rest/api/latest/issue/{issueIdOrKey}/changelog/list";
    public static final String GET_EDIT_ISSUE_METADATA = "/rest/api/latest/issue/{issueIdOrKey}/editmeta";

    public static final String GET_ALL_PROJECTS = "/rest/api/latest/project";
    public static final String CREATE_PROJECT = "/rest/api/latest/project";
    public static final String GET_PROJECT = "/rest/api/latest/project/{projectIdOrKey}";
    public static final String UPDATE_PROJECT = "/rest/api/latest/project/{projectIdOrKey}";
    public static final String DELETE_PROJECT = "/rest/api/latest/project/{projectIdOrKey}";
    public static final String ARCHIVE_PROJECT = "/rest/api/latest/project/{projectIdOrKey}/archive";
    public static final String RESTORE_DELETED_PROJECT = "/rest/api/latest/project/{projectIdOrKey}/restore";
    public static final String GET_ALL_STATUSES_FOR_PROJECT = "/rest/api/latest/project/{projectIdOrKey}/statuses";
}
