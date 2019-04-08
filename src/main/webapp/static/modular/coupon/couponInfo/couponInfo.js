/**
 * 优惠券管理初始化
 */
var CouponInfo = {
    id: "CouponInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CouponInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '优惠券ID', field: 'couponId', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'desc', visible: true, align: 'center', valign: 'middle'},
            {title: '用法规则', field: 'rules', visible: true, align: 'center', valign: 'middle'},
            {title: '展示图片', field: 'showImgUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '介绍图片', field: 'infoImgUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CouponInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CouponInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加优惠券
 */
CouponInfo.openAddCouponInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加优惠券',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/couponInfo/couponInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看优惠券详情
 */
CouponInfo.openCouponInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '优惠券详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/couponInfo/couponInfo_update/' + CouponInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除优惠券
 */
CouponInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/couponInfo/delete", function (data) {
            Feng.success("删除成功!");
            CouponInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("couponInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询优惠券列表
 */
CouponInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CouponInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CouponInfo.initColumn();
    var table = new BSTable(CouponInfo.id, "/couponInfo/list", defaultColunms);
    table.setPaginationType("client");
    CouponInfo.table = table.init();
});
