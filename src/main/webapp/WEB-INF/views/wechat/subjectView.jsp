<%--
  Created by IntelliJ IDEA.
  User: jie34
  Date: 2017/9/23
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${type}=====${src}</title>
</head>
<body>
<c:if test="${type='video'}">
    <video width="320" height="240" controls="controls">
        <source src="${src}" type="video/mp4" />
    </video>
</c:if>
<c:if test="${type='voice'}">
    <audio id="musicfx" loop="loop" autoplay="autoplay">
        <source src="${src}" type="audio/mpeg">
    </audio>
</c:if>
</body>
</html>
