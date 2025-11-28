import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class Database {
   private static final String DB_URL="jdbc:mariadb://localhost:3306/bank";
   private static final String USERNAME="bankuser";
   private static final String PASSWORD="bankpass";


   public static Connection getConnection(){
       try{
           return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
       }catch (SQLException e) {
           throw new DatabaseConnectionException("Database connection failed.", e);
       }
   }
}