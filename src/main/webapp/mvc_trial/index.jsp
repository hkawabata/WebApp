<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html lang="ja">
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    </head>
    <body>
    <%
    String notSelected = request.getParameter("notSelected");
    if(notSelected != null) {
    %>
    <font color="red">forward か redirect を選択してください！</font>
    <%
    }
    %>

    <h3>GET によるフォワード・リダイレクト</h3>
    <form action="/mvc_trial/controller" method="get">
        <input type="radio" name="forwardOrRedirect" value="forward">forward
        <input type="radio" name="forwardOrRedirect" value="redirect">redirect
        <input type="submit">
    </form>

    <h3>POST によるフォワード・リダイレクト</h3>
    <form action="/mvc_trial/controller" method="post">
        <input type="radio" name="forwardOrRedirect" value="forward">forward
        <input type="radio" name="forwardOrRedirect" value="redirect">redirect
        <input type="submit">
    </form>

    </body>
</html>