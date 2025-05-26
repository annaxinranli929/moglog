<%--
  Created by IntelliJ IDEA.
  User: annax
  Date: 2025/05/26
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>500 Internal Server Error</title>
  <style>
    body {
      text-align: center;
      padding: 5rem;
      font-family: "Noto Sans JP", sans-serif;
      background-color: #e6d0b0;
      color:#2c2215;
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
<body style="text-align:center; padding: 2rem;">
<h1>サーバーエラーが発生しました</h1>
<p>申し訳ありません。しばらくしてからもう一度お試しください。</p>
<a href="${pageContext.request.contextPath}/posts">トップに戻る</a>
</body>
</html>

