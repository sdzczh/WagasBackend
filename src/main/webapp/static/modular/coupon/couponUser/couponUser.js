/**
 * 优惠券持有管理管理初始化
 */
var CouponUser = {
    id: "CouponUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CouponUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '优惠券ID', field: 'coupon_id', visible: true, align: 'center', valign: 'middle'},
            {title: '优惠券名称', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'start_time', visible: true, align: 'center', valign: 'middle'},
            {title: '失效时间', field: 'end_time', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'State', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CouponUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CouponUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加优惠券持有管理
 */
CouponUser.openAddCouponUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加优惠券持有管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/couponUser/couponUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看优惠券持有管理详情
 */
CouponUser.openCouponUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '优惠券持有管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/couponUser/couponUser_update/' + CouponUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除优惠券持有管理
 */
CouponUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/couponUser/delete", function (data) {
            Feng.success("删除成功!");
            CouponUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("couponUserId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询优惠券持有管理列表
 */
CouponUser.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    queryData['title'] = $("#title").val();
    CouponUser.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CouponUser.initColumn();
    var table = new BSTable(CouponUser.id, "/couponUser/list", defaultColunms);
    table.setPaginationType("client");
    CouponUser.table = table.init();
});
