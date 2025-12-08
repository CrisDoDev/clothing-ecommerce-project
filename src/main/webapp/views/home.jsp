<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Trang chủ</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/images/icons/favicon.png" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/fonts/iconic/css/material-design-iconic-font.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/fonts/linearicons-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/slick/slick.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/MagnificPopup/magnific-popup.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/util.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css">
<style>
.active-pagination {
	background-color: #717fe0;
	color: white !important;
	border-color: #717fe0;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />

	<section class="section-slide">
		<div class="wrap-slick1">
			<div class="slick1">
				<div class="item-slick1"
					style="background-image: url(${pageContext.request.contextPath}/images/slide-01.jpg);">
					<div class="container h-full">
						<div class="flex-col-l-m h-full p-t-100 p-b-30 respon5">
							<span class="ltext-101 cl2 respon2">Women Collection 2018</span>
							<h2 class="ltext-201 cl2 p-t-19 p-b-43 respon1">NEW SEASON</h2>
							<a href="product"
								class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04">Shop
								Now</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<div class="sec-banner bg0 p-t-80 p-b-50">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-xl-4 p-b-30 m-lr-auto">
					<div class="block1 wrap-pic-w">
						<img src="${pageContext.request.contextPath}/images/banner-01.jpg"
							alt="IMG-BANNER"> <a href="product"
							class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
							<div class="block1-txt-child1 flex-col-l">
								<span class="block1-name ltext-102 trans-04 p-b-8">Women</span>
								<span class="block1-info stext-102 trans-04">Spring 2018</span>
							</div>
							<div class="block1-txt-child2 p-b-4 trans-05">
								<div class="block1-link stext-101 cl0 trans-09">Shop Now</div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-6 col-xl-4 p-b-30 m-lr-auto">
					<div class="block1 wrap-pic-w">
						<img src="${pageContext.request.contextPath}/images/banner-02.jpg"
							alt="IMG-BANNER"> <a href="product"
							class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
							<div class="block1-txt-child1 flex-col-l">
								<span class="block1-name ltext-102 trans-04 p-b-8">Men</span> <span
									class="block1-info stext-102 trans-04">Spring 2018</span>
							</div>
							<div class="block1-txt-child2 p-b-4 trans-05">
								<div class="block1-link stext-101 cl0 trans-09">Shop Now</div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-6 col-xl-4 p-b-30 m-lr-auto">
					<div class="block1 wrap-pic-w">
						<img src="${pageContext.request.contextPath}/images/banner-03.jpg"
							alt="IMG-BANNER"> <a href="product"
							class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
							<div class="block1-txt-child1 flex-col-l">
								<span class="block1-name ltext-102 trans-04 p-b-8">Accessories</span>
								<span class="block1-info stext-102 trans-04">New Trend</span>
							</div>
							<div class="block1-txt-child2 p-b-4 trans-05">
								<div class="block1-link stext-101 cl0 trans-09">Shop Now</div>
							</div>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<section class="bg0 p-t-23 p-b-140">
		<div class="container">
			<div class="p-b-10">
				<h3 class="ltext-103 cl5">Tổng quan các sản phẩm</h3>
			</div>

			<jsp:include page="/views/layout/sidebar.jsp" />

			<div class="row isotope-grid">
				<c:if test="${empty listProduct}">
					<div class="col-12 text-center p-t-50">
						<h3 class="ltext-103 cl5">Không tìm thấy sản phẩm nào!</h3>
					</div>
				</c:if>
				<c:forEach items="${listProduct}" var="p">
					<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item">
						<div class="block2">
							<div class="block2-pic hov-img0">
								<img
									src="${pageContext.request.contextPath}/images/${p.imageUrl}"
									alt="IMG-PRODUCT"> <a href="detail?pid=${p.id}"
									class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04">Quick
									View</a>
							</div>
							<div class="block2-txt flex-w flex-t p-t-14">
								<div class="block2-txt-child1 flex-col-l ">
									<a href="detail?pid=${p.id}"
										class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">${p.name}</a>
									<span class="stext-105 cl3"> <fmt:formatNumber
											value="${p.price}" type="number" maxFractionDigits="0" /> VND
									</span>
								</div>
								<div class="block2-txt-child2 flex-r p-t-3">
									<a href="#"
										class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
										<img class="icon-heart1 dis-block trans-04"
										src="${pageContext.request.contextPath}/images/icons/icon-heart-01.png"
										alt="ICON"> <img
										class="icon-heart2 dis-block trans-04 ab-t-l"
										src="${pageContext.request.contextPath}/images/icons/icon-heart-02.png"
										alt="ICON">
									</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

			<c:if test="${tag == null && endPage > 1}">
				<div class="flex-c-m flex-w w-full p-t-45">
					<c:forEach begin="1" end="${endPage}" var="i">
						<a href="home?page=${i}"
							class="flex-c-m stext-101 cl5 size-103 bg2 bor1 hov-btn1 p-lr-15 trans-04 m-all-7 ${currentPage == i ? 'active-pagination' : ''}">${i}</a>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</section>

	<jsp:include page="footer.jsp" />

	<script
		src="${pageContext.request.contextPath}/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/animsition/js/animsition.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/bootstrap/js/popper.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/select2/select2.min.js"></script>
	<script>$(".js-select2").each(function(){$(this).select2({minimumResultsForSearch: 20,dropdownParent: $(this).next('.dropDownSelect2')});})</script>
	<script
		src="${pageContext.request.contextPath}/vendor/daterangepicker/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/slick/slick.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/slick-custom.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/parallax100/parallax100.js"></script>
	<script>$('.parallax100').parallax100();</script>
	<script
		src="${pageContext.request.contextPath}/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
	<script>$('.gallery-lb').each(function() {$(this).magnificPopup({delegate: 'a', type: 'image', gallery: {enabled:true}, mainClass: 'mfp-fade'});});</script>
	<script
		src="${pageContext.request.contextPath}/vendor/isotope/isotope.pkgd.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/sweetalert/sweetalert.min.js"></script>
	<script>
        $('.js-addwish-b2').on('click', function(e){ e.preventDefault(); });
        $('.js-addwish-b2').each(function(){
            var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
            $(this).on('click', function(){
                swal(nameProduct, "is added to wishlist !", "success");
                $(this).addClass('js-addedwish-b2');
                $(this).off('click');
            });
        });
    </script>
	<script
		src="${pageContext.request.contextPath}/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
        $('.js-pscroll').each(function(){
            $(this).css('position','relative'); $(this).css('overflow','hidden');
            var ps = new PerfectScrollbar(this, {wheelSpeed: 1, scrollingThreshold: 1000, wheelPropagation: false});
            $(window).on('resize', function(){ ps.update(); })
        });
    </script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>