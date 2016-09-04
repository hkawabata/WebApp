<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List,java.util.ArrayList" %>
<%@ page import="java.util.List,java.util.List" %>
<%@ page import="jetty.sample.apps.keijiban.bean.UserInfo" %>
<%@ page import="jetty.sample.apps.keijiban.bean.Post" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
List<Post> posts = (List<Post>) application.getAttribute("posts");
if (posts == null) {
  posts = new ArrayList<>();
  application.setAttribute("posts", posts);
}
%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <title>掲示板</title>
  </head>
  <body>

    <h1>けいじばーん！</h1>
    <%
    String notSelected = (String) session.getAttribute("loginError");
    if(notSelected != null) {
    %>
    <font color="red">${loginErrMsg}</font>
    <%
    session.removeAttribute("loginError");
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

    <c:forEach var="post" items="${posts}">
        <hr size="2" color="gray" width="90%" align="left">
        <c:out value="${post.date}" /> <b><c:out value="${post.user}" /></b><br>
        <c:out value="${post.text}" />
    </c:forEach>

    <%
    }
    %>

    <%-- footer.jsp を呼び出し--%>
    <jsp:include page="/keijiban/footer.jsp" />
  </body>
</html>
