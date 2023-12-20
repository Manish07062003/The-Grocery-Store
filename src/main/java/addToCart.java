
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class addToCart
 */
public class addToCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public addToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String item_id = request.getParameter("item_id");
        int item_qty = Integer.parseInt(request.getParameter("item_qty"));
        int item_price = Integer.parseInt(request.getParameter("item_price"));
        int total_cost=item_qty*item_price;
        Cookie[] cookies = null;
        Cookie cookie = null;
        String username=null;
        String userType=null;
        cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }else if(cookie.getName().equals("userType")) {
                    userType=cookie.getValue();
                }
            }
        }
        if(username==null || userType==null || userType.equals("shopkeepers")) {
            response.sendRedirect("login.jsp?msg=Login as a buyer to add items to cart");

        }else {
            cartDAO dao=new cartDAO();
            if(dao.checkItemInCart(username, item_id)) {
               dao.updateQty(username, item_id, item_qty);
            }else {
                cart c=new cart();
                c.setItem_id(item_id);
                c.setItem_qty(item_qty);
                c.setTotal_cost(total_cost);
                c.setUsername(username);
                dao.insertItemToCart(c);
            }
            String path = request.getHeader("referer");
            response.sendRedirect(path);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
