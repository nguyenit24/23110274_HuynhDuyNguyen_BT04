<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Category CRUD</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<a href="<%=request.getContextPath()%>/logout">🚪 Logout</a>
<div class="container mt-1">
    <h2 class="mb-2">Category List</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>
    </c:if>
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>STT</th>
            <th>Category Name</th>
            <th>Image</th>
            <th>Category ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${categories}" var="cate" varStatus="STT">
            <tr>
                <td>${STT.index + 1}</td>
                <td>${cate.categoryname}</td>
                <td><img width="200px" src="${pageContext.request.contextPath}/image?fname=${cate.images}"/></td>
                <td>${cate.id}</td>
                <td>
                    <a href="<c:url value='/category/edit?id=${cate.id}'/>" class="btn btn-sm btn-primary">Edit</a>
                    <a href="<c:url value='/category/delete?id=${cate.id}'/>" class="btn btn-sm btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="<%=request.getContextPath()%>/category/add" class="btn btn-success" >Add New Category</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>