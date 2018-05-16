/*
 
 
package com.sam.sis.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Service;

@Service
public class LoadtoSql {

    public void load() {

        Class driver_class = null;
        try {
            driver_class = Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("found driver" + driver_class);


        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kannel?autoReconnect=true&useSSL=false", "root","admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Established connection to " + connection.getMetaData().getURL());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        Statement statement = null;
        try {
            System.out.println("at statment");
            statement = connection.createStatement();
           
            //windows
            //statement1.executeUpdate( "LOAD DATA LOCAL INFILE 'C:\\Users\\senthil_sivasamy\\Documents\\log.txt' INTO TABLE  trpwatchlog_tb FIELDS TERMINATED BY ' ' LINES TERMINATED BY '\\n'");
            //linux  ( " LOAD DATA LOCAL INFILE '/home/username/logname.log' INTO TABLE  logname.log FIELDS TERMINATED BY ' ' LINES TERMINATED BY '\\n'");
            statement.executeUpdate( "LOAD DATA LOCAL INFILE 'C:/Users/Sam/Documents/numbers.csv' INTO TABLE kannel.numbers FIELDS TERMINATED BY ' ' LINES TERMINATED BY '\\n'");

            
           
           
        } catch(SQLException e) {
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
*/