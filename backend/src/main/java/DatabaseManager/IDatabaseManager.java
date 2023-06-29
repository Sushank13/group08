package DatabaseManager;

import java.sql.Connection;

public interface IDatabaseManager
{
    public Connection establishConnection();
    public void closeConnection(Connection connection);

}
