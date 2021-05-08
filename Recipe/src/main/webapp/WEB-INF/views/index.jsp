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
				$("#idCheck_result").html("사용가능한 아이디입니다.").css("color","green");
			}else{
				$("#idCheck_result").html("이미 사용중인 아이디입니다.").css("color","red");
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
					$("#pwCheck_result").html("비밀번호를 입력해주세요").css("color","red");
				}else if(pw1 == pw2){
					$("#pwCheck_result").html("비밀번호가 일치합니다").css("color","green");
				}else{
					$("#pwCheck_result").html("비밀번호가 일치하지 않습니다.").css("color","red");
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
				$("#nicknameCheck_result").html("사용가능한 닉네임입니다.").css("color","green");
			}else{
				$("#nicknameCheck_result").html("이미 사용중인 닉네임입니다.").css("color","red");
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
          <li class="active"><a href="main.do"><span>Home</span></a></li>
          <li><a href="recipePaging.do?category="><span>Recipe</span></a></li>
          <li><a href="getMylikeList.do"><span>Mypage</span></a>
            <ul class="sub-menu">
              <li><a href="account-orders.html">My Recipe</a></li>
              <li><a href="account-wishlist.html">Like</a></li>
              <li><a href="account-profile.html">My Info</a></li>
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
       	
          <!-- 로그인 상태 -->
          <c:if test="${!empty member}">
	      <div class="toolbar-section" id="account">
	      	<form action="logout.do" method="post">
	        	<p class="text-muted text-sm mt-4"><h4>${member.nickname }<span>님</span><h4></p>
	            <p class="text-muted text-sm mt-4">환영합니다</p>
	            <button class="btn btn-primary" type="submit">Log Out</button>
	        </form>
	      </div>
          </c:if>
          <!-- 로그아웃 상태 -->
          <c:if test="${empty member}">
          <div class="toolbar-section" id="account">
            <ul class="nav nav-tabs nav-justified" role="tablist">
              <li class="nav-item"><a class="nav-link active" href="#login" data-toggle="tab" role="tab">로그인</a></li>
              <li class="nav-item"><a class="nav-link" href="#signup" data-toggle="tab" role="tab">회원가입</a></li>
            </ul>
            <div class="tab-content">
              <div class="tab-pane fade show active" id="login" role="tabpanel">
              	<!-- 로그인 -->
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
                    <label class="custom-control-label" for="logged">로그인 유지</label>
                  </div>
                  <button class="btn btn-primary btn-block" type="submit">로그인</button>
                </form>
              </div>
              <div class="tab-pane fade" id="signup" role="tabpanel">
                <form method="post" autocomplete="off" id="signup-form" action="insertMember.do">
                  <div class="form-group">
                    <input class="form-control" type="text" placeholder="아이디" id="id" name="id" required>
                    <div id="idCheck_result"></div>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="password" placeholder="비밀번호" id="pw" required>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="password" placeholder="비밀번호 확인" id="pw2" name="password" required>
                    <div id="pwCheck_result"></div>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="text" placeholder="이름" name="name" required>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="text" placeholder="닉네임" id="nickname" name="nickname" required>
                    <div id="nicknameCheck_result"></div>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="email" placeholder="이메일" name="email" required>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="text" placeholder="전화번호" name="phone" required>
                  </div>
                  <button class="btn btn-primary btn-block" type="submit">회원가입</button>
                  <p class="text-muted text-sm mt-4">OR sign up with your social account</p><a class="media-btn media-facebook" href="#"><i class="socicon-facebook"></i><span>Signup with Facebook</span></a><a class="media-btn media-google" href="#"><i class="socicon-googleplus"></i><span>Signup with Google+</span></a><a class="media-btn media-twitter" href="#"><i class="socicon-twitter"></i><span>Signup with Twitter</span></a>
                </form>
              </div>
            </div>
          </div>
          </c:if>
        </div>
      </div>
    </header>
    
    <!-- Page Content-->
    <!-- Hero Slider-->
    <section class="hero-slider">
      <div class="owl-carousel large-controls dots-inside pb-4" data-owl-carousel="{ &quot;nav&quot;: true, &quot;dots&quot;: true, &quot;loop&quot;: true, &quot;autoplay&quot;: true, &quot;autoplayTimeout&quot;: 8000 }">
        <div class="container-fluid">
          <div class="row align-items-center">
            <div class="col-md-6">
              <div class="pr-3 pt-5 pb-0 py-md-5"><img class="d-block" src="img/hero-slider/03.png" alt="Product"></div>
            </div>
            <div class="col-xl-4 col-md-6">
              <div class="padding-top-3x padding-bottom-3x px-3 px-lg-5 text-center text-md-left from-bottom">
                <h2>Featured Collection Of Stylish Indoor Lighting Fixtures</h2>
                <p class="text-sm text-muted">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.</p><a class="btn btn-primary mx-0 scale-up delay-1" href="shop-boxed-ls.html">View Collection</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Top Categories-->
    <section class="container padding-top-3x padding-bottom-3x">
      <h3 class="text-center mb-30">Recent Recipes</h3>
      <div class="row">
        <div class="col-md-3 col-sm-6 mb-30"><a class="category-card flex-wrap text-center pt-0" href="shop-boxed-ls.html">
            <div class="category-card-thumb w-100"><img src="img/shop/categories/02.jpg" alt="Category"></div>
            <div class="category-card-info w-100">
              <h3 class="category-card-title">Lighting</h3>
              <h4 class="category-card-subtitle">Starting from $95.99</h4>
            </div></a></div>
      </div>
      <div class="text-center"><a class="btn btn-outline-secondary mb-0" href="shop-categories.html">All Recipes</a></div>
    </section>
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
    <script src="resources/js/vendor.min.js"></script>
    <script src="resources/js/scripts.min.js"></script>
  </body>
</html>