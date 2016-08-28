<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="jetty.sample.apps.mvc_trial.SampleBean" %>
<html>
    <head>
        <title>MVC Trial (redirect with application scope attribute)</title>
    </head>
    <body>
        result:<br>
        <% SampleBean bean = (SampleBean) application.getAttribute("attr"); %>
        <%= bean.getAfterString() %>
    </body>
</html>
