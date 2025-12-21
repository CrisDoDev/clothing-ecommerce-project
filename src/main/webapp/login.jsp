<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Đăng nhập</title>
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
			<h2 class="auth-title">Đăng nhập</h2>
			<c:if test="${param.status == 'success'}">
				<div class="alert alert-success" role="alert">Đăng ký tài
					khoản thành công! Vui lòng đăng nhập để tiếp tục.</div>
			</c:if>

			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger" role="alert">${errorMessage}</div>
			</c:if>

			<form action="login" method="post">
				<div class="input-group">
					<label>Tài khoản</label> <input type="text" name="username"
						class="auth-input" placeholder="Nhập Email của bạn" required>
				</div>

				<div class="input-group">
					<label>Mật khẩu</label> <input type="password" name="password"
						class="auth-input" placeholder="Nhập mật khẩu" required>
				</div>

				<!--<div class="text-right p-b-10"
					style="margin-bottom: 15px; font-size: 13px;">
					<a href="#" style="color: #666;">Quên mật khẩu?</a>
				</div>
				-->

				<button type="submit" class="auth-btn">Đăng nhập</button>
			</form>

			<div class="auth-divider">
				<span>hoặc</span>
			</div>

			<div class="auth-link">
				Chưa có tài khoản? <a href="register.jsp">Đăng ký ngay</a>
			</div>

			<div class="back-home">
				<a href="views/home.jsp"><i class="fa fa-arrow-left"></i> Quay
					lại trang chủ</a>
			</div>
		</div>
	</div>
</body>
</html>