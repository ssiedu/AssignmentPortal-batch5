
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveQuestionServlet extends HttpServlet {

    private PreparedStatement ps;
    private Connection con;
    
    public void init() {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3307/ProjectData", "root", "root");
        String sql = "INSERT INTO quebank(question,sdate,fid,subject) VALUES(?,?,?,?)";
        ps = con.prepareStatement(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            con.close();
            System.out.println("Connection Closed.............");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        //read-the-request
        //userid&password&name&address&email&mobile
        String userid = request.getParameter("userid"); //3
        String question = request.getParameter("question"); //1
        String subject = request.getParameter("subject"); //4
        //2nd parameter is date (current-date)
        java.util.Date dt=new java.util.Date();
        long val=dt.getTime();
        java.sql.Date sqlDate=new java.sql.Date(val);//2
        
        //process-the-request
        try {
            
            ps.setString(1, question);
            ps.setDate(2, sqlDate);
            ps.setString(3, userid);
            ps.setString(4, subject);
            ps.executeUpdate();
            //provide the response
            out.println("<html>");
            out.println("<body>");
            out.println("<hr>");
            out.println("<h3>Question-Stored-Successfully</h3>");
            out.println("<hr>");
            out.println("<h4><a href=queupload.jsp>Add-Another-Question</a></h4>");
            out.println("<h4><a href=facultydashboard.jsp>Faculty-Dashboard</a></h4>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (Exception e) {
            //e.printStackTrace();
            out.println(e);
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
