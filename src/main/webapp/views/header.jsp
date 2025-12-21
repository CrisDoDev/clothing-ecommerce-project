<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<header class="header-v4">
	<div class="container-menu-desktop">
		<div class="top-bar">
			<div class="content-topbar flex-sb-m h-full container">
				<div class="left-top-bar">Free ship toàn quốc</div>
				<div class="right-top-bar flex-w h-full">
					<a href="#" class="flex-c-m trans-04 p-lr-25"> Help & FAQs </a>
					<c:choose>
						<%-- Truong hop 1 da dang nhap --%>
						<c:when test="${not empty sessionScope.user}">
							<a href="profile.jsp" class="flex-c-m trans-04 p-lr-25"
								style="color: #00ad5f; font-weight: bold;"> <i
								class="fa fa-user m-r-5"></i> Xin chào,
								${sessionScope.user.fullName}
							</a>

							<a href="order-history.jsp" class="flex-c-m trans-04 p-lr-25">
								Đơn mua </a>

							<c:if test="${sessionScope.user.roleId == 1}">
								<a href="admin/dashboard.jsp" class="flex-c-m trans-04 p-lr-25"
									style="color: red;"> Trang quản trị </a>
							</c:if>

							<a href="${pageContext.request.contextPath}/logout"
								class="flex-c-m trans-04 p-lr-25"> Đăng xuất </a>
						</c:when>

						<%-- Truong hop 2 : chua dang nhap --%>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/register.jsp"
								class="flex-c-m trans-04 p-lr-25"> Đăng ký </a>
							<a href="${pageContext.request.contextPath}/login.jsp"
								class="flex-c-m trans-04 p-lr-25"> Đăng nhập </a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<div class="wrap-menu-desktop">
			<nav class="limiter-menu-desktop container">
				<a href="home" class="logo">
					<h1 style="font-size: 24px; font-weight: bold; color: #333;">
						SHOP THỜI TRANG</h1>
				</a>

				<div class="menu-desktop">
					<ul class="main-menu">
						<li
							class="${pageContext.request.servletPath == '/home.jsp' ? 'active-menu' : ''}">
							<a href="home">Trang chủ</a>
						</li>
						<li><a href="product">Cửa hàng</a></li>
						<li><a href="shoping-cart.jsp">Giỏ hàng</a></li>
						<li><a href="about.jsp">Giới thiệu</a></li>
						<li><a href="contact.jsp">Liên hệ</a></li>
					</ul>
				</div>

				<div class="wrap-icon-header flex-w flex-r-m">
					<div
						class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 js-show-modal-search">
						<i class="zmdi zmdi-search"></i>
					</div>
					<div
						class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart"
						data-notify="0">
						<i class="zmdi zmdi-shopping-cart"></i>
					</div>
				</div>
			</nav>
		</div>
	</div>

	<div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
		<div class="container-search-header">
			<button
				class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search">
				<img
					src="${pageContext.request.contextPath}/images/icons/icon-close2.png"
					alt="CLOSE">
			</button>
			<form class="wrap-search-header flex-w p-l-15">
				<button class="flex-c-m trans-04">
					<i class="zmdi zmdi-search"></i>
				</button>
				<input class="plh3" type="text" name="search"
					placeholder="Search...">
			</form>
		</div>
	</div>
</header>

<div class="wrap-header-cart js-panel-cart">
	<div class="s-full js-hide-cart"></div>
	<div class="header-cart flex-col-l p-l-65 p-r-25">
		<div class="header-cart-title flex-w flex-sb-m p-b-8">
			<span class="mtext-103 cl2">Giỏ hàng</span>
			<div
				class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
				<i class="zmdi zmdi-close"></i>
			</div>
		</div>
		<div class="header-cart-content flex-w js-pscroll">
			<ul class="header-cart-wrapitem w-full">
				<li class="header-cart-item flex-w flex-t m-b-12">
					<div class="header-cart-item-img">
						<img
							src="${pageContext.request.contextPath}/images/item-cart-01.jpg"
							alt="IMG">
					</div>
					<div class="header-cart-item-txt p-t-8">
						<a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">White
							Shirt Pleat</a> <span class="header-cart-item-info">1 x $19.00</span>
					</div>
				</li>
			</ul>
			<div class="w-full">
				<div class="header-cart-total w-full p-tb-40">Total: $75.00</div>
				<div class="header-cart-buttons flex-w w-full">
					<a href="shoping-cart.html"
						class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">View
						Cart</a> <a href="shoping-cart.html"
						class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">Check
						Out</a>
				</div>
			</div>
		</div>
	</div>
</div>

<c:if test="${param.status == 'logged_out'}">
    <script>
        // Khi trang load xong thì chạy ngay script này
        document.addEventListener("DOMContentLoaded", function() {
            Swal.fire({
                icon: 'success',         
                title: 'Đăng xuất thành công!',
                text: 'Hẹn gặp lại bạn lần sau.',
                position: 'center',        
                showConfirmButton: false,  
                timer: 2000,            
                timerProgressBar: true  
            });
            
            if (window.history.replaceState) {
                const url = new URL(window.location.href);
                url.searchParams.delete('status');
                window.history.replaceState({path: url.href}, '', url.href);
            }
        });
    </script>
</c:if>