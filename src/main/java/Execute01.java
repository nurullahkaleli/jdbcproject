import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1st Step: Registration to the driver
        Class.forName("org.postgresql.Driver");// This is not necessary JDBC 4

        //2nd Step:Create Connection to database
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","070920NSe%");

        //3rd Step: Create Statement
        Statement statement = connection.createStatement();

        //4th Step: Execute Query

        /*
        execute() method can be used in DDL(Data Definition Language --> Create, Drop, Alter Table) DQL(Data Query Language --> SELECT)
            1) If you use execute() method with DDL, you will always get false
            2) If you use execute() method with DQL, you will get false or true
                If you get the ResultSet as return you will get true otherwise you will get false
         */


        //1. Example: Create a workers table with the columns worker_id, worker_name and worker_salary
        String sql1 = "CREATE TABLE workers(worker_id VARCHAR(10), worker_name VARCHAR(50), worker_salary INT)";
        boolean r1 = statement.execute(sql1);
        System.out.println("r1 = " + r1);//false

        //2.Example: Alter table by adding worker_address column into the workers table
        String sql2 = "ALTER TABLE workers ADD worker_address VARCHAR(100)";
        boolean r2 = statement.execute(sql2);
        System.out.println("r2 = " + r2);

        //3. Example: Drop the table
        String sql3 ="DROP TABLE workers";
        statement.execute(sql3);

        //5th Step: Close the connection and statement
        connection.close();
        statement.close();

    }
}
