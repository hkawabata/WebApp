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
    <font color="red">正しくパラメータを設定してください</font>
    <%
    }
    %>

    <h2>GET によるフォワード・リダイレクト</h2>
    <form action="/mvc_trial/controller" method="get">
        <input type="text" name="text"><br>
        <input type="radio" name="forwardOrRedirect" value="forward">forward
        <input type="radio" name="forwardOrRedirect" value="redirect">redirect
        <input type="radio" name="forwardOrRedirect" value="redirect_ss">redirect_ss<br>
        <input type="submit">
    </form>

    <h2>POST によるフォワード・リダイレクト</h2>
    <form action="/mvc_trial/controller" method="post">
        <input type="text" name="text"><br>
        <input type="radio" name="forwardOrRedirect" value="forward">forward
        <input type="radio" name="forwardOrRedirect" value="redirect">redirect
        <input type="radio" name="forwardOrRedirect" value="redirect_ss">redirect_ss<br>
        <input type="submit">
    </form>

    </body>
</html>