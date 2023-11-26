<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="app/homestyle.css" />

    
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">

    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
    />

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
  </head>

  <body id="body-pd">
  <div class="main">
    <header class="header" id="header">
      <div class="header_toggle">
        <i class="bx bx-menu" id="header-toggle"></i>
      </div>
      <!-- <div class="header_img"> <img src="https://i.imgur.com/hczKIze.jpg" alt=""> </div> -->
    </header>
    <div class="l-navbar" id="nav-bar">
      <nav class="nav">
        <div>
          <a href="#" class="nav_logo">
            <i class="bx bx-layer nav_logo-icon"></i>
            <span class="nav_logo-name"><h2>VOTIFY APP</h2></span>
          </a>
          <div class="nav_list">
            <a href="./home" class="nav_link active">
              <i class="bx bx-grid-alt nav_icon"></i>
              <span class="nav_name">Dashboard</span>
            </a>
              <a href="./active" class="nav_link">
              <i class="bx bx-bar-chart-alt-2 nav_icon"></i>
              <span class="nav_name">View Active polls</span>
            </a>
            
            <a href="./create" class="nav_link">
             <i class="bi bi-patch-plus" style="font-size: 23px;"></i>
              <span class="nav_name">Create A poll</span>
            </a>
          
          
            <a href="./message" class="nav_link">
             <i class="bx bx-message-square-detail nav_icon"></i>
              <span class="nav_name">Messages</span>
            </a>
             <a href="./help" class="nav_link">
              <i class="bi bi-patch-question" style="font-size: 23px;"></i>
              <span class="nav_name">Help</span>
            </a>
            <a href="./about" class="nav_link">
              <i class="bx bx-user nav_icon"></i>
              <span class="nav_name">About us</span>
            </a>
           
          </div>
        </div>
        <a href="./logout" class="nav_link">
          <i class="bx bx-log-out nav_icon"></i>
          <span class="nav_name">SignOut</span>
        </a>
      </nav>
    </div>
    <!--Container Main start-->
    <div class="height-100 bg-light">
      <%
   String userName = (String) session.getAttribute("userName");
   if (userName != null) {
%>
   <h4>Welcome <%= userName %></h4>
<%
   } else {
%>
   <h4>Welcome Guest</h4>
<%
   }
%>
      <div>
      <h1> This is the Dashboard section</h1>
    </div>
    <!--Container Main end-->

    <script>
      document.addEventListener("DOMContentLoaded", function (event) {
        const showNavbar = (toggleId, navId, bodyId, headerId) => {
          const toggle = document.getElementById(toggleId),
            nav = document.getElementById(navId),
            bodypd = document.getElementById(bodyId),
            headerpd = document.getElementById(headerId);

          // Validate that all variables exist
          if (toggle && nav && bodypd && headerpd) {
            toggle.addEventListener("click", () => {
              // show navbar
              nav.classList.toggle("show");
              // change icon
              toggle.classList.toggle("bx-x");
              // add padding to body
              bodypd.classList.toggle("body-pd");
              // add padding to header
              headerpd.classList.toggle("body-pd");
            });
          }
        };

        showNavbar("header-toggle", "nav-bar", "body-pd", "header");

        /*===== LINK ACTIVE =====*/
        const linkColor = document.querySelectorAll(".nav_link");

        function colorLink() {
          if (linkColor) {
            linkColor.forEach((l) => l.classList.remove("active"));
            this.classList.add("active");
          }
        }
        linkColor.forEach((l) => l.addEventListener("click", colorLink));

        // Your code to run since DOM is loaded and ready
      });
    </script>
    </div>
  </body>
</html>
