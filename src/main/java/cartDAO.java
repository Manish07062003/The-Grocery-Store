import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class cartDAO {
    Connection connection;
    Statement stmt;
    int noOfRecords;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getInstance().getConnection();
        return con;
    }

    public List<cart> viewAllCartItems(String username) {
        String query = "select carts.item_qty,carts.total_cost,items.item_id,items.item_name,items.category_name,items.item_image,items.item_price,items.item_qty from\n"
                + " items,carts where username=\"" + username + "\" and carts.item_id=items.item_id;";
        List<cart> list = new ArrayList<cart>();
        cart item = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                item = new cart();
                item.setItem_qty(rs.getInt(1));
                item.setItem_id(rs.getString(3));
                item.setItem_name(rs.getString(4));
                item.setCategory_name(rs.getString(5));
                item.setItem_image(rs.getString(6));
                item.setItem_price(rs.getInt(7));
                item.setTotal_cost(rs.getInt(2));
                item.setItem_stock(rs.getInt(8));
                list.add(item);
            }
            rs.close();
            connection.close();
            stmt.close();
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (item == null) {
            return null;
        }
        return list;
    }

    public void insertItemToCart(cart c) {
        String query = "insert into carts values(?,?,?,?)";
        PreparedStatement ps;
        try {
            connection = getConnection();

            ps = connection.prepareStatement(query);

            ps.setString(1, c.getUsername());
            ps.setString(2, c.getItem_id());
            ps.setInt(3, c.getItem_qty());
            ps.setInt(4, c.getTotal_cost());
            ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkItemInCart(String username, String item_id) {
        String query = "Select count(*) from carts where username=\"" + username + "\" and item_id=\"" + item_id
                + "\";";
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    return false;
                }
            }
            rs.close();
            connection.close();
            stmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public void updateQty(String username, String item_id, int item_qty) {
        String query = "update carts set item_qty=" + item_qty + " where username=\"" + username + "\" and item_id=\""
                + item_id + "\";";
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
            stmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAllItems(String item_id) {
        String query = "delete from carts where item_id=\"" + item_id + "\";";
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteUserItem(String username, String item_id) {
        String query = "delete from carts where item_id=\"" + item_id + "\" and username=\"" + username + "\";";
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateStock(String item_id, int item_stock) {
        String query = "update items set item_qty=" + item_stock + " where item_id=\"" + item_id + "\";";
        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addCartItemsToOrders(String username) {
        List<cart> items = viewAllCartItems(username);
        String query = "insert into orders values(?,?,?,?,?,?,?,?);";
        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            for (var item : items) {
                ps.setString(1, username);
                LocalDate dateObj = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date = dateObj.format(formatter);
                ps.setString(2, date);
                ps.setInt(3, item.getTotal_cost());
                ps.setInt(4, item.getItem_qty());
                ps.setString(5, item.getItem_name());
                ps.setString(7, item.getItem_image());
                ps.setString(6, item.getCategory_name());
                ps.setInt(8, item.getItem_price());
                int newStock = item.item_stock - item.getItem_qty();
                if (newStock == 0) {
                    ItemDAO dao = new ItemDAO();
                    dao.deleteItem(item.item_id);
                } else {
                    updateStock(item.item_id, newStock);
                }
                ps.execute();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearCart(String username) {
        String query = "delete from carts where username=\"" + username + "\";";
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<cart> viewUserOrders(String username) {
        String query = "select * from orders where username=\"" + username + "\";";
        List<cart> list = new ArrayList<cart>();
        cart item = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                item = new cart();
                item.setItem_qty(rs.getInt(4));
                item.setItem_name(rs.getString(5));
                item.setCategory_name(rs.getString(6));
                item.setItem_image(rs.getString(7));
                item.setItem_price(rs.getInt(8));
                item.setTotal_cost(rs.getInt(3));
                item.setUsername(username);
                item.setOrder_date(rs.getString(2));
                list.add(item);
            }
            rs.close();
            connection.close();
            stmt.close();
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (item == null) {
            return null;
        }
        return list;
    }

    public List<cart> viewAllOrders() {
        String query = "select * from orders;";
        List<cart> list = new ArrayList<cart>();
        cart item = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                item = new cart();
                item.setItem_qty(rs.getInt(4));
                item.setItem_name(rs.getString(5));
                item.setCategory_name(rs.getString(6));
                item.setItem_image(rs.getString(7));
                item.setItem_price(rs.getInt(8));
                item.setTotal_cost(rs.getInt(3));
                item.setUsername(rs.getString(1));
                item.setOrder_date(rs.getString(2));
                list.add(item);
            }
            rs.close();
            connection.close();
            stmt.close();
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (item == null) {
            return null;
        }
        return list;
    }

    public List<cart> viewQueryOrders(String start_date, String end_date) {
        String query = "select * from orders where order_date between \""+start_date+"\" and \""+end_date+"\";";
        List<cart> list = new ArrayList<cart>();
        cart item = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                item = new cart();
                item.setItem_qty(rs.getInt(4));
                item.setItem_name(rs.getString(5));
                item.setCategory_name(rs.getString(6));
                item.setItem_image(rs.getString(7));
                item.setItem_price(rs.getInt(8));
                item.setTotal_cost(rs.getInt(3));
                item.setUsername(rs.getString(1));
                item.setOrder_date(rs.getString(2));
                list.add(item);
            }
            rs.close();
            connection.close();
            stmt.close();
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (item == null) {
            return null;
        }
        return list;

    }
    public List<cart> viewUserOrderItems(String username) {
        String query = "select * from orders where username=\""+username+"\";";
        List<cart> list = new ArrayList<cart>();
        cart item = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                item = new cart();
                item.setItem_qty(rs.getInt(4));
                item.setItem_name(rs.getString(5));
                item.setCategory_name(rs.getString(6));
                item.setItem_image(rs.getString(7));
                item.setItem_price(rs.getInt(8));
                item.setTotal_cost(rs.getInt(3));
                item.setUsername(rs.getString(1));
                item.setOrder_date(rs.getString(2));
                list.add(item);
            }
            rs.close();
            connection.close();
            stmt.close();
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (item == null) {
            return null;
        }
        return list;

    }
    public void setNoOfRecords(int a) {
        noOfRecords = a;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

}
