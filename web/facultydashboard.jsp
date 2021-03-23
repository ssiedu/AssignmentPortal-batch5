<%
    //session.setMaxInactiveInterval(90);
    String userid=(String)session.getAttribute("uid");
    if(userid==null){
        response.sendRedirect("index.jsp");
    }
    int val=session.getMaxInactiveInterval();
%>
<html>
    <body>
        
        <h3>Welcome <%=userid%></h3>
        <h3>If you remain idle for <%=val%> seconds , your session will expire</h3>
        <h3>Faculty-Dashboard</h3>
        <hr>
        <pre>
            <a href="queupload.jsp">Upload-Questions</a>
            <a href="">View-Questions</a>
            <a href="EndSession">Logout</a>

        </pre>
        <hr>
    </body>
</html>
