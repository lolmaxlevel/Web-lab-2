<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="./src/img/icon.jpg">
    <link rel="stylesheet" type="text/css" href="./src/css/style.css">
    <title>error occurred</title>
</head>
<body>
<%response.setStatus(400);%>
<h1 class="rainbow-text"><%=request.getAttribute("error")==null?"нет ошибок =)":request.getAttribute("error")%></h1>
</body>
</html>
