package database;

import java.sql.Connection;

public interface IDatabaseConnection {

    /**
     * Creates database connection using parameters defined in database.properties
     * @return SQL connection to database
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    Connection getDatabaseConnection() throws Exception;

    /**
     * Closes SQL connection to database
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    void closeDatabaseConnection() throws Exception;
}
