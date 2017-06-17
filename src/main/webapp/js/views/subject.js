$(function () {
    var subjectDatagrid,subjectFormId,subject_dialog_bt,status,subjectDialog,subject_form,subjectForm,myfile;
    status = $("#status");
    myfile = $("#myfile");
    subjectDatagrid=$("#subject_datagrid");
    subjectDialog=$("#subject_dialog");
    subjectForm=$("#subject_form");
    subjectFormId = $("#subject_form [name='id']");
    var cmdObj = {
        //高级查询
        searchBtn: function () {
            subjectDatagrid.datagrid("load", {
                "keyword": $("[name='keyWord']").val(),
                "status": $("#status").combobox("getValue")
            })
        },
        //新增
        add: function () {
            subjectDialog.dialog("open");
            subjectDialog.dialog("setTitle", "新增");
            subjectForm.form("clear");
        },
        //编辑
        edit: function () {
            var rowData = subjectDatagrid.datagrid("getSelected");
            if (rowData) {
                subjectDialog.dialog("open");
                subjectDialog.dialog("setTitle", "编辑");
                subjectForm.form("clear");
                /*//特殊属性的处理*/
                if (rowData.classes) {
                    rowData["classes.className"] = rowData.classes.className;
                }
                if (rowData.type=="choice") {
                    rowData["type"] = "选择题";
                }
                if (rowData.type=="empty") {
                    rowData["type"] = "填空题";
                }
                if (rowData.type=="judge") {
                    rowData["type"] = "判断题";
                }
                alert(rowData.classes.className);
                subjectForm.form("load", {
                    question:rowData.question,
                    type:rowData.type,
                    score:rowData.score,
                    classes:rowData.classes.className,
                    answerA:rowData.answerA,
                    answerB:rowData.answerB,
                    answerC:rowData.answerC,
                    answerD:rowData.answerD,
                    mediaType:rowData.mediaType?"text":rowData.mediaType,
                    answer:rowData.answer,
                    explain:rowData.explain
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的题目", "info");
            }
        },
        //删除
        delBtn: function () {
            var rowData = subjectDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定要删除此题？", function (r) {
                    if (r) {
                        $.get("/subject_delete?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    //刷新数据表格
                                    subjectDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "info");
                            }
                        }, "json")
                    }
                })
            } else {
                $.messager.alert("温馨提示", "请选择需要删除的题目", "info");
            }
        },
        //科目格式化
        classesFormatr: function (value, row, index) {
            return value ? value.className : value;
        },
        //管理员格式化
        adminFormatr: function (value, row, index) {
            return value ? value.name : value;
        },
        //媒体类型格式化
        mediaTypeFormatr: function (value, row, index) {
            return value ? value : "text";
        },
        //刷新
        reload: function () {
            subjectDatagrid.datagrid("reload");
        },
        //关闭窗口
        cancel: function () {
            subjectDialog.dialog("close");
        },
        //保存
        save: function () {

            var url;
            if (subjectFormId.val()) {
                url = "subjectomer_update.do";
            } else {
                url = "subjectomer_save.do";
            }
            subjectForm.form("submit", {
                 url: url,
                 contentType: false,
                 processData: false,
                 success: function (data) {
                     data = $.parseJSON(data);
                     if (data.success) {
                         $.messager.alert("温馨提示", data.msg, "info", function () {
                             //关闭对话框
                             subjectDialog.dialog("close");
                             //刷新数据表格
                             subjectDatagrid.datagrid("load");
                         });
                     } else {
                         $.messager.alert("温馨提示", data.msg, "info");
                     }
                 }
             })
        },
    };
    //给所有按钮绑定事件
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });
    //表格
    subjectDatagrid.datagrid({
        url: "/subject_list.do",
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        pageList: [1, 10, 20, 30, 40, 50],
        toolbar: "#subject_datagrid_tb",
        singleSelect: true,
        columns: [
            [
                {field: 'question', align: 'center', title: '问题', width: 1},
                {field: 'type', align: 'center', title: '题目类型', width: 1},
                {field: 'score', align: 'center', title: '分值', width: 1},
                {field: 'classes', align: 'center', title: '科目', width: 1,formatter:cmdObj.classesFormatr},
                {field: 'answerA', align: 'center', title: 'A答案', width: 1},
                {field: 'answerB', align: 'center', title: 'B答案', width: 1},
                {field: 'answerC', align: 'center', title: 'C答案', width: 1},
                {field: 'answerD', align: 'center', title: 'D答案', width: 1},
                {field: 'adduser', align: 'center', title: '管理员', width: 1, formatter: cmdObj.adminFormatr},
                {field: 'answer', align: 'center', title: '标准答案', width: 1},
                {field: 'explain', align: 'center', title: '解释', width: 1},
                {field: 'url', align: 'center', title: '图片地址', width: 1},
                {field: 'mediaType', align: 'center', title: '媒体类型', width: 1,formatter: cmdObj.mediaTypeFormatr},
                {field: 'addtime', align: 'center', title: '添加时间', width: 1},
            ]
        ]
    });
    //新增
    subjectDialog.dialog({
        width: 350,
        height: 400,
        buttons: "#subject_dialog_bt",
        closed: true
    });
});