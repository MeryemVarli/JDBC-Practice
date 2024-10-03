package intro_jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.*;

public class AdvancedJDBC {



 @Test
 public void TC_01() throws SQLException {

     Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@codefish-22.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL"
             ,"Student","codefish351"); //creating a connection to the Oracle database using the JDBC driver

     Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

     ResultSet resultSet = statement.executeQuery("select * from employees");

     ResultSetMetaData metaData = resultSet.getMetaData(); //metaData provides information about the data itself



     List<Map<String, Object>> employeeInfo = new ArrayList<>();

     while (resultSet.next()){ //while i have data

         Map<String,Object> rowMap = new HashMap<>();

         for (int i = 1; i < metaData.getColumnCount(); i++) {

            rowMap.put(metaData.getColumnName(i),resultSet.getObject(i));
         }

         employeeInfo.add(rowMap);


     }
     System.out.println(employeeInfo);

     for (int i = 0; i < employeeInfo.size(); i++) {
         System.out.println(employeeInfo.get(i));

     }


 }



 @Test
    public void TC_02(){







 }

















}
