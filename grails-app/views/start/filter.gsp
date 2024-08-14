<%--
  Created by IntelliJ IDEA.
  User: pollochang
  Date: 8/15/24
  Time: 12:24 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta name="layout" content="common"/>
</head>

<body>
<div>
  <form action="${createLink(controller: 'start', action: 'filter')}" method="post">
    <div>
      <span>strings</span>
      <input name="strings" type="text" />
      <input type="submit" value="查詢">

    </div>
    <div>
      ${rows}
    </div>
  </form>
</div>
</body>
</html>