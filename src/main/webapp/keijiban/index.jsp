<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List,java.util.ArrayList" %>
<%@ page import="java.util.List,java.util.List" %>
<%@ page import="jetty.sample.apps.keijiban.bean.UserInfo" %>
<%@ page import="jetty.sample.apps.keijiban.bean.Post" %>
<%
List<Post> posts = (List<Post>) application.getAttribute("posts");
if (posts == null) {
  posts = new ArrayList<>();
  application.setAttribute("posts", posts);
}
%>
<html lang="ja">
  <head>
    <title>掲示板</title>
  </head>
  <body>

    <h1>けいじばーん！</h1>

    <%
    String notSelected = (String) session.getAttribute("badParameter");
    if(notSelected != null) {
    %>
    <font color="red">ユーザ名あるいはパスワードが間違っています</font>
    <%
    }
    %>

    <%
    UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
    if (userInfo == null) {
    %>
    <h2>ログイン</h2>
    <form action="keijiban/login" method="get">
        ユーザ名：<input type="text" name="name"><br>
        パスワード：<input type="password" name="pass"><br>
        <input type="submit">
    </form>
    <%
    } else {
    %>

    <form action="keijiban/post" method="post">
        <input type="text" name="post">
        <input type="submit" value="投稿">
    </form>

    <h2>これまでの投稿</h2>
    <%
    for (Post post: posts){
    %>
    <%= post.getDate() %>: <b><%= post.getUser() %></b><br>
    <%= post.getText() %><br>
    <hr size="2" color="gray" width="90%" align="left">
    <%
    }
    %>

    <%
    }
    %>

    <%-- footer.jsp を呼び出し--%>
    <jsp:include page="/keijiban/footer.jsp" />
  </body>
</html>
