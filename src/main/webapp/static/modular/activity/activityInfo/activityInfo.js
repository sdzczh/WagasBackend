/**
 * 用户活动管理管理初始化
 */
var ActivityInfo = {
    id: "ActivityInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ActivityInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '活动名称', field: 'goods_name', visible: true, align: 'center', valign: 'middle'},
            {title: '现有商品数量', field: 'now_number', visible: true, align: 'center', valign: 'middle'},
            {title: '状态 0未完成 1可领取', field: 'State', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ActivityInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ActivityInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户活动管理
 */
ActivityInfo.openAddActivityInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户活动管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/activityInfo/activityInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户活动管理详情
 */
ActivityInfo.openActivityInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户活动管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/activityInfo/activityInfo_update/' + ActivityInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户活动管理
 */
ActivityInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/activityInfo/delete", function (data) {
            Feng.success("删除成功!");
            ActivityInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("activityInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户活动管理列表
 */
ActivityInfo.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    queryData['act_name'] = $("#act_name").val();
    ActivityInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ActivityInfo.initColumn();
    var table = new BSTable(ActivityInfo.id, "/activityInfo/list", defaultColunms);
    table.setPaginationType("client");
    ActivityInfo.table = table.init();
});
