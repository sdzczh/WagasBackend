/**
 * 活动记录管理初始化
 */
var ActivityRecord = {
    id: "ActivityRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ActivityRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '活动名称', field: 'goods_name', visible: true, align: 'center', valign: 'middle'},
            {title: '购买商品数量', field: 'number', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'Type', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ActivityRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ActivityRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加活动记录
 */
ActivityRecord.openAddActivityRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加活动记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/activityRecord/activityRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看活动记录详情
 */
ActivityRecord.openActivityRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '活动记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/activityRecord/activityRecord_update/' + ActivityRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除活动记录
 */
ActivityRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/activityRecord/delete", function (data) {
            Feng.success("删除成功!");
            ActivityRecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("activityRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询活动记录列表
 */
ActivityRecord.search = function () {
    var queryData = {};
    queryData['act_name'] = $("#act_name").val();
    queryData['phone'] = $("#phone").val();
    ActivityRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ActivityRecord.initColumn();
    var table = new BSTable(ActivityRecord.id, "/activityRecord/list", defaultColunms);
    table.setPaginationType("client");
    ActivityRecord.table = table.init();
});
