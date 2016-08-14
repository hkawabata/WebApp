<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="jetty.sample.apps.mvc_trial.SampleBean" %>
<html>
    <head>
        <title>MVC Trial (forward)</title>
    </head>
    <body>
        result:<br>
        <% SampleBean bean = (SampleBean) request.getAttribute("attr"); %>
        <%= bean.getAfterString() %>
    </body>
</html>