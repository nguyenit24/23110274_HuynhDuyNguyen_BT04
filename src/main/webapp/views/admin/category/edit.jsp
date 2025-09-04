<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sửa Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<a href="<%=request.getContextPath()%>/logout">🚪 Logout</a>
<div class="container mt-5">
    <h2 class="mb-4">Add New Category</h2>
    <form action="<c:url value='/admin/category/edit'/>" method="post" enctype="multipart/form-data">
    
    	<input type="hidden" name="id" value="${category.id}"/>
        <div class="mb-3">
            <label for="categoryname" class="form-label">Category Name</label>
            <input type="text" class="form-control" id="categoryname" name="categoryname" value="${category.categoryname}" required>
        </div>

        <div class="mb-3">
            <label for="images" class="form-label">Image</label>
            <img src="${pageContext.request.contextPath}/image?fname=${category.images}" alt="Category Image" style="max-width: 200px; display: block; margin-bottom: 10px;"/>
            <input type="file" class="form-control" id="images" name="images" accept="image/*">
        </div>

        <button type="submit" class="btn btn-success">Save</button>
        <a href="<c:url value='/admin/categories'/>" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
