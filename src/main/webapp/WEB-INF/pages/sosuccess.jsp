<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/15 0015
  Time: 下午 03:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>多文件下载</title>
</head>
<body>
    <c:forEach items="list" var="li">
        <a href="/do_download?filename=${li.fileName}"><img src="/upload/${li.fileName}" style="height: 200px;width: 200px;"></a>
    </c:forEach>
</body>
</html>
