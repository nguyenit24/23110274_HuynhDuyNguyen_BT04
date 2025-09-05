<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <style>
        body {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: #f8f9fa;
        }
        .login-card {
            width: 400px;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
            background: white;
        }
    </style>
</head>
<body>

<div class="login-card">
    <h2 class="text-center mb-4">Đăng Nhập</h2>

    <c:if test="${alert != null}">
        <div class="alert alert-danger">${alert}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">

        <div class="mb-3">
            <label class="form-label">Tài khoản</label>
            <input type="text" name="username" class="form-control" placeholder="Nhập tài khoản">
        </div>

        <div class="mb-3">
            <label class="form-label">Mật khẩu</label>
            <input type="password" name="password" class="form-control" placeholder="Nhập mật khẩu">
        </div>
        <button type="submit" class="btn btn-primary w-100">Đăng Nhập</button>

        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/forgetpass">Quên mật khẩu?</a>
            <br>
            <a href="${pageContext.request.contextPath}/register">Tạo tài khoản mới</a>
        </div>
    </form>
</div>
</body>
</html>
