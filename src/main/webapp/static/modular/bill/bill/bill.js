/**
 * 账单管理管理初始化
 */
var Bill = {
    id: "BillTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Bill.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '操作类型', field: 'Type', visible: true, align: 'center', valign: 'middle'},
            {title: '操作数值', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '操作后数值', field: 'acc_amount', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Bill.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Bill.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加账单管理
 */
Bill.openAddBill = function () {
    var index = layer.open({
        type: 2,
        title: '添加账单管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bill/bill_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看账单管理详情
 */
Bill.openBillDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '账单管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bill/bill_update/' + Bill.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除账单管理
 */
Bill.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bill/delete", function (data) {
            Feng.success("删除成功!");
            Bill.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("billId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询账单管理列表
 */
Bill.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    queryData['type'] = $("#type").val();
    Bill.table.refresh({query: queryData});
};

Bill.resetSearch = function () {
    $("#phone").val("");
    $("#type").val("");

    Bill.search();
};
$(function () {
    var defaultColunms = Bill.initColumn();
    var table = new BSTable(Bill.id, "/bill/list", defaultColunms);
    table.setPaginationType("server");
    Bill.table = table.init();
});
