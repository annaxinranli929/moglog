<%--
  Created by IntelliJ IDEA.
  User: annax
  Date: 2025/05/26
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>404 Not Found</title>
  <style>
    body {
      text-align: center;
      padding: 5rem;
      font-family: "Noto Sans JP", sans-serif;
      background-color: #e6d0b0;
      color:#2c2215;

    }
    .error-img {
      max-width: 500px;
      margin-bottom: 1rem;
    }

    a {
      display: inline-block;
      margin-top: 2rem;
      padding: 0.5rem 1.2rem;
      background-color: #ffcc70;
      color: #333;
      text-decoration: none;
      border-radius: 8px;
    }

    a:hover {
      background-color: #f0b04c;
    }
  </style>
</head>
<body class="error">
<img src="${pageContext.request.contextPath}/img/404.png" alt="エラー" class="error-img" />
<h1>ページが見つかりません</h1>
<p>指定されたページは存在しないか、移動されました。</p>
<a href="${pageContext.request.contextPath}/posts">トップに戻る</a>
</body>
</html>
