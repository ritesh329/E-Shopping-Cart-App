<%@ page import=" model.*" %> 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
    <%
      User au=(User) request.getSession().getAttribute("auth");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./navbar.jsp">
  <style>
  * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {

      width: 100%;
      height: 100vh;
      background-color: #f8f8f8;
    }

    header {
      height: 60px;
      width: 100%;
      background: #222121;
      color: #fff;
      display: flex;
      align-items: center;
    }

    .div-container {
      width: 90%;
      margin: 0 auto;
      display: flex;
      justify-content: space-between;
    }

    nav ul li {
      list-style: none;
      display: inline;
      margin-right: 20px;
      font-weight: 550;

    }

    .logo {
      font-size: 24px;
    }
    .nav-list a .Badge{
    
        
        color:white;
        font-weight:800;
        font-size:12px;
      
        text-align:center;
        background-color:red;
        margin-left:5px;
        padding-left:3px;
        border-radius:5px;
          list-style:none;
          text-decoration:none;        
    }
    
     .nav-list a{
     
       text-decoration:none;
      
      }
    </style>
</head>
<body>

       <header>
    <div class="div-container">
      <h1 class="logo">E Shopping Cart</h1>
      <nav>
        <ul class="nav-list">
          <li> <a href="index.jsp">home </a></li>
          <li> <a href="cart.jsp">Cart <sup class="Badge">  ${ cart_list.size()} </sup> </a></li>
        
           
        
          <li> <a href="log-out">Logout</a></li>
       
           <%
           
             if(au!=null){%>
            	 
            	 <li > <a href="login.jsp" style="visibility: hidden;">Login</a></li>
               <li> <a href="order.jsp">Orders </a></li>
          <%}
            else {%>
            
                 <li > <a href="login.jsp" style="visibility: visible;">Login</a></li> 
            
            <%}
           %>
              
           
         
           
        
           
      
        </ul>
      </nav>
    </div>
  </header>
</body>
</html>