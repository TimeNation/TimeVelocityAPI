package net.timenation.timevelocityapi.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import net.timenation.timevelocityapi.TimeVelocityAPI;
import net.timenation.timevelocityapi.data.logger.LoggerType;

import javax.sql.DataSource;
import java.sql.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL {

    private final String host;
    private final String user;
    private final String password;
    private final String database;
    private final int port;
    private Connection connection;
    private final ExecutorService executorService;

    public MySQL(String database) {
        this.host = "127.0.0.1";
        this.port = 3306;
        this.user = "user";
        this.password = "w5vffZjUzJnlTXaWIGKcCeblkmjaKU";
        this.database = database;
        this.executorService = Executors.newCachedThreadPool();

        connectToDatabase();
    }

    public void connectToDatabase() {
        if(!isConnectedToDatabase()) {
            try {
                MysqlDataSource mysqlDataSource = new MysqlDataSource();

                mysqlDataSource.setServerName(host);
                mysqlDataSource.setPort(port);
                mysqlDataSource.setDatabaseName(database);
                mysqlDataSource.setUser(user);
                mysqlDataSource.setPassword(password);
                connection = mysqlDataSource.getConnection();
                TimeVelocityAPI.getInstance().getLogger().log("MySQL connection is connected", LoggerType.INFO);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                TimeVelocityAPI.getInstance().getLogger().log("MySQL connection has not connected. ERROR #" + sqlException.getErrorCode(), LoggerType.ERROR);
            }
        }
    }

    public boolean isConnectedToDatabase() {
        return connection != null;
    }

    public void disconnectFromDatabase() {
        if(isConnectedToDatabase()) {
            try {
                connection.close();
                TimeVelocityAPI.getInstance().getLogger().log("MySQL Connection ➳➳ Closed", LoggerType.INFO);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public Connection getConnectionToDatabase() {
        return connection;
    }

    public void updateDatabase(String query) {
        executorService.execute(() -> {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            } catch (SQLException var11) {
                var11.printStackTrace();
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException var10) {
                    var10.printStackTrace();
                }
            }
        });
    }

    public ResultSet getDatabaseResult(String query) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }
}
