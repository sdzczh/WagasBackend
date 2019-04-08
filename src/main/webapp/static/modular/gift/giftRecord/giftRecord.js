/**
 * 礼物赠送记录管理初始化
 */
var GiftRecord = {
    id: "GiftRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
GiftRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '礼物名称', field: 'gift_name', visible: true, align: 'center', valign: 'middle'},
            {title: '用户手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '接受方手机号', field: 'acceptPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'State', visible: true, align: 'center', valign: 'middle'},
            {title: '留言', field: 'message', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
GiftRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        GiftRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加礼物赠送记录
 */
GiftRecord.openAddGiftRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加礼物赠送记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/giftRecord/giftRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看礼物赠送记录详情
 */
GiftRecord.openGiftRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '礼物赠送记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/giftRecord/giftRecord_update/' + GiftRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除礼物赠送记录
 */
GiftRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/giftRecord/delete", function (data) {
            Feng.success("删除成功!");
            GiftRecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("giftRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询礼物赠送记录列表
 */
GiftRecord.search = function () {
    var queryData = {};
    queryData['pushPhone'] = $("#pushPhone").val();
    queryData['recivePhone'] = $("#recivePhone").val();
    queryData['giftName'] = $("#giftName").val();
    queryData['state'] = $("#state").val();
    GiftRecord.table.refresh({query: queryData});
};
GiftRecord.resetSearch = function () {
    $("#pushPhone").val("");
    $("#recivePhone").val("");
    $("#giftName").val("");
    $("#state").val("");

    GiftRecord.search();
};
$(function () {
    var defaultColunms = GiftRecord.initColumn();
    var table = new BSTable(GiftRecord.id, "/giftRecord/list", defaultColunms);
    table.setPaginationType("client");
    GiftRecord.table = table.init();
});
