<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="app/homestyle.css" />
    <link rel="stylesheet" href="app/css/multi.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512...">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body id="body-pd">
    <header class="header" id="header">
        <div class="header_toggle">
            <i class="bx bx-menu" id="header-toggle"></i>
        </div>
    </header>
    <div class="l-navbar" id="nav-bar">
        <nav class="nav">
            <div>
                <a href="#" class="nav_logo">
                    <i class="bx bx-layer nav_logo-icon"></i>
                    <span class="nav_logo-name"><h2>VOTIFY APP</h2></span>
                </a>
                <div class="nav_list">
                    <a href="./home" class="nav_link">
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
            <div class="container mt-5">
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-md-8">
                        <form id="regForm" action="./topics" method="post">
                            <h1 id="register">Poll Form</h1>
                            <div class="all-steps" id="all-steps">
                                <span class="step"><i class="fa fa-user"></i></span>
                                <span class="step"><i class="fa fa-map-marker"></i></span>
                                <span class="step"><i class="fa fa-shopping-bag"></i></span>
                            </div>
                            <div class="tab">
                                <h6>What's the Title of your Topic/question?</h6>
                                <p><input placeholder="Title..." oninput="this.className = ''" name="topicName"></p>
                            </div>
                            <div class="tab">
                                <h6>How many number of choices?</h6>
                                <p><input type="number" name="choicesNum" id="choicesNum" oninput="generateChoices()"></p>
                            </div>
                            <div id="choicesContainer"></div>

                            <div class="tab">
                                <h6>Deadline</h6>
                                <p><input type="date" name="Deadline" id="Deadline"></p>
                            </div>
                            <div class="thanks-message text-center" style="margin-top:5px" id="text-message">
                                <button type="submit" >Submit Form</button>
                            </div>
    
                            <div style="overflow:auto;" id="nextprevious">
                                <div style="float:right;">
                                    <button type="button" id="prevBtn" onclick="nextPrev(-1)"><i class="fa fa-angle-double-left"></i></button>
                                    <button type="button" id="nextBtn" onclick="nextPrev(1)"><i class="fa fa-angle-double-right"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Container Main end-->

    <script>
        var currentTab = 0;
        document.addEventListener("DOMContentLoaded", function (event) {
            showTab(currentTab);
        });

        function showTab(n) {
            var x = document.getElementsByClassName("tab");
            x[n].style.display = "block";
            if (n == 0) {
                document.getElementById("prevBtn").style.display = "none";
            } else {
                document.getElementById("prevBtn").style.display = "inline";
            }
            if (n == (x.length - 1)) {
                document.getElementById("nextBtn").innerHTML = '<i class="fa fa-angle-double-right"></i>';
            } else {
                document.getElementById("nextBtn").innerHTML = '<i class="fa fa-angle-double-right"></i>';
            }
            fixStepIndicator(n)
        }

        function nextPrev(n) {
            var x = document.getElementsByClassName("tab");
            if (n == 1 && !validateForm()) return false;
            x[currentTab].style.display = "none";
            currentTab = currentTab + n;
            if (currentTab >= x.length) {
                document.getElementById("nextprevious").style.display = "none";
                document.getElementById("all-steps").style.display = "none";
                document.getElementById("register").style.display = "none";
                document.getElementById("text-message").style.display = "block";
            }
            showTab(currentTab);
        }

        function validateForm() {
            var x, y, i, valid = true;
            x = document.getElementsByClassName("tab");
            y = x[currentTab].getElementsByTagName("input");
            for (i = 0; i < y.length; i++) {
                if (y[i].value == "") {
                    y[i].className += " invalid";
                    valid = false;
                }
            }
            if (valid) {
                document.getElementsByClassName("step")[currentTab].className += " finish";
            }
            return valid;
        }

        function fixStepIndicator(n) {
            var i, x = document.getElementsByClassName("step");
            for (i = 0; i < x.length; i++) {
                x[i].className = x[i].className.replace(" active", "");
            }
            x[n].className += " active";
        }

        function generateChoices() {
            var choicesNum = document.getElementById("choicesNum").value;
            var choicesContainer = document.getElementById("choicesContainer");

            // Clear existing content
            choicesContainer.innerHTML = "";

            // Generate form inputs based on the entered number
            for (var i = 1; i <= choicesNum; i++) {
                var label = document.createElement("label");
                label.textContent = "Choice " + i;

                var input = document.createElement("input");
                input.type = "text";
                input.name = "choice" + i;

                // Add line break for better spacing
                choicesContainer.appendChild(label);
                choicesContainer.appendChild(input);
                choicesContainer.appendChild(document.createElement("br"));
            }
        }

        const showNavbar = (toggleId, navId, bodyId, headerId) => {
            const toggle = document.getElementById(toggleId),
                nav = document.getElementById(navId),
                bodypd = document.getElementById(bodyId),
                headerpd = document.getElementById(headerId);

            if (toggle && nav && bodypd && headerpd) {
                toggle.addEventListener("click", () => {
                    nav.classList.toggle("show");
                    toggle.classList.toggle("bx-x");
                    bodypd.classList.toggle("body-pd");
                    headerpd.classList.toggle("body-pd");
                });
            }
        };

        showNavbar("header-toggle", "nav-bar", "body-pd", "header");

        const linkColor = document.querySelectorAll(".nav_link");

        function colorLink() {
            if (linkColor) {
                linkColor.forEach((l) => l.classList.remove("active"));
                this.classList.add("active");
            }
        }
        linkColor.forEach((l) => l.addEventListener("click", colorLink));
    </script>
</body>

</html>
