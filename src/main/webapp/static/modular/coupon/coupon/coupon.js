/**
 * 优惠券信息管理初始化
 */
var Coupon = {
    id: "CouponTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Coupon.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '优惠券名称', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'desc', visible: true, align: 'center', valign: 'middle'},
            {title: '用法规则', field: 'rules', visible: true, align: 'center', valign: 'middle'},
            {title: '所需积分', field: 'score', visible: true, align: 'center', valign: 'middle'},
            {title: '所需等级', field: 'Level', visible: true, align: 'center', valign: 'middle'},
            {title: '总量', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '剩余数量', field: 'remain', visible: true, align: 'center', valign: 'middle'},
            {title: '单价', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '展示图片', field: 'show_img_url', visible: true, align: 'center', valign: 'middle'},
            {title: '介绍图片', field: 'info_img_url', visible: true, align: 'center', valign: 'middle'},
            {title: '有效期至', field: 'effective_time', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Coupon.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Coupon.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加优惠券信息
 */
Coupon.openAddCoupon = function () {
    var index = layer.open({
        type: 2,
        title: '添加优惠券信息',
        area: ['800px', '620px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/coupon/coupon_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看优惠券信息详情
 */
Coupon.openCouponDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '优惠券信息详情',
            area: ['800px', '680px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/coupon/coupon_update/' + Coupon.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除优惠券信息
 */
Coupon.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/coupon/delete", function (data) {
            Feng.success("删除成功!");
            Coupon.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("couponId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询优惠券信息列表
 */
Coupon.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    Coupon.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Coupon.initColumn();
    var table = new BSTable(Coupon.id, "/coupon/list", defaultColunms);
    table.setPaginationType("client");
    Coupon.table = table.init();
});
