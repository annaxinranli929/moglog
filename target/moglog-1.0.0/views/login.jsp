<%--
  Created by IntelliJ IDEA.
  User: annax
  Date: 2025/05/26
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="jp">
<head>
    <title>管理員ログイン</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
</head>

<body class="login">

<header>
    <h1>管理員ログイン</h1>
</header>
<a class="back-link" href="${pageContext.request.contextPath}/posts">← 投稿一覧へ戻る</a>

<main class="login-container">

    <% String error = request.getParameter("error"); %>
    <% if ("1".equals(error)) { %>
    <div class="error">パスワードが正しくありません。</div>
    <% } %>

    <form action="login" method="post">
        <label for="password">パスワード:</label>
        <input type="password" name="password" id="password" required/>
        <button type="submit" class="submit-button">ログイン</button>
    </form>
</main>
</body>
</html>