<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="no-js" lang="">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>Votify | Remastering polls</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png" />
    <link rel="stylesheet" href="resources/css/bootstrap.min.css" />
    <link rel="stylesheet" href="resources/css/fontawesome-all.min.css" />
    <link rel="stylesheet" href="resources/font/flaticon.css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="style.css" />
</head>
<body>
    <section class="fxt-template-animation fxt-template-layout11">
        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col-xl-6 col-lg-7 col-sm-12 col-12 fxt-bg-color">
                    <div class="fxt-content">
                        <div class="fxt-header">
                            <p>Login into your Votify account</p>
                        </div>
                        <div class="fxt-form">
                            <form action="./login" method="post">
                                <div class="form-group">
                                    <div class="fxt-transformY-50 fxt-transition-delay-1">
                                        <input type="email" id="userEmail" class="form-control" name="userEmail" placeholder="Email" required="required" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="fxt-transformY-50 fxt-transition-delay-2">
                                        <input id="password" type="password" class="form-control" name="password" placeholder="********" required="required" />
                                        <i toggle="#password" class="fa fa-fw fa-eye toggle-password field-icon"></i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="fxt-transformY-50 fxt-transition-delay-3">
                                        <div class="fxt-checkbox-area">
                                            <div class="checkbox">
                                                <input id="checkbox1" type="checkbox" />
                                                <label for="checkbox1">Keep me logged in</label>
                                            </div>
                                            <a href="#" class="switcher-text">Forgot Password</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="fxt-transformY-50 fxt-transition-delay-4">
                                        <button type="submit" class="fxt-btn-fill">Log in</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="fxt-style-line">
                            <div class="fxt-transformY-50 fxt-transition-delay-5"></div>
                        </div>
                        <div class="fxt-footer">
                            <div class="fxt-transformY-50 fxt-transition-delay-9">
                                <p>Don't have an account?<a href="./user" class="switcher-text2 inline-text">Register</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script src="resources/js/jquery-3.5.0.min.js"></script>
    <!-- Popper js -->
    <script src="resources/js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="resources/js/bootstrap.min.js"></script>
    <!-- Imagesloaded js -->
    <script src="resources/js/imagesloaded.pkgd.min.js"></script>
    <!-- Validator js -->
    <script src="resources/js/validator.min.js"></script>
    <!-- Custom Js -->
    <script src="resources/js/main.js"></script>
</body>
</html>
