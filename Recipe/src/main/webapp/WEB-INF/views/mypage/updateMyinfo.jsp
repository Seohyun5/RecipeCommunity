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
		
		$("#password").blur(function () {
		 	pwCheck();
		});
		$("#password1").blur(function () {
			pwCheck();
		});
		
		function pwCheck() {
			var pw1 = $("#password").val();
  			var pw2 = $("#password1").val();
			console.log(pw1);
				if(pw1 == "" || pw2 == ""){
					$("#pwCheck_result").html("비밀번호는 4자 이상 입력해주세요").css("color","red");
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
          <li><a href="main.do"><span>Home</span></a></li>
          <li><a href="recipePaging.do?category="><span>Recipe</span></a></li>
          <li class="active"><a href="getMylikeList.do"><span>Mypage</span></a>
            <ul class="sub-menu">
              <li><a href="getMyrecipeList.do">My Recipe</a></li>
              <li><a href="getMylikeList.do">My Like</a></li>
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
          <!-- 글 수정 화면 : 무조건 로그인 상태-->
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
        <h1>My Info</h1>
        <ul class="breadcrumbs">
          <li><a href="getMyrecipeList.do">My Recipe</a></li>
          <li class="separator">&nbsp;/&nbsp;</li>
          <li><a href="getMylikeList.do">My Like</a></li>
          <li class="separator">&nbsp;/&nbsp;</li>
          <li>My Info</li>
        </ul>
      </div>
    </div>
    <!-- Page Content-->
    <div class="container padding-bottom-3x mb-2">
      <div class="row">
        <div class="col-lg-4">
        </div>
        <div class="col-lg-8">
          <div class="padding-top-2x mt-2 hidden-lg-up"></div>
          <form action="updateMember.do" method="post" autocomplete="off" id="updateMember" class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label for="account-fn">닉네임</label>
                <input class="form-control" type="text" id="nickname" value=${member.nickname } name="nickname" required>
                <div id="nicknameCheck_result"></div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label for="account-email">E-mail</label>
                <input class="form-control" type="email" id="email" value=${member.email } name="email" required>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label for="account-phone">전화번호</label>
                <input class="form-control" type="text" id="phone" value=${member.phone } name="phone" required>
              </div>
            </div>
            <div class="col-12">
              <div class="d-flex flex-wrap justify-content-between align-items-center">
                <button class="btn btn-primary margin-right-none" type="submit" data-toast data-toast-position="topRight" data-toast-type="success" data-toast-icon="icon-circle-check" data-toast-title="Success!" data-toast-message="Your profile updated successfuly.">개인정보 수정</button>
              </div>
              <hr class="mt-2 mb-3">
            </div>
          </form> 
          <form id="updatePw" class="row" action="updatePw.do" method="post">
            <div class="col-md-6">
              <div class="form-group">
                <label for="account-pass">새 비밀번호</label>
                <input class="form-control" type="password" id="password" name="password" required>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label for="account-confirm-pass">새 비밀번호 확인</label>
                <input class="form-control" type="password" id="password1" name="password1" required>
                <div id="pwCheck_result"></div>
              </div>
            </div>
            <div class="col-12">
              <div class="d-flex flex-wrap justify-content-between align-items-center">
                <button class="btn btn-primary margin-right-none" type="submit">비밀번호 수정</button>
              </div>
              <hr class="mt-2 mb-3">
            </div>
          </form>
       	  <form id="deleteMember" class="row" action="deleteMember.do" method="post">
            <div class="col-md-6">
              <div class="form-group">
                <label for="account-pass">비밀번호 확인</label>
                <input class="form-control" type="password" id="password" name="password" required>
              </div>
            </div>
            <div class="col-12">
              <b>탈퇴 시, 작성한 글은 지워지지 않습니다.<br>또한, 해당 아이디로 재가입이 불가능합니다.</b>
              <div class="d-flex flex-wrap justify-content-between align-items-center">
                <button class="btn btn-primary margin-right-none" type="submit">회원탈퇴</button>
              </div>
              <hr class="mt-2 mb-3">
            </div>
          </form>
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