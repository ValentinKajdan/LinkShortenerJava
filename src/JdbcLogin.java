import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class JdbcLogin {


    public String Login;
    public String MotDePasse;
    private boolean Logged = false;

    public Connection db(String username, String password, String url) {
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName); // here is the ClassNotFoundException
            
            Connection cn =(Connection) DriverManager.getConnection(url, username, password);

            return cn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}