/**
 * 会员等级管理管理初始化
 */
var UserLevel = {
    id: "UserLevelTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserLevel.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '会员等级名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '折扣系数', field: 'discount', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserLevel.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserLevel.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员等级管理
 */
UserLevel.openAddUserLevel = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员等级管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userLevel/userLevel_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员等级管理详情
 */
UserLevel.openUserLevelDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员等级管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userLevel/userLevel_update/' + UserLevel.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员等级管理
 */
UserLevel.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/userLevel/delete", function (data) {
            Feng.success("删除成功!");
            UserLevel.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userLevelId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询会员等级管理列表
 */
UserLevel.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    UserLevel.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = UserLevel.initColumn();
    var table = new BSTable(UserLevel.id, "/userLevel/list", defaultColunms);
    table.setPaginationType("client");
    UserLevel.table = table.init();
});
