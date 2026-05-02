<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add student</title>
    <style>
        body { font-family: Segoe UI, Arial, sans-serif; margin: 2rem; background: #f5f6fa; color: #2c3e50; }
        h1 { color: #34495e; }
        .card { max-width: 520px; background: #fff; padding: 1.5rem; border-radius: 6px; box-shadow: 0 1px 3px rgba(0,0,0,.08); }
        label { display: block; margin-top: 12px; font-weight: 600; }
        input[type="text"], input[type="email"] { width: 100%; max-width: 400px; padding: 8px; margin-top: 4px; box-sizing: border-box; }
        .courses { margin-top: 16px; }
        .course-row { margin: 6px 0; }
        .error { color: #c0392b; background: #fadbd8; padding: 10px; border-radius: 4px; margin-bottom: 12px; }
        .actions { margin-top: 20px; }
        a.btn, button.btn { display: inline-block; padding: 8px 14px; background: #3498db; color: #fff !important; text-decoration: none; border-radius: 4px; border: none; cursor: pointer; font-size: 14px; }
        a.btn.secondary { background: #95a5a6; margin-left: 8px; }
    </style>
</head>
<body>
<h1>Add student</h1>
<div class="card">
    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/students/save" method="post">
        <label for="name">Name</label>
        <input id="name" name="name" type="text" value="${student.name}" required>

        <label for="email">Email</label>
        <input id="email" name="email" type="email" value="${student.email}" required>

        <div class="courses">
            <strong>Courses</strong>
            <c:forEach var="c" items="${courses}">
                <div class="course-row">
                    <label>
                        <input type="checkbox" name="courseIds" value="${c.id}">
                        ${c.title} <span style="color:#888;">(${c.duration})</span>
                    </label>
                </div>
            </c:forEach>
        </div>

        <div class="actions">
            <button type="submit" class="btn">Save</button>
            <a class="btn secondary" href="${pageContext.request.contextPath}/students">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
