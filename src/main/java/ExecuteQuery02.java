import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws SQLException {

        //1.Step-get the connection

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "070920NSe%");


        //2.Step-Send the query

        Statement statement = connection.createStatement();
        String sql1 = "SELECT company, number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 ROW LIMIT 1";


        //3.Step-Receive the resultSet

        ResultSet rs1 = statement.executeQuery(sql1);
//        rs1.next();
//
//        System.out.println(rs1.getString(1)+ " " + rs1.getInt(2));

          //BU daha dinamik kod.
        while (rs1.next()) {
            System.out.println(rs1.getString(1) + " -- " + rs1.getInt(2));//GOOGLE
        }
        //2.Way
        String sql2 = "SELECT company, number_of_employees FROM companies WHERE number_of_employees= (SELECT MAX(number_of_employees)"
                + "FROM companies WHERE number_of_employees < (SELECT MAX(number_of_employees)FROM companies))";

        ResultSet rs2= statement.executeQuery(sql2);
        while (rs2.next()){
            System.out.println(rs2.getString(1) + " -- " + rs2.getInt(2));//GOOGLE
        }


        System.out.println("======");
        //--2.Example: Find the company names and number of employees whose number of employees is less than the average number of employees

        String sql3= "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies)";

        ResultSet rs3= statement.executeQuery(sql3);

        while(rs3.next()) {

            System.out.println(rs3.getString(1) + " -- " + rs3.getInt(2));
        }


        connection.close();
        statement.close();
    }
}