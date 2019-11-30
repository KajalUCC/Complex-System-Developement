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
<link rel="stylesheet" type="text/css" href="CSD_Main_Layout.css">
<style>

    .form form{

        width:500px;
        background: #FCE4EC  ;
        border-radius: 50px;
        border-width: thick;
        overflow: hidden;
        justify-content: center;
        padding: 45px 300px 40px 300px;


    }
    .form input{
        background: white;
        font-family: Poppins-Medium;
        color: #333333;
        line-height: 1.2;
        position: relative;
        border-bottom: 2px solid #d9d9d9;
        padding-bottom: 5px;
        margin-bottom: 5px;
        -webkit-box-shadow: 0 2px 10px 1px rgba(0,0,0,0.5);
        box-shadow: 0 2px 10px 1px rgba(0,0,0,0.5);
    }
    .form label{
        float: left;
        display: inline-block;
        width: 100px;

    }
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<header>
  <div class="container">
    <h1>Data Center Traffic and Infrastructure Management</h1>
  </div>
</header>
<section>
    <h2 align="center"><font><strong>Data Center Inventory</strong></font></h2>
    <table align="center" cellpadding="5" cellspacing="5" border="1"> <tr bgcolor="#A52A2A">
        <td><b>Server Id</b></td>
        <td><b>Server Load</b></td>
        <td><b>Request Count</b></td>
        <td><b>Disk Consumption(%)</b></td>
        <td><b>Temperature(C)</b></td>
        <td><b>Total Storage(GB)</b></td>
        <td><b>Available Capacity</b></td>
    </tr>
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
<%
    try{
        connection = DriverManager.getConnection(connectionUrl, userId, password);
        statement=connection.prepareStatement("select * from server_metrics");
        resultSet = statement.executeQuery();
        while(resultSet.next()){
%>
    <tr>


    <tr bgcolor="#DEB887">

        <td><%=resultSet.getInt (1)%></td>
        <td><%=resultSet.getFloat (2) %></td>
        <td><%=resultSet.getInt (3) %></td>
        <td><%=resultSet.getString (4) %></td>
        <td><%=resultSet.getString (5) %></td>
        <td><%=resultSet.getString (6) %></td>
        <td><%=resultSet.getFloat (7) %></td>

    </tr>
    <%
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
    </table>
</section>
<footer><div style="text-align:right">Data Center Traffic and Infrastructure Management</div>
</footer>
</body>
</html>
