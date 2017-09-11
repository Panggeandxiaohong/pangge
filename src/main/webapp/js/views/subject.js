$(function () {
    var subjectDatagrid, subjectFormId, subject_dialog_bt, status, subjectDialog, subject_form, process_status_combobox, subject_datagrid_active, more_subject_dialog, more_subject_form, more_myfiles,
        subjectForm, myfile, classes, type, subject_type, media_type, question, subject_type_combobox, classes_combobox, media_type_combobox, subject_datagrid_upload, more_myfiles;
    status = $("#status");
    more_myfiles = $("#more_myfiles");
    subject_datagrid_upload = $("#subject_datagrid_upload");
    more_myfiles = $("#more_myfiles");
    more_subject_form = $("#more_subject_form");
    more_subject_dialog = $("#more_subject_dialog");
    question = $("#question");
    subject_type = $("#subject_type");
    media_type = $("#media_type");
    classes = $("#classes");
    myfile = $("#myfile");
    subjectDatagrid = $("#subject_datagrid");
    subjectDialog = $("#subject_dialog");
    subjectForm = $("#subject_form");
    subjectFormId = $("#subject_form [name='id']");
    subject_type_combobox = $("#subject_type_combobox");
    classes_combobox = $("#classes_combobox");
    media_type_combobox = $("#media_type_combobox");
    process_status_combobox = $("#process_status_combobox");
    subject_datagrid_active = $("#subject_datagrid_active");
    var cmdObj = {
        //高级查询
        searchBtn: function () {
            subjectDatagrid.datagrid("load", {
                "keyword": $("[name='keyWord']").val(),
                "subject_type_combobox": $("#subject_type_combobox").combobox("getValue"),
                "classes_combobox": $("#classes_combobox").combobox("getValue"),
                "media_type_combobox": $("#media_type_combobox").combobox("getValue"),
                "process_status_combobox": $("#process_status_combobox").combobox("getValue")
            })
        },
        //新增
        upload: function () {
            more_subject_form.form("reset");
            more_subject_dialog.dialog("open");
            more_subject_dialog.dialog("setTitle", "新增");
        },
        //新增
        add: function () {
            subjectForm.form("reset");
            classes.combobox("select", 1);
            subject_type.combobox("select", 1);
            media_type.combobox("select", "text");
            subjectDialog.dialog("open");
            subjectDialog.dialog("setTitle", "新增");
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
                if (rowData.subjectType) {
                    rowData["subjectType.typeName"] = rowData.subjectType.typeName;
                }
                subjectForm.form("load", {
                    id: rowData.id,
                    question: rowData.question,
                    subject_type: rowData.subjectType.id,
                    score: rowData.score,
                    classes: rowData.classes.id,
                    answerA: rowData.answerA,
                    answerB: rowData.answerB,
                    answerC: rowData.answerC,
                    answerD: rowData.answerD,
                    media_type: rowData.mediaType == null ? "text" : rowData.mediaType,
                    answer: rowData.answer,
                    explain: rowData.explain
                });
                if (rowData.subjectType.id != 1) {
                    $("[name='answerA']").validatebox({required: false});
                    $("[name='answerB']").validatebox({required: false});
                    $("[name='answerC']").validatebox({required: false});
                    $("[name='answerD']").validatebox({required: false});
                    $("[name='answerA']").attr({"disabled": "disabled"});
                    $("[name='answerB']").attr({"disabled": "disabled"});
                    $("[name='answerC']").attr({"disabled": "disabled"});
                    $("[name='answerD']").attr({"disabled": "disabled"});
                }
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的题目", "info");
            }
        },
        //删除
        delete: function () {
            var rowData = subjectDatagrid.datagrid("getChecked");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定要删除此题？", function (r) {
                    if (r) {
                        var ids = new Array();
                        for (var i = 0; i < rowData.length; i++) {
                            ids[i] = rowData[i].id;
                        }
                        var params = {
                            ids: ids,
                            type: "Deleted"
                        };
                        $.post("/subject/delete.do", params, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    //刷新数据表格
                                    subjectDatagrid.datagrid("reload");
                                });
                            } else {
                                subjectDatagrid.datagrid("reload");
                                $.messager.alert("温馨提示", data.msg, "info");
                            }
                        }, "json")
                    }
                })
            } else {
                $.messager.alert("温馨提示", "请选择需要删除的题目", "info");
            }
        },
        //激活
        active: function () {
            var rowData = subjectDatagrid.datagrid("getChecked");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定要激活此题？", function (r) {
                    if (r) {
                        var ids = new Array();
                        for (var i = 0; i < rowData.length; i++) {
                            ids[i] = rowData[i].id;
                        }
                        var params = {
                            ids: ids,
                            type: "Active"
                        };
                        $.post("/subject/delete.do", params, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    //刷新数据表格
                                    subjectDatagrid.datagrid("reload");
                                });
                            } else {
                                subjectDatagrid.datagrid("reload");
                                $.messager.alert("温馨提示", data.msg, "info");
                            }
                        }, "json")
                    }
                })
            } else {
                $.messager.alert("温馨提示", "请选择需要激活的题目", "info");
            }
        },
        //科目格式化
        classesFormatr: function (value, row, index) {
            return value ? value.className : value;
        },
        //题目类型格式化
        typeFormatr: function (value, row, index) {
            return value ? value.typeName : value;
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
            more_subject_dialog.dialog("close");
        },
        //保存
        save: function () {
            if (question.val() == "") {
                $.messager.alert("温馨提示", "请输入题目！", "error");
            }
            var url;
            if (subjectFormId.val()) {
                url = "subject/update.do";
            } else {
                url = "subject/save.do";
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
                        //关闭对话框
                        subjectDialog.dialog("close");
                        //刷新数据表格
                        subjectDatagrid.datagrid("load");
                    }
                }
            })
        },
        move_save: function () {
            if (more_myfiles.val() == "") {
                $.messager.alert("温馨提示", "请上传文件！", "error");
            }
            more_subject_form.form("submit", {
                url: "/subject/upload.do",
                contentType: false,
                processData: false,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            //关闭对话框
                            more_subject_dialog.dialog("close");
                            //刷新数据表格
                            subjectDatagrid.datagrid("load");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "info");
                        //关闭对话框
                        more_subject_dialog.dialog("close");
                        //刷新数据表格
                        subjectDatagrid.datagrid("load");
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
        url: "/subject/list.do",
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        selectOnCheck: true,
        striped: true,
        pageNumber: 1,
        // pageSize: 10,
        checkOnSelect: true,
        // pageList: [1, 5, 10, 20, 40, 50],
        toolbar: "#subject_datagrid_tb",
        singleSelect: false,
        columns: [
            [
                {field: 'id', align: 'center', title: '', checkbox: true},
                {field: 'question', align: 'center', title: '问题', width: 1},
                {field: 'subjectType', align: 'center', title: '题目类型', width: 1, formatter: cmdObj.typeFormatr},
                {field: 'score', align: 'center', title: '分值', width: 1},
                {field: 'classes', align: 'center', title: '科目', width: 1, formatter: cmdObj.classesFormatr},
                {field: 'answerA', align: 'center', title: 'A答案', width: 1},
                {field: 'answerB', align: 'center', title: 'B答案', width: 1},
                {field: 'answerC', align: 'center', title: 'C答案', width: 1},
                {field: 'answerD', align: 'center', title: 'D答案', width: 1},
                {field: 'adduser', align: 'center', title: '管理员', width: 1, formatter: cmdObj.adminFormatr},
                {field: 'answer', align: 'center', title: '标准答案', width: 1},
                {field: 'explain', align: 'center', title: '解释', width: 1},
                {field: 'url', align: 'center', title: '图片地址', width: 1},
                {field: 'mediaType', align: 'center', title: '媒体类型', width: 1, formatter: cmdObj.mediaTypeFormatr},
                {field: 'processStatus', align: 'center', title: '试题状态', width: 1},
            ]
        ]
    });
    //设置分页控件
    var p = $('#subject_datagrid').datagrid('getPager');
    $(p).pagination({
        // pageSize: 10,//每页显示的记录条数，默认为10
        // pageList: [5, 10, 15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
        onSelectPage: function (pageNumber, pageSize) {
            $(this).pagination('loading');
            alert('pageNumber:' + pageNumber + ',pageSize:' + pageSize);
            $(this).pagination('loaded');
        }

    });
    classes.combobox({
        url: '/classes_getclasses.do',
        valueField: 'id',
        textField: 'className',
        // value:"数学",
        panelHeight: 'auto'
    });
    classes_combobox.combobox({
        url: '/classes_getclasses.do',
        valueField: 'id',
        textField: 'className',
        // value:"数学",
        panelHeight: 'auto'
    });
    subject_type_combobox.combobox({
        url: '/type_list.do',
        valueField: 'id',
        textField: 'typeName',
        // value:"数学",
        panelHeight: 'auto'
    });
    subject_type.combobox({
        url: '/type_list.do',
        valueField: 'id',
        textField: 'typeName',
        // value:"数学",
        panelHeight: 'auto',
        onSelect: function (record) {
            if (record.id == 1) {
                $("[name='answerA']").removeAttr("disabled");
                $("[name='answerB']").removeAttr("disabled");
                $("[name='answerC']").removeAttr("disabled");
                $("[name='answerD']").removeAttr("disabled");
                $("[name='answerA']").validatebox({required: true});
                $("[name='answerB']").validatebox({required: true});
                $("[name='answerC']").validatebox({required: true});
                $("[name='answerD']").validatebox({required: true});
            } else if (record.id == 2) {
                $("[name='answerA']").validatebox({required: false});
                $("[name='answerB']").validatebox({required: false});
                $("[name='answerC']").validatebox({required: false});
                $("[name='answerD']").validatebox({required: false});
                $("[name='answerA']").attr({"disabled": "disabled"});
                $("[name='answerB']").attr({"disabled": "disabled"});
                $("[name='answerC']").attr({"disabled": "disabled"});
                $("[name='answerD']").attr({"disabled": "disabled"});
            } else if (record.id == 3) {
                $("[name='answerA']").validatebox({required: false});
                $("[name='answerB']").validatebox({required: false});
                $("[name='answerC']").validatebox({required: false});
                $("[name='answerD']").validatebox({required: false});
                $("[name='answerA']").attr({"disabled": "disabled"});
                $("[name='answerB']").attr({"disabled": "disabled"});
                $("[name='answerC']").attr({"disabled": "disabled"});
                $("[name='answerD']").attr({"disabled": "disabled"});
            }
        }
    });
    media_type.combobox({
        valueField: 'value',
        textField: 'value',
        // value:"数学",
        panelHeight: 'auto',
        data: [{
            value: 'text',
        }, {
            value: 'image'
        }, {
            value: 'voice'
        }, {
            value: 'video'
        }]
    }),
        media_type_combobox.combobox({
            valueField: 'value',
            textField: 'value',
            // value:"数学",
            panelHeight: 'auto',
            data: [{
                value: 'text',
            }, {
                value: 'image'
            }, {
                value: 'voice'
            }, {
                value: 'video'
            }]
        }),
        process_status_combobox.combobox({
            valueField: 'value',
            textField: 'value',
            // value:"数学",
            panelHeight: 'auto',
            data: [{
                value: 'Active',
            }, {
                value: 'Deleted'
            }]
        }),
        //新增
        subjectDialog.dialog({
            width: 500,
            height: 550,
            buttons: "#subject_dialog_bt",
            closed: true
        });
    //新增
    more_subject_dialog.dialog({
        width: 300,
        height: 400,
        buttons: "#move_subject_dialog_bt",
        closed: true
    });
});
function textareaClick() {
    if ($("#question").val() == "") {
        $("#question").val("请在这里输入题目！");
    } else if ($("#question").val() == "请在这里输入题目！") {
        $("#question").val("");
    }
}