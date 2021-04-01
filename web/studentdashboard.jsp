<%@taglib uri="/WEB-INF/tlds/mylib.tld" prefix="tags" %>
<html>
    <body>
        <h3>Student-Dashboard</h3>
        <hr>
        <pre>
            <a href="SubjectListServlet">View-Questions-Subject-Wise</a>
            <a href="">View-Questions-Date-Wise</a>
            <a href="">Upload Solution</a>
            <a href="index.jsp">Logout</a>
        </pre>
        <hr>
        <h3>Our-Que-Bank</h3>
        <hr>
        <tags:questions/>
    </body>
</html>
