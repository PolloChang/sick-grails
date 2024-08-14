<%--
  Created by IntelliJ IDEA.
  User: pollochang
  Date: 8/14/24
  Time: 11:28 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="common"/>
</head>

<body>
<div>
    <h2>新增資料</h2>
    <form action="${createLink(controller: 'start', action: 'createUseDomain')}" method="post">
        <div>
            <span>strings</span>
            <input name="ex100.strings" type="text" />
            <input type="submit" value="儲存">
        </div>
    </form>
</div>

</body>
</html>