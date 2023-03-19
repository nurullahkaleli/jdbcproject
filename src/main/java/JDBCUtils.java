import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtils {

    public static Connection connection;
    public static Statement statement;

    //1.step:Create the connection

    public static Connection connectToDatabase() {

        try {
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "070920NSe%");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;

    }

    //2.step:Create Statement

    public static Statement createStatement(){

        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;

    }
    //3.step: Execute Query
    public static boolean execute(String query){

        boolean result = false;
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    //4.step: Close  connection and statement
    public static void closeConnectionAndStatement() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Method to drop table
    public static void dropTable(String tableName){
        try {
            statement.execute("DROP TABLE " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //The method to put column data into a list

    public static List<Object> getColumnList(String columnName, String tableName){
        List<Object> list= new ArrayList<>();

        String sql= "SELECT " +columnName + " FROM " + tableName;

        ResultSet resultSet;
        try {
            resultSet =statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                list.add(resultSet.getObject(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return list;

    }



}