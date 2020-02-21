<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
String id = request.getParameter("userId");
String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String dbName = "Quickchef";
String userId = "root";
String password = "root";

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<h2 align="center"><font><strong>Retrieve data from database in jsp</strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>name</b></td>
<td><b>image_url</b></td>
<td><b>rating</b></td>
<td><b>address</b></td>
<td><b>longitude</b></td>
</tr>
<%
try{ 
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Quickchef","root","root");
statement=connection.createStatement();
String sql ="SELECT * FROM restaurants";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr bgcolor="#DEB887">
     
<td><%=resultSet.getString("name") %></td>
<td><%=resultSet.getString("image_url") %></td>
<td><%=resultSet.getString("rating") %></td>
<td><%=resultSet.getString("address") %></td>
<td><%=resultSet.getString("longitude") %></td>


</tr>

<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</table>