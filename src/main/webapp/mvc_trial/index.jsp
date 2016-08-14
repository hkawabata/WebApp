<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html lang="ja">
    <head>
        <title>MVC Trial</title>
    </head>
    <body>

    <h1>フォワード・リダイレクト</h1>

    <%
    String notSelected = request.getParameter("badParameter");
    if(notSelected != null) {
    %>
    <font color="red">forward か redirect を選択してください！</font>
    <%
    }
    %>

    <h2>GET によるフォワード・リダイレクト</h2>
    <form action="/mvc_trial/controller" method="get">
        <input type="radio" name="forwardOrRedirect" value="forward">forward
        <input type="radio" name="forwardOrRedirect" value="redirect">redirect
        <input type="submit">
    </form>

    <h2>POST によるフォワード・リダイレクト</h2>
    <form action="/mvc_trial/controller" method="post">
        <input type="radio" name="forwardOrRedirect" value="forward">forward
        <input type="radio" name="forwardOrRedirect" value="redirect">redirect
        <input type="submit">
    </form>

    </body>
</html>