/**
 * 礼物管理管理初始化
 */
var Gift = {
    id: "GiftTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Gift.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '礼物名称', field: 'giftName', visible: true, align: 'center', valign: 'middle'},
            {title: '图片url', field: 'imgUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'gift_type', visible: true, align: 'center', valign: 'middle'},
            {title: '价格', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Gift.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Gift.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加礼物管理
 */
Gift.openAddGift = function () {
    var index = layer.open({
        type: 2,
        title: '添加礼物管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/gift/gift_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看礼物管理详情
 */
Gift.openGiftDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '礼物管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/gift/gift_update/' + Gift.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除礼物管理
 */
Gift.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/gift/delete", function (data) {
            Feng.success("删除成功!");
            Gift.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("giftId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询礼物管理列表
 */
Gift.search = function () {
    var queryData = {};
    queryData['gift_name'] = $("#gift_name").val();
    Gift.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Gift.initColumn();
    var table = new BSTable(Gift.id, "/gift/list", defaultColunms);
    table.setPaginationType("client");
    Gift.table = table.init();
});
