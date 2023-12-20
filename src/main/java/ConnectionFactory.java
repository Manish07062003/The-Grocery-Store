import java.sql.*;

public class ConnectionFactory {
    private static ConnectionFactory instance = new ConnectionFactory();

    private String url = "jdbc:mysql://localhost:3306/Grocery_Store";
    private String user = "root";
    private String password = "Manish@123";
    private String driverClass = "com.mysql.cj.jdbc.Driver";


    private ConnectionFactory()

    {
        try {

            Class.forName(driverClass);

        }
        catch (ClassNotFoundException e) {

            e.printStackTrace();

        }
    }

    public static ConnectionFactory getInstance()

    {
        return instance;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException
    {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

}
