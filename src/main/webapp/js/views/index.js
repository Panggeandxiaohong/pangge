$(function () {
    $("#menu").tree({
        url: '/queryForMenu',
        onClick: function (node) {
            node.attributes = $.parseJSON(node.attributes);
            var exist = $("#tabs").tabs("exists", node.text);
            if (exist) {
                $("#tabs").tabs("select", node.text);
            } else {
                $("#tabs").tabs("add", {
                    title: node.text,
                    content: '<iframe src ="' + node.attributes.url + '" style="width:100%;height:99%" frameborder=0></iframe>',
                    closable: true
                });
            }
        }
    });
    $("#tabs").tabs({
        fit: true

    });
});