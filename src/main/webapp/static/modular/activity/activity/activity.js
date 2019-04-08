/**
 * 活动信息管理管理初始化
 */
var Activity = {
    id: "ActivityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Activity.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '商品名称', field: 'goodsName', visible: true, align: 'center', valign: 'middle'},
            {title: '活动描述', field: 'desc', visible: true, align: 'center', valign: 'middle'},
            {title: '目标商品数量', field: 'aimNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '赠送数量', field: 'giveNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'State', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Activity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Activity.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加活动信息管理
 */
Activity.openAddActivity = function () {
    var index = layer.open({
        type: 2,
        title: '添加活动信息管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/activity/activity_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看活动信息管理详情
 */
Activity.openActivityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '活动信息管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/activity/activity_update/' + Activity.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除活动信息管理
 */
Activity.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/activity/delete", function (data) {
            Feng.success("删除成功!");
            Activity.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("activityId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询活动信息管理列表
 */
Activity.search = function () {
    var queryData = {};
    queryData['goods_name'] = $("#goods_name").val();
    Activity.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Activity.initColumn();
    var table = new BSTable(Activity.id, "/activity/list", defaultColunms);
    table.setPaginationType("client");
    Activity.table = table.init();
});
