<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Recipe</title>
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
          <li class="active"><a href="recipePaging.do?category="><span>Recipe</span></a></li>
          <li><a href="account-orders.html"><span>Mypage</span></a>
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
          <div class="toolbar-section" id="account">
            <ul class="nav nav-tabs nav-justified" role="tablist">
              <li class="nav-item"><a class="nav-link active" href="#login" data-toggle="tab" role="tab">Log In</a></li>
              <li class="nav-item"><a class="nav-link" href="#signup" data-toggle="tab" role="tab">Sign Up</a></li>
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
        <h1>RECIPE</h1>
      </div>
    </div>
    <!-- Page Content-->
    <div class="container padding-bottom-2x mb-4">
      <div class="row justify-content-center">
        <div class="col-lg-10">
          <!-- Post Meta-->
          <div class="blog-post-meta">
            <div class="column"><span>by&nbsp;</span>${recipe.written }<span class="divider"></span><span>in&nbsp;</span><a href="recipePaging.do?category=${recipe.category }">${recipe.category }</a></div>
            <div class="column"><span><i class="material-icons date_range"></i>&nbsp;${recipe.date }</span></div>
          </div>
          <!-- Post Content -->
          <h2 class="margin-top-2x">${recipe.subject }</h2>
          <p>${recipe.content }</p>
          <c:forEach var="rimg" items="${rimageList }">
          	<div class="gallery-item" data-hash="one">
            	<!-- <a href="/rdownload.do?recipeno=${recipe.recipeno }&rfilename=${rimg.rimageFileName }" data-size="555x480"> --> 
                <img src="<c:url value='/rdownload.do?recipeno=${recipe.recipeno }&rfilename=${rimg.rimageFileName }' />" alt="Recipe"></a></div>
          </c:forEach>
          <div class="col-12 text-right">
          <a href="updateRjsp.do?recipeno=${recipe.recipeno }" class="btn btn-primary btn-sm">수정</a>
          <a href="deleteRecipe.do?recipeno=${recipe.recipeno }" class="btn btn-primary btn-sm">삭제</a>
          </div>
          <!-- Post Tags + Share-->
          <div class="d-flex flex-wrap justify-content-between align-items-center pt-3 pb-4">
            <div class="pb-2"></div>
            <a class="btn btn-outline-secondary btn-sm text-danger" href="insertMylike.do"><i class="material-icons favorite_border"></i>&nbsp;LIKE</a>
          </div>
          <!-- Post Navigation-->
          <div class="entry-navigation">
            <div class="column text-left"><a class="btn btn-outline-secondary btn-sm" href="#"><i class="material-icons keyboard_arrow_left"></i>&nbsp;이전</a></div>
            <div class="column"><a class="btn btn-outline-secondary view-all" href="recipePaging.do?category=${recipe.category }" data-toggle="tooltip" data-placement="top" title="목록"><i class="material-icons menu"></i></a></div>
            <div class="column text-right"><a class="btn btn-outline-secondary btn-sm" href="#">다음&nbsp;<i class="material-icons keyboard_arrow_right"></i></a></div>
          </div>
          <!-- Comments-->
          <section class="padding-top-3x" id="comments">
            <h3 class="padding-bottom-1x">댓글</h3>
            <!-- Comment-->
            <div class="comment">
              <div class="comment-author-ava"><img src="img/reviews/01.jpg" alt="Comment author"></div>
              <div class="comment-body">
                <div class="comment-header">
                  <h4 class="comment-title">Francis Burton</h4>
                </div>
                <p class="comment-text">At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.</p>
                <div class="comment-footer">
                  <div class="column"><span class="comment-meta">2 days ago</span></div>
                  <div class="column"><a class="reply-link" href="#"><i class="material-icons reply"></i>Reply</a></div>
                </div>
                <!-- Comment reply-->
                <div class="comment comment-reply">
                  <div class="comment-author-ava"><img src="img/reviews/02.jpg" alt="Comment author"></div>
                  <div class="comment-body">
                    <div class="comment-header">
                      <h4 class="comment-title">Maggie Scott</h4>
                    </div>
                    <p class="comment-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                    <div class="comment-footer"><span class="comment-meta">1 day ago</span></div>
                  </div>
                </div>
              </div>
            </div>
            <!-- Comment-->
            <div class="comment">
              <div class="comment-author-ava"><img src="img/reviews/03.jpg" alt="Comment author"></div>
              <div class="comment-body">
                <div class="comment-header">
                  <h4 class="comment-title">Jacob Hammond</h4>
                </div>
                <p class="comment-text">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
                <div class="comment-footer">
                  <div class="column"><span class="comment-meta">5 days ago</span></div>
                  <div class="column"><a class="reply-link" href="#"><i class="material-icons reply"></i>Reply</a></div>
                </div>
              </div>
            </div>
          </section>
          <!-- Comment Form-->
          <form class="row" method="post">
            <div class="col-12">
              <div class="form-group">
                <textarea class="form-control" rows="7" id="comment-text" placeholder="댓글을 입력하세요" required></textarea>
              </div>
            </div>
            <div class="col-12 text-right">
              <button class="btn btn-primary" type="submit">입력</button>
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
    <script src="resources/js/vendor.min.js"></script>
    <script src="resources/js/scripts.min.js"></script>
  </body>
</html>