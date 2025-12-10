<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${product.name}</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="images/icons/favicon.png" />
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="fonts/iconic/css/material-design-iconic-font.min.css">
<link rel="stylesheet" type="text/css"
	href="fonts/linearicons-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css"
	href="vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
<link rel="stylesheet" type="text/css"
	href="vendor/MagnificPopup/magnific-popup.css">
<link rel="stylesheet" type="text/css"
	href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>

	<jsp:include page="header.jsp" />

	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="home" class="stext-109 cl8 hov-cl1 trans-04">Home <i
				class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i></a> <span
				class="stext-109 cl4">${product.name}</span>
		</div>
	</div>

	<section class="sec-product-detail bg0 p-t-65 p-b-60">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-lg-7 p-b-30">
					<div class="p-l-25 p-r-30 p-lr-0-lg">
						<div class="wrap-slick3 flex-sb flex-w">
							<div class="wrap-slick3-dots"></div>
							<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>
							<div class="slick3 gallery-lb">
								<div class="item-slick3" data-thumb="images/${product.imageUrl}">
									<div class="wrap-pic-w pos-relative">
										<img src="images/${product.imageUrl}" alt="IMG-PRODUCT">
										<a
											class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
											href="images/${product.imageUrl}"><i class="fa fa-expand"></i></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-lg-5 p-b-30">
					<div class="p-r-50 p-t-5 p-lr-0-lg">
						<h4 class="mtext-105 cl2 js-name-detail p-b-14">${product.name}</h4>
						<span class="stext-105 cl3"> <fmt:formatNumber
								value="${p.price}" type="number" maxFractionDigits="0" /> VND
						</span>
						<p class="stext-102 cl3 p-t-23">${product.description}</p>
					</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp" />

	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="vendor/animsition/js/animsition.min.js"></script>
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="vendor/select2/select2.min.js"></script>
	<script>$(".js-select2").each(function(){$(this).select2({minimumResultsForSearch: 20,dropdownParent: $(this).next('.dropDownSelect2')});})</script>
	<script src="vendor/slick/slick.min.js"></script>
	<script src="js/slick-custom.js"></script>
	<script src="vendor/parallax100/parallax100.js"></script>
	<script>$('.parallax100').parallax100();</script>
	<script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
	<script>$('.gallery-lb').each(function() {$(this).magnificPopup({delegate: 'a', type: 'image', gallery: {enabled:true}, mainClass: 'mfp-fade'});});</script>
	<script src="vendor/sweetalert/sweetalert.min.js"></script>
	<script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>