import java.sql.*;

public class userDAO {
    Connection connection;
    Statement stmt;
    int noOfRecords;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getInstance().getConnection();
        return con;
    }

    public void insertUser(Users user) {
        String query = "insert into " + user.getType() + " values(?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            connection = getConnection();

            ps = connection.prepareStatement(query);

            ps.setString(1, user.getId());
            ps.setString(2, user.getFirstname());
            ps.setString(3, user.getLastname());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setString(7, user.getEmail());
            ps.setString(6, user.getAddress());
            ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkUsername(Users user) {
        String query = "select count(*) from " + user.getType() + " where username=\"" + user.username + "\";";
        PreparedStatement ps;
        try {
            connection = getConnection();

            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public boolean checkUsernamePassword(Users user) {
        String query = "select count(*) from " + user.getType() + " where password=\"" + user.getPassword()+"\" and username=\""+user.getUsername()+"\";";
        PreparedStatement ps;
        try {
            connection = getConnection();

            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public void setNoOfRecords(int a) {
        noOfRecords = a;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

}
