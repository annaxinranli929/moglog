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
    <meta charset="UTF-8">
    <title>投稿一覧 - もぐログ</title>
    <link rel="stylesheet" href="assets/css/style.css" />
</head>
<body class="posts">

    <!--Header -->
    <header>
        <h1>投稿一覧（もぐログ）</h1>
    </header>

    <!-- Main Content-->
    <main class="container">
    <%
        List<Post> posts = (List<Post>) request.getAttribute("posts");
        if (posts != null) {
          for (Post post : posts) {
    %>
        <!-- Cards -->
        <article class="card">
            <img src="<%= post.getImagePath() %>" width="300" alt="Post Image" />
            <div class="content">
                <h2 class="title"><%= post.getTitle() %></h2>
                <p class="meta">by <%= post.getAuthor() %> at <%= post.getCreatedAt() %></p>
                <p class="comment"><%= post.getContent() %></p>
            </div>
        </article>
    <%
            }
        } else {
    %>
        <p>No posts found.</p>
    <%
        }
    %>
    </main>

    <footer>2025 もぐログ</footer>
</body>
</html>
