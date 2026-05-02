<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Students</title>
    <style>
        body { font-family: Segoe UI, Arial, sans-serif; margin: 2rem; background: #f5f6fa; color: #2c3e50; }
        h1 { color: #34495e; }
        table { border-collapse: collapse; width: 100%; max-width: 960px; background: #fff; box-shadow: 0 1px 3px rgba(0,0,0,.08); }
        th, td { border: 1px solid #dfe6e9; padding: 10px 12px; text-align: left; vertical-align: top; }
        th { background: #3498db; color: #fff; }
        tr:nth-child(even) { background: #f8f9fa; }
        .toolbar { margin-bottom: 1rem; }
        a.btn, .btn { display: inline-block; padding: 8px 14px; background: #3498db; color: #fff !important; text-decoration: none; border-radius: 4px; border: none; cursor: pointer; font-size: 14px; }
        a.btn.secondary { background: #95a5a6; margin-left: 8px; }
        .courses { font-size: 13px; color: #555; }
        ul.courses { margin: 4px 0 0 1.2rem; padding: 0; }
    </style>
</head>
<body>
<h1>All students</h1>
<div class="toolbar">
    <a class="btn" href="${pageContext.request.contextPath}/students/add">Add student</a>
</div>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Courses</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="s" items="${students}">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.email}</td>
            <td class="courses">
                <c:choose>
                    <c:when test="${empty s.courses}">—</c:when>
                    <c:otherwise>
                        <ul class="courses">
                            <c:forEach var="c" items="${s.courses}">
                                <li>${c.title} <span style="color:#888;">(${c.duration})</span></li>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </td>
            <td><a class="btn secondary" href="${pageContext.request.contextPath}/students/edit/${s.id}">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
