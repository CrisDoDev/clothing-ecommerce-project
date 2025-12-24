<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Đăng ký</title>
<link rel="icon" type="image/png" href="images/icons/favicon.png" />

<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="css/auth.css">
</head>
<body>
	<div class="auth-container">
		<div class="auth-box">
			<h2 class="auth-title">Đăng ký</h2>

			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger" role="alert">${errorMessage}</div>
			</c:if>

			<form action="register" method="post">
				<div class="input-group">
					<label>Họ và tên</label> <input type="text" name="fullname"
						class="auth-input" placeholder="Ví dụ: Nguyễn Văn A"
						value="${param.fullname}" required>
				</div>

				<div class="input-group">
					<label>Email</label> <input type="email" name="email"
						class="auth-input" placeholder="Nhập email" value="${param.email}"
						required>
				</div>

				<div class="input-group">
					<label>Số điện thoại</label> <input type="tel" name="phone"
						class="auth-input" placeholder="Nhập số điện thoại (10 số)"
						value="${param.phone}" pattern="0[0-9]{9}"
						title="Số điện thoại phải bắt đầu bằng số 0 và có 10 chữ số"
						required>
				</div>

				<div class="input-group">
					<label>Mật khẩu</label> <input type="password" name="password"
						class="auth-input" placeholder="Tạo mật khẩu" required>
				</div>

				<div class="input-group">
					<label>Xác nhận mật khẩu</label> <input type="password"
						name="confirmPassword" class="auth-input"
						placeholder="Nhập lại mật khẩu" required>
				</div>

				<button type="submit" class="auth-btn">Đăng ký thành viên</button>
			</form>

			<div class="auth-divider">
				<span>hoặc</span>
			</div>

			<div class="auth-link">
				Đã có tài khoản? <a href="login.jsp">Đăng nhập</a>
			</div>

			<div class="back-home">
				<a href="${pageContext.request.contextPath}/home"><i class="fa fa-arrow-left"></i> Quay
					lại trang chủ</a>
			</div>
		</div>
	</div>
</body>
</html>