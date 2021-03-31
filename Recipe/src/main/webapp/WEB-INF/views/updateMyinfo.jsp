<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>My Info</title>
    <!-- SEO Meta Tags-->
    <meta name="description" content="Unishop - Universal E-Commerce Template">
    <meta name="keywords" content="shop, e-commerce, modern, flat style, responsive, online store, business, mobile, blog, bootstrap 4, html5, css3, jquery, js, gallery, slider, touch, creative, clean">
    <meta name="author" content="Rokaux">
    <!-- Mobile Specific Meta Tag-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- Favicon and Apple Icons-->
    <link rel="icon" type="image/x-icon" href="favicon.ico">
    <link rel="icon" type="image/png" href="favicon.png">
    <link rel="apple-touch-icon" href="touch-icon-iphone.png">
    <link rel="apple-touch-icon" sizes="152x152" href="touch-icon-ipad.png">
    <link rel="apple-touch-icon" sizes="180x180" href="touch-icon-iphone-retina.png">
    <link rel="apple-touch-icon" sizes="167x167" href="touch-icon-ipad-retina.png">
    <!-- Vendor Styles including: Bootstrap, Font Icons, Plugins, etc.-->
    <link rel="stylesheet" media="screen" href="css/vendor.min.css">
    <!-- Main Template Styles-->
    <link id="mainStyles" rel="stylesheet" media="screen" href="css/styles.min.css">
    <!-- Modernizr-->
    <script src="js/modernizr.min.js"></script>
  </head>
  <!-- Body-->
  <body>
    <!-- Navbar-->
    <!-- Remove "navbar-sticky" class to make navigation bar scrollable with the page.-->
    <header class="navbar navbar-sticky">
      <!-- Site Branding-->
      <div class="site-branding"><a class="site-logo hidden-xs-down" href="index.html"><img src="img/logo/logo.png" alt="Unishop"></a><a class="site-logo logo-sm hidden-sm-up" href="index.html"><img src="img/logo/logo-sm.png" alt="Unishop"></a>
      </div>
      <!-- Main Navigation-->
      <nav class="site-menu">
        <ul>
          <li><a href="index.html"><span>Home</span></a></li>
          <li><a href="shop-boxed-ls.html"><span>Recipe</span></a></li>
          <li><a href="blog.html"><span>Blog</span></a></li>
          <li class="active"><a href="account-orders.html"><span>MYPAGE</span></a>
            <ul class="sub-menu">
                <li><a href="account-orders.html">My Recipe</a></li>
                <li><a href="account-wishlist.html">My Blog</a></li>
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
          <div class="toolbar-section" id="account">
            <ul class="nav nav-tabs nav-justified" role="tablist">
              <li class="nav-item"><a class="nav-link active" href="#login" data-toggle="tab" role="tab">Log In</a></li>
              <li class="nav-item"><a class="nav-link" href="#signup" data-toggle="tab" role="tab">Sign Up</a></li>
            </ul>
            <div class="tab-content">
              <div class="tab-pane fade show active" id="login" role="tabpanel">
                <form autocomplete="off" id="login-form">
                  <div class="form-group input-group">
                    <input class="form-control" type="email" placeholder="Email" required><span class="input-group-addon"><i class="material-icons mail"></i></span>
                  </div>
                  <div class="form-group input-group">
                    <input class="form-control" type="password" placeholder="Password" required><span class="input-group-addon"><i class="material-icons lock"></i></span>
                  </div>
                  <div class="custom-control custom-checkbox form-group">
                    <input class="custom-control-input" type="checkbox" id="logged" checked>
                    <label class="custom-control-label" for="logged">Keep me logged in</label>
                  </div>
                  <button class="btn btn-primary btn-block" type="submit">Log In</button>
                </form>
              </div>
              <div class="tab-pane fade" id="signup" role="tabpanel">
                <form autocomplete="off" id="signup-form">
                  <div class="form-group">
                    <input class="form-control" type="text" placeholder="Full Name" required>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="email" placeholder="Email" required>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="password" placeholder="Password" required>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="password" placeholder="Confirm Password" required>
                  </div>
                  <button class="btn btn-primary btn-block" type="submit">Sign Up</button>
                  <p class="text-muted text-sm mt-4">OR sign up with your social account</p><a class="media-btn media-facebook" href="#"><i class="socicon-facebook"></i><span>Signup with Facebook</span></a><a class="media-btn media-google" href="#"><i class="socicon-googleplus"></i><span>Signup with Google+</span></a><a class="media-btn media-twitter" href="#"><i class="socicon-twitter"></i><span>Signup with Twitter</span></a>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
    <!-- Page Title-->
    <div class="page-title">
      <div class="container">
        <h1>My Info</h1>
        <ul class="breadcrumbs">
          <li><a href="index.html">My Recipe</a>
          </li>
          <li class="separator">&nbsp;/&nbsp;</li>
          <li><a href="account-orders.html">My Blog</a>
          </li>
          <li class="separator">&nbsp;/&nbsp;</li>
          <li><a href="account-orders.html">My Like</a>
          </li>
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
          <form class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label for="account-fn">닉네임</label>
                <input class="form-control" type="text" id="nickname" value=${member.nickname } name="nickname" required>
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
                <button class="btn btn-primary margin-right-none" type="button" data-toast data-toast-position="topRight" data-toast-type="success" data-toast-icon="icon-circle-check" data-toast-title="Success!" data-toast-message="Your profile updated successfuly.">개인정보 수정</button>
              </div>
              <hr class="mt-2 mb-3">
            </div>
          </form>
          <form name="updatePw" class="row" action="updatePw.do" method="post">
            <div class="col-md-6">
              <div class="form-group">
                <label for="account-pass">새 비밀번호</label>
                <input class="form-control" type="password" id="password" name="password">
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <label for="account-confirm-pass">새 비밀번호 확인</label>
                <input class="form-control" type="password" id="password1" name="password1">
              </div>
            </div>
            <div class="col-12">
              <div class="d-flex flex-wrap justify-content-between align-items-center">
                <button class="btn btn-primary margin-right-none" type="button" onclick="pwCheck()">비밀번호 수정</button>
                <script type="text/javascript">
                	function pwCheck(){
                		var pw = document.getElementById("password").value;
                		var pw2 = document.getElementById("password1").value;
                		if(pw != pw2){
                			alert("비밀번호가 다릅니다.");
                			pw.focus();
                			return false;
                		}else if(pw == pw2){
                			if((pw.length < 4 || pw2.length < 4) || (pw == "" || pw2 == "")){
                				alert("비밀번호는 최소 4자 이상 입력해주세요.");
                			}else{
                				document.updatePw.action="updatePw.do"
                				document.updatePw.method="post"
                				document.updatePw.submit();
                				alert("비밀번호가 변경되었습니다.")
                			}
                		}
                	}
                </script>
              </div>
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