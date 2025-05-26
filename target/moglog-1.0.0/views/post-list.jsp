<%--
  Created by IntelliJ IDEA.
  User: annax
  Date: 2025/05/03
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, model.Post" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>🍴 投稿一覧 | 食の書</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&family=Zen+Maru+Gothic&display=swap"
          rel="stylesheet">
</head>

<body class="posts">
<!--Header -->
<header>
    <h1>食の書</h1>
    <div class="search-container">
        <form action="${pageContext.request.contextPath}/posts" method="get" class="search-container">
            <input type="text" name="keyword" class="search-bar" placeholder="タイトルを検索">
            <button type="submit" class="search-button">
                <img src="${pageContext.request.contextPath}/img/sicon.png" alt="検索"/>
            </button>
        </form>
    </div>
</header>
<!-- logout message -->
<%
    String msg = request.getParameter("msg");
    if ("logout".equals(msg)) {
%>
<div class="logout-message" id="logoutMsg">ログアウトしました。</div>
<%
    }
%>

<!-- layout -->
<div class="layout">
    <aside class="sidebar">
        <%
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        %>

        <a href="posts" class="side-button">すべての投稿</a>
        <a href="${pageContext.request.contextPath}/views/create-post.jsp" class="side-button">投稿作成</a>

        <% if (isAdmin != null && isAdmin) { %>
        <span class="side-button admin-disabled">管理員モード中</span>
        <a href="${pageContext.request.contextPath}/logout" class="side-button">ログアウト</a>
        <% } else { %>
        <a href="${pageContext.request.contextPath}/login" class="side-button">管理員ログイン</a>
        <% } %>


    </aside>

    <!-- Main Content-->
    <main class="container">
        <div class="post-grid">
            <%
                // Servletから渡された投稿リスト(posts)を受け取る
                List<Post> posts = (List<Post>) request.getAttribute("posts");
                // DEBUG CHECK
                // out.println("【DEBUG】post size = " + (posts == null ? "null" : posts.size()));

                if (posts != null) {
                    for (Post post : posts) {
            %>
            <!-- Cards -->

            <article class="card">
                <a href="view-post?id=<%= post.getId() %>" class="card-link">
                    <img src="<%= post.getImagePath() %>" width="300" alt="Post Image"/></a>
                <div class="content">
                    <h2 class="title"><%= post.getTitle() %>
                    </h2>
                    <p class="meta">by <%= post.getAuthor() %>
                    </p>
                    <form action="${pageContext.request.contextPath}/like-post" method="post">
                        <input type="hidden" name="id" value="<%= post.getId() %>">
                        <button type="submit" class="like-button">❤ <%= post.getLikes() %> いいね</button>
                    </form>
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
<script src="${pageContext.request.contextPath}/js/form.js" defer></script>
</body>
</html>
