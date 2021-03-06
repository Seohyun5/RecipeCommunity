<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Recipe Community</title>
    <!-- SEO Meta Tags-->
    <meta name="description" content="Unishop - Universal E-Commerce Template">
    <meta name="keywords" content="shop, e-commerce, modern, flat style, responsive, online store, business, mobile, blog, bootstrap 4, html5, css3, jquery, js, gallery, slider, touch, creative, clean">
    <meta name="author" content="Rokaux">
    <!-- Mobile Specific Meta Tag-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- Favicon and Apple Icons-->
    <link rel="icon" type="image/x-icon" href="resources/favicon.ico">
    <link rel="icon" type="image/png" href="resources/favicon.png">
    <link rel="apple-touch-icon" href="resources/touch-icon-iphone.png">
    <link rel="apple-touch-icon" sizes="152x152" href="resources/touch-icon-ipad.png">
    <link rel="apple-touch-icon" sizes="180x180" href="resources/touch-icon-iphone-retina.png">
    <link rel="apple-touch-icon" sizes="167x167" href="resources/touch-icon-ipad-retina.png">
    <!-- Vendor Styles including: Bootstrap, Font Icons, Plugins, etc.-->
    <link rel="stylesheet" media="screen" href="resources/css/vendor.min.css">
    <!-- Main Template Styles-->
    <link id="mainStyles" rel="stylesheet" media="screen" href="resources/css/styles.min.css">
    <!-- Modernizr-->
    <script src="resources/js/modernizr.min.js"></script>
    <script type="text/javascript">
	    var cnt=1;
	    function fn_addFile(){
	    	$("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
	    	cnt++;
	    }
	   
    </script>
  </head>
  <!-- Body-->
  <body>
    <!-- Navbar-->
    <!-- Remove "navbar-sticky" class to make navigation bar scrollable with the page.-->
    <header class="navbar navbar-sticky">
      <!-- Site Branding-->
      <div class="site-branding"><a class="site-logo hidden-xs-down" href="index.html"><img src="resources/img/logo/logo.png" alt="Unishop"></a><a class="site-logo logo-sm hidden-sm-up" href="index.html"><img src="resources/img/logo/logo-sm.png" alt="Unishop"></a>
      </div>
      <!-- Main Navigation-->
      <nav class="site-menu">
        <ul>
          <li><a href="main.do"><span>Home</span></a></li>
          <li class="active"><a href="recipePaging.do?category=&id="><span>Recipe</span></a></li>
          <li><a href="mylikePaging.do?category="><span>Mypage</span></a>
            <ul class="sub-menu">
              <li><a href="recipePaging.do?category=&id=${member.id }">My Recipe</a></li>
              <li><a href="mylikePaging.do?category=">My Like</a></li>
              <li><a href="enterMyinfo.do">My Info</a></li>
            </ul>
          </li>
        </ul>
      </nav>
      <!-- Toolbar-->
      <div class="toolbar">
        <div class="inner"><a class="toolbar-toggle mobile-menu-toggle" href="#mobileMenu"><i class="material-icons menu"></i></a><a class="toolbar-toggle" href="#account"><i class="material-icons person"></i></a></div>
        <!-- Toolbar Dropdown-->
        <div class="toolbar-dropdown">
          <!-- Account Section-->
          <!-- ??? ?????? ?????? : ????????? ????????? ??????-->
          <div class="toolbar-section" id="account">
	      	<form action="logout.do" method="post">
	      		<p class="text-muted text-sm mt-4"><h4>${member.nickname }<span>???</span><h4></p>
	            <p class="text-muted text-sm mt-4">???????????????</p>
	            <button class="btn btn-primary" type="submit">Log Out</button>
	         </form>
	      </div>
        </div>
      </div>
    </header>
    
    <!-- Page Title-->
    <div class="page-title">
      <div class="container">
        <h1>RECIPE WRITE</h1>
      </div>
    </div>
    <!-- Page Content-->
    <div class="container padding-bottom-3x mb-2">
    <!-- Comment Form--> 
      <form action="insertRecipe.do" class="row" method="post" enctype="multipart/form-data">
      	<div class="col-sm-6">
          <div class="form-group">
            <label for="select-input">???????????? </label>
            <select name="category" class="form-control" id="select-input" required>
              <option>??????????????? ?????????????????? </option>
              <option value="??????">??????</option>
              <option value="??????">??????</option>
              <option value="??????">??????</option>
              <option value="??????">??????</option>
              <option value="????????????">????????????</option>
              <option value="??????">??????</option>
            </select>
          </div>
        </div>
        <div class="col-sm-12">
          <div class="form-group">
            <label for="comment-name">?????????</label>
            <input class="form-control" type="text" id="recipetitle" placeholder="???????????? ???????????????" name="subject" required>
          </div>
        </div>
        <div class="col-12">
          <div class="form-group">
            <label for="comment-text">??????</label>
            <textarea class="form-control" rows="17" id="recipecontent" placeholder="????????? ???????????????" name="content" required></textarea>
          </div>
        </div>
        <div class="col-sm-6">
          <div class="form-group">
            <label for="checkout-ln">????????????</label>
              <div class="custom-file" id="d_file">
                <input name = "main_image" class="custom-file-input" type="button" onClick="fn_addFile()" required>
                <label class="custom-file-label" for="d_file"></label>
              </div>
          </div>
        </div>
        <div class="col-12 text-right">
          <button class="btn btn-primary" type="submit">??????</button>
        </div>
      </form>
    </div>
    <!-- Site Footer-->
    <footer class="site-footer">
      <div class="column text-center">
        <p class="text-sm mb-4">?????????<span class="text-primary">&nbsp; RECIPECOMMUNITY@rpj.com</span></p>
        <p class="text-xxs text-muted mb-0 mt-3">by SEOHYUN OH</p>
      </div>
    </footer>
    <!-- Back To Top Button--><a class="scroll-to-top-btn" href="#"><i class="material-icons trending_flat"></i></a>
    <!-- Backdrop-->
    <div class="site-backdrop"></div>
    <!-- JavaScript (jQuery) libraries, plugins and custom scripts-->
    <script src="resources/js/vendor.min.js"></script>
    <script src="resources/js/scripts.min.js"></script>
  </body>
</html>