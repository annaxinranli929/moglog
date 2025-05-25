<%--
  Created by IntelliJ IDEA.
  User: annax
  Date: 2025/05/05
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="jp">
<head>
    <meta charset="UTF-8">
    <title>🍴 投稿を作る | 食の書</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />


    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
</head>

<body class="create">

<header>
    <h1>新しいグルメを投稿！</h1>
</header>
<a class="back-link" href="${pageContext.request.contextPath}/posts">← 投稿一覧へ戻る</a>
<main class="form-container">
    <form action="${pageContext.request.contextPath}/create-post" method="post" enctype="multipart/form-data">
        <img id="preview" src="#" alt="プレビュー画像" style="display:none; max-width: 300px; border-radius: 10px;"><br><br>

        <label for="image">写真をアップしよう</label>
        <input type="file" name="image" id="image" accept="image/*">

        <label for="title">タイトル</label>
        <input type="text" name="title" id="title" required
               oninvalid="this.setCustomValidity('ここにタイトルが必要だよ')"
               oninput="this.setCustomValidity('')">

        <label for="author">ユーザー名</label>
        <input type="text" name="author" id="author" required
               oninvalid="this.setCustomValidity('名乗ってから投稿してね')"
               oninput="this.setCustomValidity('')">

        <label for="content">コメントや感想など</label>
        <textarea name="content" id="content" rows="5" required
                  oninvalid="this.setCustomValidity('何か書いてほしいな')"
                  oninput="this.setCustomValidity('')"></textarea>

        <span id="count">残り200文字</span>

        <button type="submit" class="submit-button"> シェア！</button>
    </form>
</main>
<script src="${pageContext.request.contextPath}/js/form.js" defer></script>
</body>
</html>
