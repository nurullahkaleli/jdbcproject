import java.sql.*;

public class CallableStatement01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "070920NSe%");
        Statement statement = connection.createStatement();

        //1.Example: Create a function which uses 2 parameters and return the sum of the parameters
        //1.Step:
        String sql1 =" CREATE OR REPLACE FUNCTION additionF(x NUMERIC, y NUMERIC) RETURNS NUMERIC LANGUAGE plpgsql AS $$ BEGIN RETURN x+y; END $$";

        //2.Step: Execute the function

        statement.execute(sql1);

        //3.Step:Prepare the callable statement

        CallableStatement cs1 = connection.prepareCall("{? = call additionF(?, ?) }");

        //4.Step: Use registerOut() for result output and setInt() to set up the parameters

        cs1.registerOutParameter(1,Types.NUMERIC);
        cs1.setInt(2,6);
        cs1.setInt(3,4);

        //5.Step: Execute callable statement

        cs1.execute();

        //6th.Step: Print the result

        System.out.println(cs1.getObject(1));


        //2.Example: Create a function which calculate the volume of a cone--r*r*h*3,14/3

        //1.Step:
        String sql2 =" CREATE OR REPLACE FUNCTION volumeOfCone(r NUMERIC, h NUMERIC) RETURNS NUMERIC LANGUAGE plpgsql AS $$ BEGIN RETURN r*r*h*3.14/3; END $$";

        //2.Step: Execute the function

        statement.execute(sql2);

        //3.Step:Prepare the callable statement

        CallableStatement cs2 = connection.prepareCall("{? = call volumeOfCone(?, ?) }");

        //4.Step: Use registerOut() for result output and setInt() to set up the parameters

        cs2.registerOutParameter(1,Types.NUMERIC);
        cs2.setInt(2,2);
        cs2.setInt(3,6);

        //5.Step: Execute callable statement

        cs2.execute();

        //6th.Step: Print the result

        System.out.println(cs2.getObject(1));
        System.out.printf("%.2f",cs2.getObject(1));












        connection.close();
        statement.close();



    }
}
