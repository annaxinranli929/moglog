<%--
  Created by IntelliJ IDEA.
  User: annax
  Date: 2025/05/07
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Post" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>投稿を編集 | 食の書</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="edit">
<div class="form-container">
    <h2>投稿を編集</h2>

    <%
        Post post = (Post) request.getAttribute("post");
    %>

    <form action="<%= request.getContextPath() %>/edit-post" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<%= post.getId() %>"/>

        <label for="title">タイトル</label>
        <input type="text" name="title" id="title" value="<%= post.getTitle() %>" required>

        <label for="author">ユーザー名</label>
        <input type="text" name="author" id="author" value="<%= post.getAuthor() %>" required>

        <label for="content">本文</label>
        <textarea name="content" id="content" rows="6" required><%= post.getContent() %></textarea>

        <label for="image">画像（変更しない場合はそのまま）</label>
        <input type="file" name="image" id="image">
        <p>現在の画像: <img src="<%= request.getContextPath() %>/<%= post.getImagePath() %>" alt="投稿画像" width="150"/></p>

        <button type="submit" class="submit-button">更新する</button>
    </form>
</div>
</body>
</html>
