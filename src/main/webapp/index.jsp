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
        <meta charset="UTF-8"/>
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
    </body>
</html>
