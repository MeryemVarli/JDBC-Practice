package intro_jdbc;

import metaData.Queries;
import org.junit.Test;

import java.sql.*;

import static metaData.Queries.leftJoins;

public class introJDBC {


  @Test
  public void testConnection() throws SQLException {

      Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@codefish-22.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
              "Student","codefish351");

      Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

      ResultSet resultSet = statement.executeQuery("select * from employees"); //where you write your query

      System.out.println(resultSet.getString(1));

      int count=0;
      while(resultSet.next()){

          System.out.println(resultSet.getString(1));
          System.out.println(resultSet.getString(2));
          System.out.println(resultSet.getString(3));
count++;
      }
      System.out.println(count);

      connection.close();
      statement.close();
      resultSet.close();

  }


  @Test
    public void testQuery() throws SQLException {

      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@codefish-22.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
              "Student","codefish351");

      Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

      ResultSet resultSet = statement.executeQuery(leftJoins()); //you can import that like this but the method has to be static

    ResultSetMetaData metaData = resultSet.getMetaData();

      int columnCount = metaData.getColumnCount();

      System.out.println(columnCount);


      while(resultSet.next()){


          for (int i = 1; i <=columnCount ; i++) {

              System.out.println("|"+resultSet.getObject(i));
          }
          System.out.println(" ");

          connection.close();
          statement.close();
          resultSet.close();
      }


  }


}
