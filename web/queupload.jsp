<%
    String userid=(String)session.getAttribute("uid");
    if(userid==null){
        response.sendRedirect("index.jsp");
    }
%>

<html>
    <body>
        <h3>Question-Upload-Page</h3>
        <form action="SaveQuestionServlet">     
            <pre>
            question    <input type="text" name="question"/>
            subject     <select name="subject">
                            <option>java</option>
                            <option>oracle</option>
                            <option>python</option>
                            <option>dotnet</option>
                            <option>cpp</option>
                        </select>
                        <input type="submit" value="Submit"/>
            </pre>
        </form>
    </body>
</html>
