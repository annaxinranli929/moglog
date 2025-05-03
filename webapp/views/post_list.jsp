<%--
  Created by IntelliJ IDEA.
  User: annax
  Date: 2025/05/03
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, model.dto.Post" %>
<html>
<head>
    <title>投稿一覧 - もぐログ</title>
</head>
<body>
    <h1>投稿一覧（もぐログ）</h1>

    <%
        List<Post> posts = (List<Post>) request.getAttribute("posts");
        if (posts != null) {
          for (Post post : posts) {
    %>
        <div style="border: 1px solid #ccc; margin: 10px; padding: 10px;">
            <h2><%= post.getTitle() %></h2>
            <p>by <%= post.getAuthor() %> at <%= post.getCreatedAt() %></p>
            <p><%= post.getContent() %></p>
            <img src="<%= post.getImagePath() %>" width="300" alt="Post Image" />
        </div>
    <%
            }
        } else {
    %>
        <p>No posts found.</p>
    <%
        }
    %>
</body>
</html>
