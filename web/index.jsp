<%
    //reading cookies
    //step-1    (fetch all cookies coming with request)
    Cookie ck[]=request.getCookies();
    //step-2    (search for desired one)
    //searching for 2 cookies (id,pw)
    String v1="",v2="";
    
    if(ck!=null)
        for(Cookie c:ck){
            String name=c.getName();
            if(name.equals("id")){
                v1=c.getValue();
            }else if(name.equals("pw")){
                v2=c.getValue();
            }
        }
    
%>

<html>
    <body>
        <h3>Assignment Portal</h3>
        <hr>
        <form action="UserAuthentication" method="get">
            <table border="0">
                <tr>
                    <td>Userid</td><td><input type="text" name="userid" value="<%=v1%>" /></td>
                </tr>
                <tr>
                    <td>Password</td><td><input type="password" name="password" value="<%=v2%>"/></td>
                </tr>
                <tr>
                    <td>Usertype</td><td><select name="usertype"><option>admin</option><option>faculty</option><option>student</option></select></td>
                </tr>
                <tr>
                    <td>Save Password</td><td><input type="checkbox" name="save" value="yes" checked="checked" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login"/><td><input type="reset" value="Reset"/></td>
                </tr>
            </table>
        </form>
        <hr>
        <a href="registration.jsp">New-Student-Registration</a>
    </body>
</html>
