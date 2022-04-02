package racing_team;

public class RacingDatabaseQueries {


    public static final String INSERT_SAMPLE_RACING_DRIVERS = "INSERT INTO racingdriver (age, name, nationality, outfitColor, carId, salary) values (?, ?, ?, ?, ?, ?)";
    public static final String FIND_PROJECTS_BY_BUDGET = "SELECT * FROM PROJECTS WHERE budget >= %d";
    public static final String FIND_PROJECT_BY_NAME = "SELECT * FROM PROJECTS WHERE projectName = '%s'";
    public static final String ADD_PROJECT = "INSERT INTO PROJECTS (projectName, budget) VALUES ('%s', %d)";
    public static final String UPDATE_PROJECT_BY_ID = "UPDATE PROJECTS SET projectName = ?, budget = ? WHERE projectId = ?";
    public static final String FIND_PROJECT_BY_ID = "SELECT * FROM PROJECTS WHERE projectId = ?";
    public static final String DELETE_PROJECT_BY_ID = "DELETE FROM PROJECTS WHERE projectId = ?";
    public static final String FIND_ALL_ENGINEERS_WHO_WORKING_ON_PROJECTS =
            "SELECT * FROM engineers, projects WHERE engineers.projectId = projects.projectId ORDER BY engineers.projectId DESC;";
}

