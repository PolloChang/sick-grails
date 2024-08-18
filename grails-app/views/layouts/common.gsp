<%--
  Created by IntelliJ IDEA.
  User: pollochang
  Date: 8/15/24
  Time: 12:21 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
  <g:layoutHead/>
</head>

<body>
<header>
    <a href="${createLink(controller: 'start' , action: 'index')}">Home</a>
    <a href="${createLink(controller: 'start' , action: 'filter')}">filter</a>
    <a href="${createLink(controller: 'start' , action: 'filter2')}">SQL Injection</a>
    <a href="${createLink(controller: 'start' , action: 'filter3')}">command Injection</a>
</header>
<main>
<h1>這是一個有漏洞的 Grails 試驗程式</h1>
<g:layoutBody/>
</main>
</body>
</html>