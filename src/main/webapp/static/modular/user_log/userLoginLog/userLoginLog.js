/**
 * 会员登录日志管理初始化
 */
var UserLoginLog = {
    id: "UserLoginLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserLoginLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户ID', field: 'user_id', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '是否成功', field: 'success', visible: true, align: 'center', valign: 'middle'},
            {title: 'IP', field: 'ip', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserLoginLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserLoginLog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员登录日志
 */
UserLoginLog.openAddUserLoginLog = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员登录日志',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userLoginLog/userLoginLog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员登录日志详情
 */
UserLoginLog.openUserLoginLogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员登录日志详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userLoginLog/userLoginLog_update/' + UserLoginLog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员登录日志
 */
UserLoginLog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/userLoginLog/delete", function (data) {
            Feng.success("删除成功!");
            UserLoginLog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userLoginLogId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询会员登录日志列表
 */
UserLoginLog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    UserLoginLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = UserLoginLog.initColumn();
    var table = new BSTable(UserLoginLog.id, "/userLoginLog/list", defaultColunms);
    table.setPaginationType("client");
    UserLoginLog.table = table.init();
});
