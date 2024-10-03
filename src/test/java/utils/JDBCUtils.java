package utils;

import org.junit.After;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

  static Connection connection;

  static Statement statement;

  static ResultSet resultSet;



    private static Statement establishConnection(){

        try {
            connection = DriverManager.getConnection(getProp("hostName"),
                    getProp("username"),
                    getProp("password"));

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException("Could not connect to database"+e.getMessage());
        }

        return statement;
    }






    public static ResultSet queryDB(String query){

        statement = establishConnection();

        try {
            resultSet = statement.executeQuery(query);
            return resultSet;
        }catch (SQLException exception){
            throw new RuntimeException("Failed running query" + exception.getMessage());
        }
    }



    private static  String getProp(String key) {

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(new File("db.properties")));
        }catch (IOException exception){
            throw new RuntimeException("Could not load or find properties");
        }

        return  properties.getProperty(key);
    }




    @After
    public void closeConnections(){

        System.out.println("Connection closed");

        try{
            if (resultSet != null){
                resultSet.close();
            }

            if (statement != null){
                statement.close();
            }

            if (connection !=null){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


    }


}
