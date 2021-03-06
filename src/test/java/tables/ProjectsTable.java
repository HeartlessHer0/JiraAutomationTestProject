package tables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.DataBaseService;

import java.sql.ResultSet;

public class ProjectsTable {

    DataBaseService dataBaseService;

    public ProjectsTable(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    Logger logger = LoggerFactory.getLogger(ProjectsTable.class);

    public void dropTable() {
        logger.info("Deleting the Project table");
        String dropTableProjectsSQL = "DROP TABLE ProjectsTable;";

        dataBaseService.executeSql(dropTableProjectsSQL);
    }

    public void createProjectsTable() {
        logger.info("Creating the Project table");

        String createTableSQL = "CREATE TABLE ProjectsTable (" +
                "ID SERIAL PRIMARY KEY, " +
                "ProjectName CHARACTER VARYING(30), " +
                "ProjectKey CHARACTER VARYING(30) " +
                ");";

        dataBaseService.executeSql(createTableSQL);
    }

    public void addProject(String projectName, String projectKey) {
        logger.info("Adding an entry to the table");

        String insertTableSQL = "INSERT INTO public.ProjectsTable(" +
                "ProjectName, ProjectKey)" +
                "VALUES ('" + projectName + "', '" + projectKey + "');";

        dataBaseService.executeSql(insertTableSQL);
    }

    public ResultSet getProjects() {
        String selectSQL = "SELECT * FROM public.ProjectsTable ORDER BY id ASC;";

        return dataBaseService.executeQuery(selectSQL);
    }

    public ResultSet getProjectById(int ID) {
        String selectSQL = "SELECT * FROM public.ProjectsTable WHERE ID =" + ID + ";";

        return dataBaseService.executeQuery(selectSQL);
    }

    public ResultSet getProjectParameterById(String columnName, int ID) {
        String selectSQL = "SELECT " + columnName + " FROM public.ProjectsTable WHERE ID =" + ID + ";";

        return dataBaseService.executeQuery(selectSQL);
    }
}
