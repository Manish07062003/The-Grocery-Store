import java.sql.*;
import java.util.*;

public class ItemDAO {
    Connection connection;
    Statement stmt;
    int noOfRecords;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getInstance().getConnection();
        return con;
    }

    public List<Item> viewAllItems(int offset, int noOfRecords) {
        String query = "select SQL_CALC_FOUND_ROWS * from items limit " + offset + ", " + noOfRecords;
        List<Item> list = new ArrayList<Item>();
        Item item = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                item = new Item();
                item.setItem_id(rs.getString(1));
                item.setItem_name(rs.getString(2));
                item.setCategory_name(rs.getString(3));
                item.setItem_stock(rs.getInt(4));
                item.setItem_image(rs.getString(5));
                item.setItem_price(rs.getInt(6));
                list.add(item);
            }
            rs.close();
            rs = stmt.executeQuery("Select FOUND_ROWS()");
            if (rs.next()) {
                setNoOfRecords(rs.getInt(1));
            }
            connection.close();
            stmt.close();
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(item==null) {
            return null;
        }
        return list;
    }

    public List<Item> searchItems(int offset, int noOfRecords,String itemName) {
        String query = "select SQL_CALC_FOUND_ROWS * from items where item_name=\""+itemName+"\" limit " + offset + ", " + noOfRecords;
        List<Item> list = new ArrayList<Item>();
        Item item = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                item = new Item();
                item.setItem_id(rs.getString(1));
                item.setItem_name(rs.getString(2));
                item.setCategory_name(rs.getString(3));
                item.setItem_stock(rs.getInt(4));
                item.setItem_image(rs.getString(5));
                item.setItem_price(rs.getInt(6));
                list.add(item);
            }
            rs.close();
            rs = stmt.executeQuery("Select FOUND_ROWS()");
            if (rs.next()) {
                setNoOfRecords(rs.getInt(1));
            }
            connection.close();
            stmt.close();
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(item==null) {
            return null;
        }
        return list;
    }

    public void insertItem(Item item) {
        String query = "insert into items values(?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            connection = getConnection();

            ps = connection.prepareStatement(query);

            ps.setString(1, item.getItem_id());
            ps.setString(2, item.getItem_name());
            ps.setString(3, item.getCategory_name());
            ps.setInt(4, item.getItem_stock());
            ps.setString(5, item.getItem_image());
            ps.setInt(6, item.getItem_price());
            ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Item getItem(String item_id) {
        Item item = new Item();
        String query = "select item_id,item_qty from items where item_id=\"" + item_id + "\";";
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                item.setItem_id(rs.getString(1));
                item.setItem_stock(rs.getInt(2));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return item;
    }

    public void updateStock(String item_id, int item_qty) {
        String query = "update items set item_qty=" + item_qty + " where item_id=\"" + item_id + "\";";
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteItem(String item_id) {
        cartDAO dao = new cartDAO();
        dao.deleteAllItems(item_id);
        String query = "delete from items where item_id=\"" + item_id + "\";";
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setNoOfRecords(int a) {
        noOfRecords = a;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

}
