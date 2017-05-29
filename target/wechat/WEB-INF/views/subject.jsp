<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>微信考试系统</title>
    <%@ include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/subject.js"></script>
</head>
<body>
    <div id="subject_datagrid"></div>
    <%--新增/更新对话框--%>
    <div id="subject_dialog">
        <form id="subject_form" method="post">
            <input type="hidden" name="id"/>
            <table align="center" style="margin-top: 15px">
                <tr>
                    <td>问题描述</td>
                    <td><input type="text" name="question"/></td>
                </tr>
                <tr>
                    <td>类型</td>
                    <td><input type="text" name="type"/></td>
                </tr>
                <tr>
                    <td>分值</td>
                    <td><input type="text" name="score"/>

                    </td>
                </tr>
                <tr>
                    <td>科目</td>
                    <td><input type="classes" name="classes" class="easyui-combobox"/></td>
                </tr>
                <tr>
                    <td>A答案</td>
                    <td><input type="text" name="answerA"/></td>
                </tr>
                <tr>
                    <td>B答案</td>
                    <td><input type="text" name="answerB"/></td>
                </tr>
                <tr>
                    <td>C答案</td>
                    <td><input type="text" name="answerC"/></td>
                </tr>
                <tr>
                    <td>D答案</td>
                    <td><input type="text" name="answerD"/></td>
                </tr>
                <tr>
                    <td>标准答案</td>
                    <td><input type="text" name="answer"/>
                    </td>
                </tr>
                <tr>
                    <td>解释</td>
                    <td><input type="text" name="explain"/>
                    </td>
                </tr>
                <tr>
                    <td>图片</td>
                    <td><div id="swfu-placeholder"><input type="button" onclick="swfu.startUpload();" value="上传" />
                </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="subject_datagrid_tb">
        <div>
            <a id="subject_datagrid_save" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
            <a id="subject_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
            <a id="subject_datagrid_upload" class="easyui-linkbutton" iconCls="icon-upload" plain="true" data-cmd="upload">上传</a>
            <a id="subject_datagrid_output" class="easyui-linkbutton" iconCls="icon-output" plain="true" data-cmd="output">导出</a>
            <a id="subject_datagrid_download" class="easyui-linkbutton" iconCls="icon-download" plain="true" data-cmd="download">下载模板</a>
            <a id="subject_datagrid_reload" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        </div>
        <div>
            关键字:<input type="text" name="keyWord"/>
            <input type="text" id="status" class="easyui-combobox" name="status"/>
            <a class="easyui-linkbutton" iconCls="icon-search" data-cmd="searchBtn">查询</a>
        </div>
    </div>
    <div id="subject_dialog_bt">
        <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="save">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">取消</a>
    </div>
</body>
</html>
