
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class shopDisplay
 */
public class shopDisplay extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public shopDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int recordsPerPage;
        if(request.getParameter("noOfItems")==null) {
           recordsPerPage=10; 
        }else {
            recordsPerPage=Integer.parseInt(request.getParameter("noOfItems"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        ItemDAO dao = new ItemDAO();
        List<Item> list = dao.viewAllItems((page - 1) * recordsPerPage, recordsPerPage);
        if (list == null) {
            RequestDispatcher view = request.getRequestDispatcher("shop.jsp?msg=No Items Found");
            view.forward(request, response);
        } else {
            int noOfRecords = dao.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("ItemList", list);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            String msg = request.getParameter("msg");
            RequestDispatcher view = request.getRequestDispatcher("shop.jsp?msg=" + msg);
            view.forward(request, response);
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
