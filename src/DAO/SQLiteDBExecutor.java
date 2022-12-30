package DAO;

import java.sql.*;

/**
 * @author bao20
 * @version 1.1
 * @since 1.0
 */
public class SQLiteDBExecutor {
    /**
     * Connect to database
     *
     * @return A Connection to database
     */
    public static Connection connect() {
        Connection conn = null;
        try {

            // create a connection to the database
            conn = DriverManager.getConnection(Constant.DB_URL);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * Close connection database
     *
     * @param conn Connection to database
     */
    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Execute query to database with parameter
     *
     * @param sqlStatement A sql statement to execute
     * @param conn         A connection to database
     * @param parameter    List of parameter to fill in query string
     * @return A ResultSet representing a database result set data
     */
    public static ResultSet executeQuery(String sqlStatement, Connection conn, Object... parameter) {
        ResultSet data = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);

            for (int i = 0; i < parameter.length; i++) {
                fillPrepareStatement(statement, i + 1, parameter[i]);
            }
            data = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Execute query to database with no parameter
     *
     * @see SQLiteDBExecutor#executeQuery(String, Connection, Object...)
     */
    public static ResultSet executeQuery(String sqlStatement, Connection conn) {
        ResultSet data = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            data = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Execute non query to database with parameter
     *
     * @param sqlStatement A SQL statement to execute
     * @param conn         A connection to database
     * @param parameter    List of parameter to fill in query string
     * @return A Boolean representing success or fail of execute sql statement
     */
    public static boolean executeNonQuery(String sqlStatement, Connection conn, Object... parameter) {
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            for (int i = 0; i < parameter.length; i++) {
                fillPrepareStatement(statement, i + 1, parameter[i]);
            }
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Execute non query to database with no parameter
     *
     * @param sqlStatement
     * @param conn
     * @return 
     * @see SQLiteDBExecutor#executeNonQuery(String, Connection, Object...)
     */
    public static boolean executeNonQuery(String sqlStatement, Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Fill in sql statement with parameter
     *
     * @param preparedStatement SQL statement to fill in
     * @param index             index of parameter (start by 1)
     * @param parameter         parameter to fill in
     * @throws SQLException
     */
    private static void fillPrepareStatement(PreparedStatement preparedStatement,
                                             int index,
                                             Object parameter) throws SQLException {
        if (preparedStatement == null) return;
        if (Integer.class.equals(parameter.getClass())) preparedStatement.setInt(index, (Integer) parameter);
        else if (String.class.equals(parameter.getClass())) preparedStatement.setString(index, (String) parameter);        
        else if (Double.class.equals(parameter.getClass())) preparedStatement.setDouble(index, (Double) parameter);
        else if (byte[].class.equals(parameter.getClass())) preparedStatement.setBytes(index, (byte[]) parameter);
    }
}

