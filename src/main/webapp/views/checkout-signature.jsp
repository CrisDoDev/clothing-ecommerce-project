<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ký xác nhận đơn hàng</title>
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body class="animsition">
	<jsp:include page="header.jsp" />

	<div class="container m-t-140 m-b-100">
		<div class="row">
			<div class="col-md-8 mx-auto">
				<div class="p-t-30 p-b-40 p-l-40 p-r-40 bor10 shadow-sm bg0"
					style="border: 1px solid #ccc;">
					<h4 class="mtext-111 cl2 p-b-20 text-center">ĐẶT HÀNG THÀNH
						CÔNG</h4>

					<p class="stext-111 cl6 p-b-20 text-center">
						Mã đơn hàng: <strong>#${orderId}</strong> <br /> Tổng tiền: <strong><fmt:formatNumber
								value="${totalMoney}" type="currency" currencySymbol="₫" /></strong>
					</p>

					<div class="bg0 p-t-20 p-b-20">
						<h5 class="mtext-111 cl2 p-b-10">Mã băm đơn hàng</h5>

						<div class="form-group m-b-20">
							<input type="text" id="orderHash" class="form-control"
								value="${orderHash}" readonly>
						</div>

						<h5 class="mtext-111 cl2 p-t-20 p-b-10">Xác nhận chữ ký số</h5>
						<form action="submit-signature" method="post">
							<input type="hidden" name="orderId" value="${orderId}"> <input
								type="hidden" name="orderHash" value="${orderHash}">

							<div class="form-group">
								<textarea name="signature" class="form-control" rows="5"
									required></textarea>
							</div>

							<button type="submit"
								class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer mt-4">
								XÁC NHẬN</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>