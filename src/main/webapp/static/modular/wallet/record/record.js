/**
 * 钱包流水记录管理初始化
 */
var Record = {
    id: "RecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Record.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户ID', field: 'user_id', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'tyPe', visible: true, align: 'center', valign: 'middle'},
            {title: '订单号', field: 'order_number', visible: true, align: 'center', valign: 'middle'},
            {title: '金额', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '操作后金额', field: 'acc_balance', visible: true, align: 'center', valign: 'middle'},
            {title: '积分', field: 'score', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Record.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Record.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加钱包流水记录
 */
Record.openAddRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加钱包流水记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/record/record_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看钱包流水记录详情
 */
Record.openRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '钱包流水记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/record/record_update/' + Record.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除钱包流水记录
 */
Record.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/record/delete", function (data) {
            Feng.success("删除成功!");
            Record.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("recordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询钱包流水记录列表
 */
Record.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    Record.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Record.initColumn();
    var table = new BSTable(Record.id, "/record/list", defaultColunms);
    table.setPaginationType("client");
    Record.table = table.init();
});
