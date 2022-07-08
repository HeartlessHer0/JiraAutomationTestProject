package configuration;

public class Endpoints {
    public static final String MOVE_ISSUES_TO_BACKLOG = "/rest/agile/1.0/backlog/issue";
    public static final String MOVE_ISSUES_TO_BACKLOG_FOR_BOARD = "/rest/agile/1.0/backlog/{boardId}/issue";

    public static final String CREATE_BOARD = "/rest/agile/1.0/board";
    public static final String GET_BOARD_BY_FILTER_ID = "/rest/agile/1.0/board/filter/{filterId}";
    public static final String GET_BOARD = "/rest/agile/1.0/board/{boardId}";
    public static final String DELETE_BOARD = "/rest/agile/1.0/board/{boardId}";
    public static final String GET_BOARD_CONFIGURATION = "/rest/agile/1.0/board/{boardId}/configuration";
    public static final String GET_BOARD_EPICS = "/rest/agile/1.0/board/{boardId}/epic";
    public static final String GET_BOARD_FEATURES = "/rest/agile/1.0/board/{boardId}/features";
    public static final String GET_BOARD_ISSUES = "/rest/agile/1.0/board/{boardId}/issue";
    public static final String GET_BOARD_PROJECTS = "/rest/agile/1.0/board/{boardId}/project";
    public static final String GET_BOARD_PROJECTS_FULL = "/rest/agile/1.0/board/{boardId}/project/full";

    public static final String GET_ISSUES_WITHOUT_EPIC = "/rest/agile/1.0/epic/none/issue";
    public static final String REMOVE_ISSUES_FROM_EPIC = "/rest/agile/1.0/epic/none/issue";
    public static final String GET_EPIC = "/rest/agile/1.0/epic/{epicIdOrKey}";
    public static final String PARTIALLY_UPDATE_EPIC = "/rest/agile/1.0/epic/{epicIdOrKey}";
    public static final String GET_ISSUES_FOR_EPIC = "/rest/agile/1.0/epic/{epicIdOrKey}/issue";
    public static final String MOVE_ISSUES_TO_EPIC = "/rest/agile/1.0/epic/{epicIdOrKey}/issue";
    public static final String RANK_EPICS = "/rest/agile/1.0/epic/{epicIdOrKey}/rank";

    public static final String RANK_ISSUES = "/rest/agile/1.0/issue/rank";
    public static final String GET_ISSUE = "/rest/agile/1.0/issue/{issueIdOrKey}";
    public static final String GET_ISSUE_ESTIMATION_FOR_BOARD = "/rest/agile/1.0/issue/{issueIdOrKey}/estimation";
    public static final String ESTIMATE_ISSUE_FOR_BOARD = "/rest/agile/1.0/issue/{issueIdOrKey}/estimation";

    public static final String GET_ALL_BOARDS = "/rest/agile/1.0/board";
}
