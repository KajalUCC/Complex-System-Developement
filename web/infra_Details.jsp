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
    <left_col>
        <button type="button" onclick="window.location.href='add_new_server.html' ">Add new server</button><br><br>
        <button type="button" onclick="window.location.href='send_Request.html' ">Send New Request</button><br><br>
        <button type="button" onclick="window.location.href='send_bulk_Request.html' ">Send Bulk Request</button><br><br>
    </left_col>
<h2 align="center"><font><strong>Data Center Inventory</strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1">
    <tr>

    </tr>
    <tr bgcolor="#A52A2A">
        <td><b>Data Center Count</b></td>
        <td><b>Capacity(GB)</b></td>
        <td><b>Available Servers</b></td>
        <td><b>Temperature(C) </b></td>
        <td><b>Power Consumption(Watt)</b></td>
    </tr>

    <tr bgcolor="#DEB887">

        <td><%=request.getAttribute("server_count") %></td>
        <td><%=request.getAttribute("server_cap") %></td>
        <td><%=request.getAttribute("server_aval") %></td>
        <td><%=request.getAttribute("server_temp") %></td>
        <td><%=request.getAttribute("server_power") %></td>

    </tr>
</table>
</section>
<footer><div style="text-align:right">Data Center Traffic and Infrastructure Management</div>
</footer>
</body>
</html>
