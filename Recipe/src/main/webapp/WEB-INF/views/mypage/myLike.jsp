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
          <li><a href="recipePaging.do?category=&id="><span>Recipe</span></a></li>
          <li class="active"><a href="mylikePaging.do?category="><span>Mypage</span></a>
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
          <!-- My Like 화면 : 무조건 로그인 상태-->
          <div class="toolbar-section" id="account">
	      	<form action="logout.do" method="post">
	      		<p class="text-muted text-sm mt-4"><h4>${member.nickname }<span>님</span><h4></p>
	            <p class="text-muted text-sm mt-4">환영합니다</p>
	            <button class="btn btn-primary" type="submit">Log Out</button>
	         </form>
	      </div>
        </div>
      </div>
    </header>
    

    <!-- Page Title-->
    <div class="page-title">
      <div class="container">
        <h1>My Like</h1>
        <ul class="breadcrumbs">
          <li><a href="recipePaging.do?category=&id=${member.id }">My Recipe</a></li>
          <li class="separator">&nbsp;/&nbsp;</li>
          <li>My Like</li>
          <li class="separator">&nbsp;/&nbsp;</li>
          <li><a href="enterMyinfo.do">My Info</a></li>
        </ul>
      </div>
    </div>
    <!-- Page Content-->
    <div class="container padding-bottom-3x mb-1">
      <div class="row">
        <!-- Content-->
        <div class="col-lg-9 col-md-8 order-md-2">
          <!-- Search-->
          <div class="d-flex flex-wrap-reverse flex-md-nowrap justify-content-center justify-content-sm-between align-items-center mb-30">
            <div class="pt-3 pb-1 pb-sm-3 text-sm text-center text-sm-left"></div>
            <form action="searchMylike.do" class="input-group shop-search-box" method="get"><span class="input-group-btn">
                <button type="submit"><i class="material-icons search"></i></button></span>
              <input class="form-control" type="search" placeholder="레시피명을 입력한 후, 엔터를 누르세요" id="keyword" name="keyword">
            </form>
          </div>
          <!-- Products Grid-->
          <div class="row mb-2">
            <!-- Item-->
            <c:forEach var="item" items="${mylikeList}">
            <div class="col-lg-4 col-sm-6">
              <div class="product-card mb-30">
                <div class="product-card-thumb"> <a class="product-card-link" href="getRecipe.do?recipeno=${item.recipeno }"></a><img src="<c:url value='/rthumbnails.do?recipeno=${item.recipeno }' />" alt="Product">
                  <div class="product-card-buttons">
                    <button class="btn btn-white btn-sm btn-wishlist" data-toggle="tooltip" title="Wishlist"><i class="material-icons favorite_border"></i></button>
                  </div>
                </div>
                <div class="product-card-details">
                  <h3 class="product-card-title"><a href="getRecipe.do?recipeno=${item.recipeno }&category=${paging.category}">${item.subject }</a></h3>
                </div>
              </div>
            </div>
            </c:forEach>
          </div>
          <!-- Pagination-->
          <nav class="pagination">
            <div class="column text-left hidden-xs-down">
	          <a class="btn btn-outline-secondary btn-sm" href="mylikePaging.do?nowPage=1&category=${paging.category}"><i class="material-icons keyboard_arrow_left"></i>&nbsp;처음</a>
	          	<c:if test="${paging.prev == true }">
	              <a class="btn btn-outline-secondary btn-sm" href="mylikePaging.do?nowPage=${paging.prevPage }&category=${paging.category}"><i class="material-icons keyboard_arrow_left"></i>&nbsp;이전</a>
	            </c:if>  
	          </div>
	          <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i">
	            <div class="column">
	              <ul class="pages">
	                <li><a href="mylikePaging.do?nowPage=${i }&category=${paging.category}">${i }</a></li>
	              </ul>
	            </div>
	          </c:forEach>
	          <div class="column text-right hidden-xs-down">
	            <c:if test="${paging.next == true }">
	              <a class="btn btn-outline-secondary btn-sm" href="mylikePaging.do?nowPage=${paging.nextPage }&category=${paging.category}">다음&nbsp;<i class="material-icons keyboard_arrow_right"></i></a>
	            </c:if>
	            <a class="btn btn-outline-secondary btn-sm" href="mylikePaging.do?nowPage=${paging.lastPage }&category=${paging.category}">마지막&nbsp;<i class="material-icons keyboard_arrow_right"></i></a>
	          </div>
          </nav>
        </div>
        <!-- Sidebar -->
        <div class="col-lg-3 col-md-4 order-md-1">
          <div class="sidebar-toggle position-left"><i class="material-icons filter_list"></i></div>
          <aside class="sidebar sidebar-offcanvas position-left"><span class="sidebar-close"><i class="material-icons icon_close"></i></span>
            <!-- Widget Categories-->
            <section class="widget widget-categories pt-0">
              <h3 class="widget-title">카테고리</h3>
              <ul>
              	<li><a href="mylikePaging.do?category=">전체</a></li>
                <li><a href="mylikePaging.do?category=korean">한식</a></li>
                <li><a href="mylikePaging.do?category=western">양식</a></li>
                <li><a href="mylikePaging.do?category=japanese">일식</a></li>
                <li><a href="mylikePaging.do?category=chinese">중식</a></li>
                <li><a href="mylikePaging.do?category=bread">제과제빵</a></li>
                <li><a href="mylikePaging.do?category=drink">음료</a></li>
              </ul>
            </section>
            <!-- Widget Sorting-->
            <section class="widget widget-icon-list">
              <h3 class="widget-title">정렬</h3>
              <ul>
                <li><a href="#"><i class="material-icons sort"></i>최신순</a></li>
                <li><a href="#"><i class="material-icons vertical_align_top"></i>오래된순</a></li>
              </ul>
            </section>
          </aside>
        </div>
      </div>
    </div>
    <!-- Site Footer-->
    <footer class="site-footer">
      <div class="column text-center">
        <p class="text-sm mb-4">Need Support? Call<span class="text-primary">&nbsp;001 (917) 555-4836</span></p><a class="social-button sb-skype" href="#" data-toggle="tooltip" data-placement="top" title="Skype"><i class="socicon-skype"></i></a><a class="social-button sb-facebook" href="#" data-toggle="tooltip" data-placement="top" title="Facebook"><i class="socicon-facebook"></i></a><a class="social-button sb-google-plus" href="#" data-toggle="tooltip" data-placement="top" title="Google +"><i class="socicon-googleplus"></i></a><a class="social-button sb-twitter" href="#" data-toggle="tooltip" data-placement="top" title="Twitter"><i class="socicon-twitter"></i></a><a class="social-button sb-instagram" href="#" data-toggle="tooltip" data-placement="top" title="Instagram"><i class="socicon-instagram"></i></a>
        <p class="text-xxs text-muted mb-0 mt-3">© All rights reserved. Made with <i class='material-icons favorite text-danger'></i> by rokaux</p>
      </div>
    </footer>
    <!-- Back To Top Button--><a class="scroll-to-top-btn" href="#"><i class="material-icons trending_flat"></i></a>
    <!-- Backdrop-->
    <div class="site-backdrop"></div>
    <!-- JavaScript (jQuery) libraries, plugins and custom scripts-->
    <script src="js/vendor.min.js"></script>
    <script src="js/scripts.min.js"></script>
  </body>
</html>