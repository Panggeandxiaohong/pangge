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
    <title>${subject.question}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <META name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <META name="apple-mobile-web-app-capable" content="yes">
    <META name="apple-mobile-web-app-status-bar-style" content="black">
    <META name="format-detection" content="telephone=no">
    <META name="GENERATOR" content="MSHTML 10.00.9200.16578"></HEAD>
</head>
<body>
<c:if test="${subject.mediaType eq 'video'}">
    <video width="320" height="240" controls="controls">
        <source src="${subject.url}" type="video/mp4" />
    </video>
</c:if>
<c:if test="${subject.mediaType eq 'voice'}">
    <audio id="musicfx" loop="loop" autoplay="autoplay">
        <source src="${subject.url}" type="audio/mpeg">
    </audio>
</c:if>
<c:if test="${subject.mediaType eq 'image'}">
    <img src="${subject.url}">
</c:if>
${subject.question}<br/>
${subject.answerA}<br/>
${subject.answerB}<br/>
${subject.answerC}<br/>
${subject.answerD}<br/>
</body>
</html>
