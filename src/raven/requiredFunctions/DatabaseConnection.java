package raven.requiredFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    // PostgreSQL JDBC driver class name
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "PostgreSQL driver missing", e);
            throw new IllegalStateException("PostgreSQL driver missing");
        }
    }

    private Connection connection;

    private DatabaseConnection() {
        // Private constructor to implement Singleton pattern
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    private void connectToDatabase() throws SQLException {
        // PostgreSQL connection parameters
        String server, port, database, username, password;
        port = "5432";

        if (!false) {

            server = "localhost";
            database = "clg_mngt_db";
            username = "clg_mngt_admin";
            password = "clg_admin";

        } else if (true) {
            server = "ep-steep-cherry-a1ivaifl.ap-southeast-1.aws.neon.tech";
            database = "clg_mngt";
            username = "clg_mngt_db_owner";
            password = "4wTevHb2jNEm";

        } else {

            server = "cf9gid2f6uallg.cluster-czrs8kj4isg7.us-east-1.rds.amazonaws.com";
            port = "5432";
            database = "d7le6oo123ivnf";
            username = "u6b2ajaqip55rs";
            password = "p406510778dd6be5b0f50f56523b89a9f02ba9a4a6d1c5790b9074cfedac00baf";

            // PostgreSQL JDBC connection URL
            // Establish the connection
        }
//        String url = String.format("jdbc:postgresql://%s:%s/%s", server, port, database);
        String url = String.format("postgresql://clg_mngt_db_owner:4wTevHb2jNEm@ep-withered-darkness-a1fec6nn.ap-southeast-1.aws.neon.tech/clg_mngt_db?sslmode=require");
//        connection = DriverManager.getConnection(url, username, password);
        connection = DriverManager.getConnection(url);

    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connectToDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Failed to connect to the database", ex);
            }
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
