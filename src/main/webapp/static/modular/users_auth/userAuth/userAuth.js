/**
 * 第三方授权管理管理初始化
 */
var UserAuth = {
    id: "UserAuthTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserAuth.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户ID', field: 'user_id', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '授权类型', field: 'identityType', visible: true, align: 'center', valign: 'middle'},
            {title: '用户唯一标识', field: 'token', visible: true, align: 'center', valign: 'middle'},
            {title: '身份', field: 'userAuth', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserAuth.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserAuth.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加第三方授权管理
 */
UserAuth.openAddUserAuth = function () {
    var index = layer.open({
        type: 2,
        title: '添加第三方授权管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userAuth/userAuth_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看第三方授权管理详情
 */
UserAuth.openUserAuthDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '第三方授权管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userAuth/userAuth_update/' + UserAuth.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除第三方授权管理
 */
UserAuth.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/userAuth/delete", function (data) {
            Feng.success("删除成功!");
            UserAuth.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userAuthId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询第三方授权管理列表
 */
UserAuth.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    UserAuth.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = UserAuth.initColumn();
    var table = new BSTable(UserAuth.id, "/userAuth/list", defaultColunms);
    table.setPaginationType("client");
    UserAuth.table = table.init();
});
