<html>
    <body>
        <h3>Assignment Portal</h3>
        <hr>
        <form action="UserAuthentication" method="get">
            <table border="0">
                <tr>
                    <td>Userid</td><td><input type="text" name="userid"/></td>
                </tr>
                <tr>
                    <td>Password</td><td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td>Usertype</td><td><select name="usertype"><option>admin</option><option>faculty</option><option>student</option></select></td>
                </tr>
                <tr>
                    <td>Save Password</td><td><input type="checkbox" name="save" value="yes"/></td>
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
