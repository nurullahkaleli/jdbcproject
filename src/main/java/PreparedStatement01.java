import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "070920NSe%");


        //--1.Example: Update the number of employees to 9999 if the company name is IBM by using prepared statement
        //1.step Create Prepared Statement Query
        String sql1= "UPDATE companies SET number_of_employees =? WHERE  company=?";

        //2.step-Create Prepared Statement Object
        PreparedStatement ps1= connection.prepareStatement(sql1);

        //3.step-Assign the values by using setInt() setString() methods etc

        ps1.setInt(1, 555);
        ps1.setString(2, "GOOGLE");

        //4.Step-Execute the query for the update
        int numberOfRows = ps1.executeUpdate();
        String sql2 = "SELECT * FROM companies";

        //5.Step - Display result
        Statement statement = connection.createStatement();
        ResultSet rs2 = statement.executeQuery(sql2);

        while (rs2.next()){
            System.out.println(rs2.getInt(1) + " "+ rs2.getString(2)+ "-"+ rs2.getInt(3));
        }




        connection.close();
        statement.close();





    }
}
