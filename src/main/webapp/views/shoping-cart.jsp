
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Giỏ hàng</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.png" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/linearicons-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
<jsp:include page="header.jsp" />
</head>
<body class="animsition">

	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="home" class="stext-109 cl8 hov-cl1 trans-04"> Trang chủ
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a> <span class="stext-109 cl4">Giỏ hàng</span>
		</div>
	</div>

	<form class="bg0 p-t-75 p-b-85" action="cart" method="post">
		<input type="hidden" name="action" value="update">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th class="column-1">Sản phẩm</th>
									<th class="column-2"></th>
									<th class="column-3">Giá</th>
									<th class="column-4">Số lượng</th>
									<th class="column-5">Tổng</th>
								</tr>

								<%-- Kiểm tra giỏ hàng có trống không --%>
								<c:if test="${empty sessionScope.cart.items}">
									<tr>
										<td colspan="5" class="text-center p-t-20 p-b-20">Giỏ
											hàng đang trống. <a href="product">Mua sắm ngay!</a>
										</td>
									</tr>
								</c:if>

								<%-- Vòng lặp hiển thị CartItem --%>
								<c:forEach items="${sessionScope.cart.items}" var="item">
									<tr class="table_row">
										<td class="column-1">
											<%-- Nút Xóa (Click vào ảnh hoặc thêm icon X) --%>
											<div class="how-itemcart1"
												onclick="window.location='cart?action=remove&id=${item.product.id}&size=${item.size}'"
												title="Xóa sản phẩm này">
												<img src="images/${item.product.imageUrl}" alt="IMG">
											</div>
										</td>
										<td class="column-2">${item.product.name}<br> <span
											style="font-size: 12px; color: #888;">Size:
												${item.size}</span>
										</td>
										<td class="column-3"><fmt:formatNumber
												value="${item.product.price}" type="number"
												maxFractionDigits="0" /> đ</td>
										<td class="column-4">
											<div class="wrap-num-product flex-w m-l-auto m-r-0">
												<div
													class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
													<i class="fs-16 zmdi zmdi-minus"></i>
												</div>

												<%-- Name phải unique để xử lý update sau này, tạm thời để hiển thị --%>
												<input class="mtext-104 cl3 txt-center num-product"
													type="number"
													name="num_product_${item.product.id}_${item.size}"
													value="${item.quantity}">

												<div
													class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
													<i class="fs-16 zmdi zmdi-plus"></i>
												</div>
											</div>
										</td>
										<td class="column-5"><fmt:formatNumber
												value="${item.totalPrice}" type="number"
												maxFractionDigits="0" /> đ</td>
									</tr>
								</c:forEach>
							</table>
						</div>

						<div
							class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
							<div class="flex-w flex-m m-r-20 m-tb-5"></div>

							<button
								class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
								Cập nhật giỏ hàng</button>
						</div>
					</div>
				</div>

				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
					<div
						class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-30">Tổng số tiền</h4>

						<div class="flex-w flex-t bor12 p-b-13">
							<div class="size-208">
								<span class="stext-110 cl2"> Tạm tính: </span>
							</div>

							<div class="size-209">
								<span class="mtext-110 cl2"> <c:set var="total"
										value="${sessionScope.cart.totalMoney}" /> <fmt:formatNumber
										value="${total}" type="number" maxFractionDigits="0" /> đ
								</span>
							</div>
						</div>

						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="mtext-101 cl2"> Tổng cộng: </span>
							</div>

							<div class="size-209 p-t-1">
								<span class="mtext-110 cl2"> <fmt:formatNumber
										value="${total}" type="number" maxFractionDigits="0" /> đ
								</span>
							</div>
						</div>

						<c:choose>
							<c:when test="${not empty sessionScope.cart.items}">
								<a href="checkout"
									class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
									Tiến hành thanh toán </a>
							</c:when>
							<c:otherwise>
								<button type="button"
									class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer"
									disabled style="opacity: 0.5;">Giỏ hàng trống</button>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
			</div>
		</div>
	</form>

	<jsp:include page="footer.jsp" />

	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="vendor/animsition/js/animsition.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$('.js-pscroll').each(function() {
			$(this).css('position', 'relative');
			$(this).css('overflow', 'hidden');
			var ps = new PerfectScrollbar(this, {
				wheelSpeed : 1,
				scrollingThreshold : 1000,
				wheelPropagation : false,
			});
			$(window).on('resize', function() {
				ps.update();
			})
		});
	</script>
	<script src="js/main.js"></script>

	<script>
		// Code này để nút +/- hoạt động về mặt giao diện
		$('.btn-num-product-down').on('click', function() {
			var numProduct = Number($(this).next().val());
			if (numProduct > 0)
				$(this).next().val(numProduct - 1);
		});

		$('.btn-num-product-up').on('click', function() {
			var numProduct = Number($(this).prev().val());
			$(this).prev().val(numProduct + 1);
		});
	</script>

</body>
</html>