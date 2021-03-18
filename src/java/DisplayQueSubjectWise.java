import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayQueSubjectWise extends HttpServlet {

    PreparedStatement ps;
    Connection con;

    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/ProjectData", "root", "root");
            String sql = "select code,question,sdate,name,subject from quebank,faculty where quebank.fid=faculty.userid and subject=?";
            ps = con.prepareStatement(sql);
        } catch (Exception e) {
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
            PrintWriter out=response.getWriter();
            
            String subject=request.getParameter("sub");

            //we will fetch the questions-list for this subject from db and will show them in presentable manner
            try{
                ps.setString(1, subject);
                ResultSet rs=ps.executeQuery();
                out.println("<html>");
                out.println("<body>");
                out.println("<h3>Question-List-For-"+subject+"</h3>");
                out.println("<hr>");
                out.println("<table border=2>");
                out.println("<tr>");
                out.println("<th>Sno</th><th>Qno</th><th align=left>Que</th><th align=left>Date</th><th>Faculty</th>");
                out.println("</tr>");
                int sn=1;
                while(rs.next()){
                    String code=rs.getString("code");
                    String que=rs.getString("question");
                    String sdate=rs.getString("sdate");
                    String faculty=rs.getString("name");
                    
                    out.println("<tr>");
                    out.println("<td>"+sn+"</td>");
                    out.println("<td>"+code+"</td>");
                    out.println("<td>"+que+"</td>");
                    out.println("<td>"+sdate+"</td>");
                    out.println("<td>"+faculty+"</td>");
                    out.println("</tr>");
                    sn++;
                }
                out.println("</table>");
                out.println("<hr>");
                out.println("<a href=studentdashboard.jsp>Student-Dashboard</a><br>");
                out.println("<a href=SubjectListServlet>Subject-Page</a><br>");
                out.println("</body>");
                out.println("</html>");
            }catch(Exception e){
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
