<%--
  Created by IntelliJ IDEA.
  User: ghost
  Date: 24/11/2019
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Drop Downlist</title>
</head>
<body>
<%
    String id = request.getParameter("serverId");
    String driverName = "com.mysql.cj.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://127.0.0.1:3306/csdDB";
    String userId = "root";
    String password = "toor20213.";

    try {
        Class.forName(driverName);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
%>
<form action="serverDetailsServlet" method="post">
<p>Select Name :
    <select name="ServerID">
<%
    try{
        connection = DriverManager.getConnection(connectionUrl, userId, password);
        statement=connection.prepareStatement("select server_id from servers");
        resultSet = statement.executeQuery();
        while(resultSet.next()){
            String serverid=resultSet.getString("server_id");
%>
        <option value="<%=serverid %>"><%=serverid %></option>
<%
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
%>
    </select>
</p>
    <input type="submit" value="Submit" />
</form>
<h2 align="center"><font><strong>Retrieve data from database in jsp</strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1">
    <tr>

    </tr>
    <tr bgcolor="#A52A2A">
        <td><b>Server Load</b></td>
        <td><b>Request Count</b></td>
        <td><b>Disk Consumption</b></td>
        <td><b>Temperature</b></td>
        <td><b>Total Storage</b></td>
        <td><b>Available Capacity</b></td>
    </tr>

    <tr bgcolor="#DEB887">

        <td><%=request.getAttribute("server_load") %></td>
        <td><%=request.getAttribute("request_count") %></td>
        <td><%=request.getAttribute("disk_consumption") %></td>
        <td><%=request.getAttribute("temperature") %></td>
        <td><%=request.getAttribute("total_storage") %></td>
        <td><%=request.getAttribute("available_cap") %></td>

    </tr>
</table>