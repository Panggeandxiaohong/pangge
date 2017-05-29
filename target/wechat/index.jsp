<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>微信考试系统</title>
    <%@ include file="WEB-INF/views/common.jsp"%>
    <script type="text/javascript" src="/js/views/index.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:80px;background: url('/image/banner-pic.gif') no-repeat;background-size:cover;">
    <h2>微信考试系统</h2>
</div>
<div data-options="region:'west',split:true" style="width:150px;">
    <div class="easyui-accordion" fit="true">
        <div title="菜单">
            <div id="mm" class="easyui-menu" style="width:120px;">
                <div>New</div>
                <div>
                    <span>Open</span>
                    <div style="width:150px;">
                        <div><b>Word</b></div>
                        <div>Excel</div>
                        <div>PowerPoint</div>
                    </div>
                </div>
                <div data-options="iconCls:'icon-save'">Save</div>
                <div class="menu-sep"></div>
                <div>Exit</div>
            </div>
        </div>
        <div iconCls="icon-help" title="帮助"></div>
    </div>
</div>
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <div id="tabs">
        <div title="欢迎页" data-options="closable:true">
            <h2>欢迎使用本系统</h2>
        </div>
    </div>
</div>
<div data-options="region:'south'" style="height:25px;background: url('/image/banner-pic.gif') no-repeat;background-size:cover;">
    <center>版权说明</center>
</div>
</body>
</body>
</html>
