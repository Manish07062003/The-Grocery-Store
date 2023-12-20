
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
        user.setType(request.getParameter("userType"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        userDAO dao = new userDAO();
        if (dao.checkUsernamePassword(user)) {
            Cookie cookie1=new Cookie("userType",user.getType());
            Cookie cookie2=new Cookie("username",user.getUsername());
            cookie1.setMaxAge(60*60*24);
            cookie2.setMaxAge(60*60*24);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.sendRedirect("shopDisplay");
        } else {
            response.sendRedirect("login.jsp?msg=Incorrect Username And Password");
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
