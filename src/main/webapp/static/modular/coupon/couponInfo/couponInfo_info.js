/**
 * 初始化优惠券详情对话框
 */
var CouponInfoInfoDlg = {
    couponInfoInfoData : {}
};

/**
 * 清除数据
 */
CouponInfoInfoDlg.clearData = function() {
    this.couponInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponInfoInfoDlg.set = function(key, val) {
    this.couponInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CouponInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.CouponInfo.layerIndex);
}

/**
 * 收集数据
 */
CouponInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('couponId')
    .set('title')
    .set('desc')
    .set('rules')
    .set('showImgUrl')
    .set('infoImgUrl')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
CouponInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/couponInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.CouponInfo.table.refresh();
        CouponInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.couponInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CouponInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/couponInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.CouponInfo.table.refresh();
        CouponInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.couponInfoInfoData);
    ajax.start();
}

$(function() {

});
