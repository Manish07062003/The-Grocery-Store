
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class displayOrders
 */
public class displayOrders extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = null;
        Cookie cookie = null;
        cookies = request.getCookies();
        String username = null;
        String userType = null;
        if (cookies != null) {
            String cookieName1 = "userType";
            String cookieName2 = "username";
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals(cookieName1)) {
                    userType = cookie.getValue();
                } else if (cookie.getName().equals(cookieName2)) {
                    username = cookie.getValue();
                }
            }
        }

        if (cookies == null || username == null || userType == null ) {
            response.sendRedirect("login.jsp?msg=Login to access orders");
        } else if(userType.equals("buyers")){
            cartDAO dao = new cartDAO();
            List<cart> list = dao.viewUserOrders(username);
            if (list == null) {
                RequestDispatcher view = request.getRequestDispatcher("orders.jsp?msg=No Orders Found");
                view.forward(request, response);
            } else {
                request.setAttribute("ItemList", list);
                RequestDispatcher view = request.getRequestDispatcher("orders.jsp");
                view.forward(request, response);
            }
        }else if(userType.equals("shopkeepers")) {
            cartDAO dao = new cartDAO();
            List<cart> list = dao.viewAllOrders();
            if (list == null) {
                RequestDispatcher view = request.getRequestDispatcher("orders.jsp?msg=No Orders Found");
                view.forward(request, response);
            } else {
                request.setAttribute("ItemList", list);
                RequestDispatcher view = request.getRequestDispatcher("orders.jsp?username="+username+"&userType="+userType);
                view.forward(request, response);
            }
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
