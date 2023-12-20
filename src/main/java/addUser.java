
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.UID;

/**
 * Servlet implementation class addUser
 */
public class addUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Users user = new Users();
        user.setId(new UID().toString());
        user.setType(request.getParameter("userType"));
        user.setFirstname(request.getParameter("firstname"));
        user.setLastname(request.getParameter("lastname"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setAddress(request.getParameter("address"));
        user.setEmail(request.getParameter("email"));
        userDAO dao = new userDAO();
        if (dao.checkUsername(user)) {
            request.setAttribute("error", "Username Already Exists");
            RequestDispatcher view = request.getRequestDispatcher("error.jsp");
            view.forward(request, response);

        } else {
            Cookie cookie1 = new Cookie("userType", user.getType());
            Cookie cookie2 = new Cookie("username", user.getUsername());
            cookie1.setMaxAge(60 * 60 * 24);
            cookie2.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            dao.insertUser(user);
            response.sendRedirect("shopDisplay");
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
