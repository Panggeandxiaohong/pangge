<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>微信考试系统</title>
    <%@ include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/subject.js"></script>
    <script type="text/javascript" src="/js/plugin/jquery-form.js"></script>
</head>
<body>
    <div id="subject_datagrid"></div>
    <%--新增/更新对话框--%>
    <div id="subject_dialog">
        <form id="subject_form"  enctype="multipart/form-data" method="post">
            <input type="hidden" name="id"/>
            <table align="center" style="margin-top: 15px">
               <tr>
                    <td>问题描述</td>
                    <td><textarea  id="question" onfocus="if(this.value == '请在这里输入题目！') this.value = ''" onblur="if(this.value =='') this.value = '请在这里输入题目！'" name="question"  rows="10" cols="50">请在这里输入题目！</textarea></td>
                </tr>
                <tr>
                    <td>类型</td>
                    <td><input id="subject_type" name="subject_type" class="easyui-combobox"/></td>
                </tr>
                <tr>
                    <td>多媒体类型</td>
                    <td><input id="media_type" name="media_type" class="easyui-combobox"/></td>
                </tr>
                <tr>
                    <td>分值</td>
                    <td><input type="text" name="score" class="easyui-numberbox"  data-options="min:0,max:10,precision:2,required:true" /></td>
                </tr>
                <tr>
                    <td>科目</td>
                    <td><input id="classes" name="classes" class="easyui-combobox"/></td>
                </tr>
                <tr>
                    <td>A答案</td>
                    <td><input type="text" name="answerA" class="easyui-validatebox"  data-options="required:true" /></td>
                </tr>
                <tr>
                    <td>B答案</td>
                    <td><input type="text" name="answerB" class="easyui-validatebox"  data-options="required:true" /></td>
                </tr>
                <tr>
                    <td>C答案</td>
                    <td><input type="text" name="answerC" class="easyui-validatebox"  data-options="required:true" /></td>
                </tr>
                <tr>
                    <td>D答案</td>
                    <td><input type="text" name="answerD" class="easyui-validatebox"  data-options="required:true" /></td>
                </tr>
                <tr>
                    <td>标准答案</td>
                    <td><input type="text" name="answer" class="easyui-validatebox"  data-options="required:true" />
                    </td>
                </tr>
                <tr>
                    <td>解释</td>
                    <td><input type="text" name="explain" class="easyui-validatebox"  data-options="required:true" />
                    </td>
                </tr>
                <tr>
                    <td>题目附件</td>
                    <td><input  id="myfile" type="file" name="myfiles"/></td></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="more_subject_dialog">
        <form id="more_subject_form"  enctype="multipart/form-data" method="post">
                    <input  id="subjects" type="file" name="subjects"/>
                    <input  id="more_myfiles" type="file" name="more_myfiles"/>
        </form>
    </div>
    <div id="subject_datagrid_tb">
        <div>
            <a id="subject_datagrid_save" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
            <a id="subject_datagrid_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
            <a id="subject_datagrid_delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="delete">删除</a>
            <a id="subject_datagrid_active" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="active">激活</a>
            <a id="subject_datagrid_upload" class="easyui-linkbutton" iconCls="icon-upload" plain="true" data-cmd="upload">上传</a>
            <a id="subject_datagrid_output" class="easyui-linkbutton" iconCls="icon-output" plain="true" data-cmd="output">导出</a>
            <a id="subject_datagrid_download" class="easyui-linkbutton" iconCls="icon-download" plain="true" data-cmd="download">下载模板</a>
            <a id="subject_datagrid_reload" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        </div>
        <div>
            关键字:<input type="text" name="keyWord"/>
            <input type="text" id="subject_type_combobox" class="easyui-combobox" name="subject_type_combobox"/>
            <input type="text" id="classes_combobox" class="easyui-combobox" name="classes_combobox"/>
            <input type="text" id="media_type_combobox" class="easyui-combobox" name="media_type_combobox"/>
            <input type="text" id="process_status_combobox" class="easyui-combobox" name="process_status_combobox"/>
            <a class="easyui-linkbutton" iconCls="icon-search" data-cmd="searchBtn">查询</a>
        </div>
    </div>
    <div id="subject_dialog_bt">
        <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="save">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">取消</a>
    </div>
    <div id="move_subject_dialog_bt">
        <a class="easyui-linkbutton" iconCls="icon-save" data-cmd="move_save">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">取消</a>
    </div>
</body>
</html>
