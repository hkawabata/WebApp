<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>MVC Trial (forward)</title>
    </head>
    <body>
        result:<br>
        <%= (String) request.getAttribute("attr") %>
    </body>
</html>