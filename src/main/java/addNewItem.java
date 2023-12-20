
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.UID;

public class addNewItem extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public addNewItem() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Item item=new Item();
        item.setItem_id(new UID().toString());
        item.setItem_name(request.getParameter("item_name"));
        item.setCategory_name(request.getParameter("item_category"));
        item.setItem_stock(Integer.parseInt(request.getParameter("item_stock")));
        item.setItem_image(request.getParameter("item_img"));
        item.setItem_price(Integer.parseInt(request.getParameter("item_price")));
        ItemDAO dao = new ItemDAO();
        dao.insertItem(item);
        response.sendRedirect("home.jsp?msg=Added Item Successfully");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
