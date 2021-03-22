
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserAuthentication extends HttpServlet {

    private PreparedStatement ps,ps1;
    private Connection con;
    
    public void init() {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3307/ProjectData", "root", "root");
        String sql = "SELECT * FROM student WHERE userid=? AND password=?";
        ps = con.prepareStatement(sql);
        ps1= con.prepareStatement("SELECT * FROM faculty where userid=? AND password=?");
        
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

        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String usertype = request.getParameter("usertype");
        String save=request.getParameter("save");
        
        PrintWriter out = response.getWriter();

        if (usertype.equals("admin")) {
            ServletConfig config = getServletConfig();
            String validId = config.getInitParameter("admin-id");
            String validPw = config.getInitParameter("admin-password");
            if (userid.equals(validId) && password.equals(validPw)) {
                if(save!=null){
                    //user wish to save password
                    //step-1 (create cookie object)
                    Cookie c1=new Cookie("id",validId);
                    Cookie c2=new Cookie("pw",validPw);
                    //step-2 (we will set the age)
                    c1.setMaxAge(60*60*24*30);
                    c2.setMaxAge(60*60*24*30);
                    //step-3 (add the cookies to response)
                    response.addCookie(c1);
                    response.addCookie(c2);
                }
                
                
                
                
                //send this request to admindashboard
                response.sendRedirect("admindashboard.jsp");
                
                //out.println("Welcome Admin...");
            } else {
                out.println("Invalid Admin Credentials");
            }
        } else if (usertype.equals("faculty")) {
              try{
                ps1.setString(1, userid);
                ps1.setString(2, password);
                ResultSet rs=ps1.executeQuery();
                boolean found=rs.next();    //true-rs-contains-1-row(credentials are correct,false-rs-empty-credentials are wrong
                if(found){
                    //storing the userid to session (so that other servlets can use)
                    //step-1 (fetch the session for this user)
                    HttpSession session=request.getSession();
                    //step-2 (write the data to session)
                    session.setAttribute("uid", userid);
                    
                    String status=rs.getString("status");
                    if(status.equals("disabled")){
                        response.sendRedirect("facultyprofileupdate.jsp");
                    }else{
                        response.sendRedirect("facultydashboard.jsp");
                    }
                }else{
                    out.println("Invalid Faculty Account");
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        } else if (usertype.equals("student")) {
            //we will match the id/pwd coming with request to credentials stored in db.
            try{
                ps.setString(1, userid);
                ps.setString(2, password);
                ResultSet rs=ps.executeQuery();
                boolean found=rs.next();    //true-rs-contains-1-row(credentials are correct,false-rs-empty-credentials are wrong
                if(found){
                    //RequestDispatcher rd=request.getRequestDispatcher("studentdashboard.jsp");
                    //rd.forward(request, response);
                    response.sendRedirect("studentdashboard.jsp");
                    //out.println("Welcome Student");
                }else{
                    out.println("Invalid Student Account");
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
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
