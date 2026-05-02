<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body { font-family: Segoe UI, Arial, sans-serif; margin: 2rem; background: #f5f6fa; color: #2c3e50; }
        .error { color: #c0392b; background: #fadbd8; padding: 14px; border-radius: 4px; max-width: 520px; }
        a { color: #3498db; }
    </style>
</head>
<body>
<h1>Something went wrong</h1>
<div class="error">
    <c:if test="${not empty errorMessage}">${errorMessage}</c:if>
    <c:if test="${empty errorMessage}">An unexpected error occurred.</c:if>
</div>
<p><a href="${pageContext.request.contextPath}/students">Back to students</a></p>
</body>
</html>
