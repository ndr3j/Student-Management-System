package StudentController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentDb {


    static Connection connection;
    public static Connection Connect() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo", "root", "1234");

            System.out.println("Success");

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Failed");
        }

        return connection;
    }}


