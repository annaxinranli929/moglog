<%--
  Created by IntelliJ IDEA.
  User: annax
  Date: 2025/05/06
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>🍴 投稿を見る | 食の書</title>
    <link rel="stylesheet" href="../assets/css/style.css"/>

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&family=Zen+Maru+Gothic&display=swap"
          rel="stylesheet">
</head>

<body class="view">
<header>
    <h1></h1>
</header>

<a class="back-link" href="${pageContext.request.contextPath}/posts">← 投稿一覧へ戻る</a>

<div class="post-container">
    <!--アプリ全体のルートパス & DBに保存された画像の相対パス -->
    <img src="${pageContext.request.contextPath}/${post.imagePath}" class="post-image" alt="投稿画像"/>
    <p class="post-title">${post.title}</p>
    <p class="post-meta">
        by ${post.author} | <%= request.getAttribute("formattedDate") %>
    </p>
    <div class="post-content">${post.content}</div>
    <br>
    <a href="${pageContext.request.contextPath}/edit-post?id=${post.id}" class="edit-btn">✏️ 編集する</a>
    <form action="<%= request.getContextPath() %>/delete-post" method="post">
        <input type="hidden" name="id" value="${post.id}">
        <button type="submit" class="delete-btn">🗑️ 削除</button>
    </form>

    <h3 class="view-subtitle">コメントを投稿</h3>

    <form action="${pageContext.request.contextPath}/add-comment" method="post">
        <!-- 投稿IDを隠して送信 -->
        <input type="hidden" name="postId" value="${post.id}"/>

        <!-- コメント内容 -->
        <textarea name="content" id="commentInput"
                  placeholder="コメント内容を入力してください…" rows="4"
                  maxlength="500" onclick="showExtraFields()" required></textarea>

        <!-- 名前 -->
        <div id="extraFields" class="extraFields">
            <input type="text" name="author" id="nameInput" placeholder="お名前" maxlength="50"
                   required/>

            <button type="button" class="close-btn" onclick="hideExtraFields()">Close</button>


            <button type="submit" class="submit-button">送信する</button>
        </div>
    </form>
    <br>

    <h3 class="view-subtitle">コメント一覧</h3>
    <c:if test="${not empty comments}">
        <ul class="comment-list">
            <c:forEach var="comment" items="${comments}">
                <li class="comment-item">
                    <p><strong><c:out value="${comment.author}"/></strong> さんより</p>
                    <p><c:out value="${comment.content}"/></p>
                </li>
                <form action="${pageContext.request.contextPath}/delete-comment" method="post"
                      onsubmit="return confirm('本当に削除しますか？');">
                    <input type="hidden" name="commentId" value="${comment.id}"/>
                    <input type="hidden" name="postId" value="${post.id}"/>
                    <button type="submit" class="delete-btn">🗑️ 削除</button>
                </form>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty comments}">
        <p>コメントはまだありません。</p>
    </c:if>
</div>
<script src="${pageContext.request.contextPath}/assets/js/form.js" defer></script>
</body>
</html>
