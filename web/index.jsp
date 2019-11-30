<html>
<head>
  <title>Complex system Developement- Add new Server</title>
  <link rel="stylesheet" type="text/css" href="CSD_Main_Layout.css">
  <style>

    .form form{

      width:500px;
      background: white ;
      border-radius: 50px;
      border-width: thick;
      overflow: hidden;
      justify-content: center;
      padding: 45px 300px 40px 300px;
      color: black;
    }
    .form input{
      background: white;
      font-family: Poppins-Medium;
      color: black;
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
      color: black;
    }
  </style>
</head>

<body>
<header>
  <div class="container">
    <h1>DataCenter Traffic and Infrastructure Management</h1>

  </div>
</header>
<section>
  <left_col>
  </left_col>
  <right_col><h1 style ="text-align:center">Login Page</h1>
    <div class="form">
  <form name="loginForm" method="post" action="LoginServlet.java">
    Username: <input type="text" name="username"/> <br/>
    Password: <input type="password" name="password"/> <br/>
    <input type="submit" value="Login" /></form>
    </div>
</section>
<footer><div style="text-align:right">Data Center Traffic and Infrastructure Management</div>
</footer>
</body>
</html>
