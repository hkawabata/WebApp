<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List,java.util.ArrayList"%>
<%
String name = "Taro";
int age = 20;
List<String> list = new ArrayList<String>();
list.add("a");
list.add("b");
list.add("c");
%>
<html>
    <head>
        <title>JSP Trial</title>
    </head>
    <body>
        <!-- このコメントは html ファイルのソースに出力される -->
        <%-- このコメントは html のソースにも出力されない --%>
        <%
        out.println("Hello JSP!");
        %>
        <br/>
        My name is <%= name %>. I am <%= age %> years old.

        <%for (int i = 0; i < 3; i++) { %>
        <p>繰り返し文<%= list.get(i) %></p>
        <% } %>

        <form action="/hello/world" method="get">
            Hello World のページへ：
            <input type="submit" value="ページ遷移">
        </form>
        <form action="/request/response" method="post">
            Request/Response のページへ：<br>
            ID: <input type="text" name="id">
            pass:<input type="text" name="pass">
            <input type="submit" value="ページ遷移">
        </form>
    </body>
</html>
