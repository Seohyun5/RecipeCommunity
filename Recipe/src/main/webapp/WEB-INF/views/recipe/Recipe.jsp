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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript">
    
	$(document).ready(function () {
		
		$("#id").blur(function () {
			 var id = $("#id").val();
			console.log(id);
			if(id != ""){
				checkId(id);
			}else{
				$("#idCheck_result").html("");
			}
		});
	
		 function checkId(id) {
				$.ajax({
					type : 'POST',
					url : '${pageContext.request.contextPath}/idCheck.do',
					data : {"id" : id}
				}).done(function (data) {
					console.log(data);
					idResult(data);
				}).fail(function (request,status,error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				})
		}
		 
		function idResult(data) {
			if(data==0){
				$("#idCheck_result").html("??????????????? ??????????????????.").css("color","green");
			}else{
				$("#idCheck_result").html("?????? ???????????? ??????????????????.").css("color","red");
			}
		}
		$("#pw").blur(function () {
		 	pwCheck();
		});
		$("#pw2").blur(function () {
			pwCheck();
		});
		function pwCheck() {
			var pw1 = $("#pw").val();
  			var pw2 = $("#pw2").val();
			console.log(pw1);
				if(pw2 == "" || pw1 == ""){
					$("#pwCheck_result").html("??????????????? ??????????????????").css("color","red");
				}else if(pw1 == pw2){
					$("#pwCheck_result").html("??????????????? ???????????????").css("color","green");
				}else{
					$("#pwCheck_result").html("??????????????? ???????????? ????????????.").css("color","red");
				}
		}
		
		$("#nickname").blur(function () {
			var nickname = $("#nickname").val();
			console.log(nickname);
			if(nickname != ""){
				checkNickname(nickname);
			}else{
				$("#nicknameCheck_result").html("");
			}
		});
	
		 function checkNickname(nickname) {
				$.ajax({
					type : 'POST',
					url : '${pageContext.request.contextPath}/nicknameCheck.do',
					data : {"nickname" : nickname}
				}).done(function (data) {
					console.log(data);
					nicknameResult(data);
				}).fail(function (request,status,error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				})
		}
		 
		function nicknameResult(data) {
			if(data==0){
				$("#nicknameCheck_result").html("??????????????? ??????????????????.").css("color","green");
			}else{
				$("#nicknameCheck_result").html("?????? ???????????? ??????????????????.").css("color","red");
			}
		}
	});
	    
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
          
          <!-- ????????? ?????? -->
          <c:if test="${!empty member}">
	      <div class="toolbar-section" id="account">
	      	<form action="logout.do" method="post">
	        	<p class="text-muted text-sm mt-4"><h4>${member.nickname }<span>???</span><h4></p>
	            <p class="text-muted text-sm mt-4">???????????????</p>
	            <button class="btn btn-primary" type="submit">Log Out</button>
	        </form>
	      </div>
          </c:if>
          <!-- ???????????? ?????? -->
          <c:if test="${empty member}">
          <div class="toolbar-section" id="account">
            <ul class="nav nav-tabs nav-justified" role="tablist">
              <li class="nav-item"><a class="nav-link active" href="#login" data-toggle="tab" role="tab">?????????</a></li>
              <li class="nav-item"><a class="nav-link" href="#signup" data-toggle="tab" role="tab">????????????</a></li>
            </ul>
            <div class="tab-content">
              <div class="tab-pane fade show active" id="login" role="tabpanel">
              	<!-- ????????? -->
                <form action="login.do" method="post" autocomplete="off" id="login-form">
                  <div class="form-group input-group">
                    <input class="form-control" type="text" placeholder="ID" name="id" required>
                    <span class="input-group-addon"><i class="material-icons mail"></i></span>
                  </div>
                  <div class="form-group input-group">
                    <input class="form-control" type="password" placeholder="Password" name="password" required>
                    <span class="input-group-addon"><i class="material-icons lock"></i></span>
                  </div>
                  <div class="custom-control custom-checkbox form-group">
                    <input class="custom-control-input" type="checkbox" id="logged" checked>
                    <label class="custom-control-label" for="logged">????????? ??????</label>
                  </div>
                  <button class="btn btn-primary btn-block" type="submit">?????????</button>
                </form>
              </div>
              <div class="tab-pane fade" id="signup" role="tabpanel">
                <form method="post" autocomplete="off" id="signup-form" action="insertMember.do">
                  <div class="form-group">
                    <input class="form-control" type="text" placeholder="?????????" id="id" name="id" required>
                    <div id="idCheck_result"></div>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="password" placeholder="????????????" id="pw" required>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="password" placeholder="???????????? ??????" id="pw2" name="password" required>
                    <div id="pwCheck_result"></div>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="text" placeholder="??????" name="name" required>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="text" placeholder="?????????" id="nickname" name="nickname" required>
                    <div id="nicknameCheck_result"></div>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="email" placeholder="?????????" name="email" required>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="text" placeholder="????????????" name="phone" required>
                  </div>
                  <button class="btn btn-primary btn-block" type="submit">????????????</button>
                  <p class="text-muted text-sm mt-4">OR sign up with your social account</p><a class="media-btn media-facebook" href="#"><i class="socicon-facebook"></i><span>Signup with Facebook</span></a><a class="media-btn media-google" href="#"><i class="socicon-googleplus"></i><span>Signup with Google+</span></a><a class="media-btn media-twitter" href="#"><i class="socicon-twitter"></i><span>Signup with Twitter</span></a>
                </form>
              </div>
            </div>
          </div>
          </c:if>
        </div>
      </div>
    </header>
    
    <!-- Page Title-->
    <div class="page-title">
      <div class="container">
        <h1>RECIPE</h1>
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
            <form action="searchRecipe.do?id=" class="input-group shop-search-box" method="get"><span class="input-group-btn">
                <button type="submit"><i class="material-icons search"></i></button></span>
              <input class="form-control" type="search" placeholder="??????????????? ????????? ???, ????????? ????????????" id="keyword" name="keyword">
            </form>
          </div>
          <!-- Products Grid-->
          <div class="row mb-2">
            <!-- Item-->
            <c:forEach var="item" items="${recipeList}">
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
          
          <c:if test="${empty paging.keyword }">
	          <div class="column text-left hidden-xs-down">
	          <a class="btn btn-outline-secondary btn-sm" href="recipePaging.do?nowPage=1&category=${paging.category}&id="><i class="material-icons keyboard_arrow_left"></i>&nbsp;??????</a>
	          	<c:if test="${paging.prev == true }">
	              <a class="btn btn-outline-secondary btn-sm" href="recipePaging.do?nowPage=${paging.prevPage }&category=${paging.category}&id="><i class="material-icons keyboard_arrow_left"></i>&nbsp;??????</a>
	            </c:if>  
	          </div>
	          <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i">
	            <div class="column">
	              <ul class="pages">
	                <li><a href="recipePaging.do?nowPage=${i }&category=${paging.category}&id=">${i }</a></li>
	              </ul>
	            </div>
	          </c:forEach>
	          <div class="column text-right hidden-xs-down">
	            <c:if test="${paging.next == true }">
	              <a class="btn btn-outline-secondary btn-sm" href="recipePaging.do?nowPage=${paging.nextPage }&category=${paging.category}&id=">??????&nbsp;<i class="material-icons keyboard_arrow_right"></i></a>
	            </c:if>
	            <a class="btn btn-outline-secondary btn-sm" href="recipePaging.do?nowPage=${paging.lastPage }&category=${paging.category}&id=">?????????&nbsp;<i class="material-icons keyboard_arrow_right"></i></a>
	            <c:if test="${!empty member}">
	              <a class="btn btn-outline-secondary btn-sm" href="writeRecipe.do">?????????&nbsp;</a>
	            </c:if>
	          </div>
          </c:if>
          
          <c:if test="${!empty paging.keyword }">
	          <div class="column text-left hidden-xs-down">
	          	<c:if test="${paging.prev == true }">
	              <a class="btn btn-outline-secondary btn-sm" href="searchRecipe.do?nowPage=${paging.prevPage }&keyword=${paging.keyword}"><i class="material-icons keyboard_arrow_left"></i>&nbsp;???????????????</a>
	            </c:if>  
	          </div>
	          <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i">
	            <div class="column">
	              <ul class="pages">
	                <li><a href="searchRecipe.do?nowPage=${i }&keyword=${paging.keyword}">${i }</a></li>
	              </ul>
	            </div>
	          </c:forEach>
	          <div class="column text-right hidden-xs-down">
	            <c:if test="${paging.next == true }">
	              <a class="btn btn-outline-secondary btn-sm" href="searchRecipe.do?nowPage=${paging.nextPage }&keyword=${paging.keyword}">???????????????&nbsp;<i class="material-icons keyboard_arrow_right"></i></a>
	            </c:if>
	            <c:if test="${!empty member}">
	              <a class="btn btn-outline-secondary btn-sm" href="writeRecipe.do">?????????&nbsp;</a>
	            </c:if>
	          </div>
          </c:if>
          
          </nav>
        </div>
        
        <!-- Sidebar -->
        <div class="col-lg-3 col-md-4 order-md-1">
          <div class="sidebar-toggle position-left"><i class="material-icons filter_list"></i></div>
          <aside class="sidebar sidebar-offcanvas position-left"><span class="sidebar-close"><i class="material-icons icon_close"></i></span>
            <!-- Widget Categories-->
            <section class="widget widget-categories pt-0">
              <h3 class="widget-title">????????????</h3>
              <ul>
              	<li><a href="recipePaging.do?category=&id=">??????</a></li>
                <li><a href="recipePaging.do?category=korean&id=">??????</a></li>
                <li><a href="recipePaging.do?category=western&id=">??????</a></li>
                <li><a href="recipePaging.do?category=japanese&id=">??????</a></li>
                <li><a href="recipePaging.do?category=chinese&id=">??????</a></li>
                <li><a href="recipePaging.do?category=bread&id=">????????????</a></li>
                <li><a href="recipePaging.do?category=drink&id=">??????</a></li>
              </ul>
            </section>
            <!-- Widget Sorting-->
            <section class="widget widget-icon-list">
              <h3 class="widget-title">??????</h3>
              <ul>
                <li><a href="recipePaging.do?category=${paging.category}&id="><i class="material-icons sort"></i>?????????</a></li>
                <li><a href="#"><i class="material-icons vertical_align_top"></i>????????????</a></li>
              </ul>
            </section>
          </aside>
        </div>
      </div>
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