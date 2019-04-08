/**
 * 初始化优惠券信息详情对话框
 */
var CouponInfoDlg = {
    couponInfoData : {}
};

/**
 * 清除数据
 */
CouponInfoDlg.clearData = function() {
    this.couponInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponInfoDlg.set = function(key, val) {
    this.couponInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CouponInfoDlg.close = function() {
    parent.layer.close(window.parent.Coupon.layerIndex);
}

/**
 * 收集数据
 */
CouponInfoDlg.collectData = function() {
    this
    .set('id')
    .set('score')
    .set('level')
    .set('amount')
    .set('remain')
    .set('price')
    .set('effectiveTime')
    .set('title')
    .set('desc')
    .set('rules')
    .set('showImgUrl')
    .set('infoImgUrl');
};

/**
 * 提交添加
 */
CouponInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/coupon/add", function(data){
        Feng.success("添加成功!");
        window.parent.Coupon.table.refresh();
        CouponInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.couponInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CouponInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/coupon/update", function(data){
        Feng.success("修改成功!");
        window.parent.Coupon.table.refresh();
        CouponInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.couponInfoData);
    ajax.start();
};

$(function() {

});
