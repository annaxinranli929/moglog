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
    <title>食の書 - 投稿一覧</title>
    <link rel="stylesheet" href="assets/css/style.css" />

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
</head>

<body class="posts">
    <!--Header -->
    <header>
        <h1>食の書</h1>
        <div class="search-container">
            <input type="text" class="search-bar" placeholder="検索...">
            <button type="submit" class="search-button">
                <img src="../assets/img/sicon.png" alt="検索" />
            </button>
        </div>
    </header>

    <!-- layout -->
    <div class="layout">
        <aside class="sidebar">
            <a href="posts" class="side-button">すべての投稿</a>
            <a href="create.jsp" class="side-button">投稿作成</a>
            <a href="posts" class="side-button">ログイン</a>
        </aside>

    <!-- Main Content-->
    <main class="container">
        <div class="post-grid">
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
        </div>
    </main>
    </div>
</body>
</html>
