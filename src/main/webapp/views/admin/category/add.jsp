<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<a href="<%=request.getContextPath()%>/logout">🚪 Logout</a>
<div class="container mt-5">
    <h2 class="mb-4">Add New Category</h2>
    <form action="<c:url value='/category/add'/>" method="post" enctype="multipart/form-data"> 
        <div class="mb-3">
            <label for="categoryname" class="form-label">Category Name</label>
            <input type="text" class="form-control" id="categoryname" name="categoryname" required>
        </div>

        <div class="mb-3">
            <label for="images" class="form-label">Image</label>
            <input type="file" class="form-control" id="image" name="images" accept="image/*">
        </div>

        <button type="submit" class="btn btn-success">Save</button>
        <a href="javascript:history.back()" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
