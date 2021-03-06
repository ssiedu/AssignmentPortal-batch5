import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAuthentication extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userid=request.getParameter("userid");
        String password=request.getParameter("password");
        String usertype=request.getParameter("usertype");
        PrintWriter out=response.getWriter();
        
        ServletConfig config=getServletConfig();
        String validId=config.getInitParameter("admin-id");
        String validPw=config.getInitParameter("admin-password");
        
        if(usertype.equals("admin")){
            if(userid.equals(validId) && password.equals(validPw)){
                out.println("Welcome Admin...");
            }else{
                out.println("Invalid Admin Credentials");
            }
        }else if(usertype.equals("faculty")){
            out.println("Welcome Faculty...");
        }else if(usertype.equals("student")){
            out.println("Welcome Student...");
        }
        
        
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
