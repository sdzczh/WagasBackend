/**
 * 初始化优惠券持有管理详情对话框
 */
var CouponUserInfoDlg = {
    couponUserInfoData : {}
};

/**
 * 清除数据
 */
CouponUserInfoDlg.clearData = function() {
    this.couponUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponUserInfoDlg.set = function(key, val) {
    this.couponUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CouponUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CouponUserInfoDlg.close = function() {
    parent.layer.close(window.parent.CouponUser.layerIndex);
}

/**
 * 收集数据
 */
CouponUserInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('couponId')
    .set('startTime')
    .set('endTime')
    .set('state')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
CouponUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/couponUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.CouponUser.table.refresh();
        CouponUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.couponUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CouponUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/couponUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.CouponUser.table.refresh();
        CouponUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.couponUserInfoData);
    ajax.start();
}

$(function() {

});
