<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>絆桌</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<META http-equiv="Content-Type" content="text/html; charset=BIG5">
	<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/frontend/frontend_template/images/icons/board-game (1).png" />
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/vendor/bootstrap/css/bootstrap.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/fonts/iconic/css/material-design-iconic-font.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/fonts/linearicons-v1.0.0/icon-font.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/vendor/animate/animate.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/vendor/css-hamburgers/hamburgers.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/vendor/animsition/css/animsition.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/vendor/select2/select2.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/vendor/daterangepicker/daterangepicker.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/vendor/slick/slick.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/vendor/MagnificPopup/magnific-popup.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/vendor/perfect-scrollbar/perfect-scrollbar.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/frontend_template/css/main.css">
	<!--===============================================================================================-->

</head>

<body class="animsition">

	<!-- Header  -->
	<header class="header-v2">
		<!-- Header desktop -->
		<div class="container-menu-desktop trans-03">
			<div class="wrap-menu-desktop">
				<nav class="limiter-menu-desktop p-l-45">
					<!-- Logo desktop -->
					<a href="BackEndTemplate.html" class="logo">
						<img src="<%=request.getContextPath()%>/frontend/frontend_template/images/icons/LOGO_WISH3.0.png" alt="IMG-LOGO" width="100px">
					</a>
					<!-- Menu desktop -->
					<div class="menu-desktop">
						<ul class="main-menu">
							<li class="active-menu">
								<a href="Top&Fot.html">首頁</a>
							</li>
							<li>
								<a href="product.html">商城</a>
							</li>

							<li>
								<a href="#">訂位</a>
							</li>
							<li>
								<a href="#">活動</a>
							</li>
							<li>
								<a href="blog.html">討論區</a>
							</li>
							<li>
								<a href="about.html">關於我們</a>
							</li>
							<li>
								<a href="contact.html">聯絡我們</a>
							</li>
						</ul>
					</div>
					<!-- Icon header 搜尋、購物車、漢堡 -->
					<div class="wrap-icon-header flex-w flex-r-m h-full">
						<a href="<%=request.getContextPath()%>/frontend/cart/getCart.do" >
							<div class="flex-c-m h-full p-l-18 p-r-25 bor5" >
								<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11  js-show-cart">
									<!-- <div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 icon-header-noti js-show-cart" data-notify="2"></div> -->
									<i  class="zmdi zmdi-shopping-cart"></i>
								</div>
							</div>
							<input type="hidden" name="action"	value="getCart">
						</a>

						<div class="flex-c-m h-full p-lr-19">
							<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-sidebar">
								<i class="zmdi zmdi-menu"></i>
							</div>
						</div>
					</div>
				</nav>
			</div>
		</div>
		<!-- Header Mobile -->
		<div class="wrap-header-mobile">
			<!-- Logo moblie -->
			<div class="logo-mobile">
				<a href="index.html"><img src="<%=request.getContextPath()%>/frontend/frontend_template/images/icons/logo-01.png" alt="IMG-LOGO"></a>
			</div>
			<!-- Icon header -->
			<div class="wrap-icon-header flex-w flex-r-m h-full m-r-15">
				<div class="flex-c-m h-full p-r-10">
					<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-modal-search">
						<i class="zmdi zmdi-search"></i>
					</div>
				</div>

				<div class="flex-c-m h-full p-lr-10 bor5">
					<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 icon-header-noti js-show-cart"
						data-notify="2">
						<i  class="zmdi zmdi-shopping-cart"></i>
					</div>
				</div>
			</div>
			<!-- Button show menu -->
			<div class="btn-show-menu-mobile hamburger hamburger--squeeze">
				<span class="hamburger-box">
					<span class="hamburger-inner"></span>
				</span>
			</div>
		</div>
		<!-- Menu Mobile -->
		<div class="menu-mobile">
			<ul class="main-menu-m">
				<li>
					<a href="Top&Fot.html">首頁</a>
					<ul class="sub-menu-m">
						<li><a href="index.html">Homepage 1</a></li>
						<li><a href="home-02.html">Homepage 2</a></li>
						<li><a href="home-03.html">Homepage 3</a></li>
					</ul>
					<span class="arrow-main-menu-m">
						<i class="fa fa-angle-right" aria-hidden="true"></i>
					</span>
				</li>
				<li>
					<a href="product.html">商城</a>
				</li>
				<li>
					<a href="shoping-cart.html" class="label1 rs1" data-label1="hot">Features</a>
				</li>
				<li>
					<a href="blog.html">討論區</a>
				</li>
				<li>
					<a href="about.html">關於我們</a>
				</li>
				<li>
					<a href="contact.html">聯絡我們</a>
				</li>
			</ul>
		</div>
	</header>
	<!-- Sidebar -->
	<aside class="wrap-sidebar js-sidebar">
		<div class="s-full js-hide-sidebar"></div>

		<div class="sidebar flex-col-l p-t-22 p-b-25">
			<div class="flex-r w-full p-b-30 p-r-27">
				<div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-sidebar">
					<i class="zmdi zmdi-close"></i>
				</div>
			</div>

			<div class="sidebar-content flex-w w-full p-lr-65 js-pscroll">
				<ul class="sidebar-link w-full">
					<li class="p-b-13">
						<a href="Top&Fot.html" class="stext-102 cl2 hov-cl1 trans-04">
							首頁
						</a>
					</li>

					<li class="p-b-13">
						<a href="#" class="stext-102 cl2 hov-cl1 trans-04">
							會員中心
						</a>
					</li>

					<li class="p-b-13">
						<a href="#" class="stext-102 cl2 hov-cl1 trans-04">
							我的訂單
						</a>
					</li>
					<li class="p-b-13">
						<a href="#" class="stext-102 cl2 hov-cl1 trans-04">
							我的優惠券
						</a>
					</li>

					<li class="p-b-13">
						<a href="#" class="stext-102 cl2 hov-cl1 trans-04">
							收藏清單
						</a>
					</li>

					<li class="p-b-13">
						<a href="#" class="stext-102 cl2 hov-cl1 trans-04">
							申請退貨
						</a>
					</li>

					<li class="p-b-13">
						<a href="#" class="stext-102 cl2 hov-cl1 trans-04">
							Help & FAQs
						</a>
					</li>
				</ul>

				<div class="sidebar-gallery w-full p-tb-30">
					<img src="<%=request.getContextPath()%>/frontend/frontend_template/images/icons/LOGO_WISH3.0.png" alt="" width="70px">
					<span class="mtext-101 cl5">
						
					</span>

				</div>

				
			</div>
		</div>
	</aside>
