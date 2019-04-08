/**
 * 初始化礼物管理详情对话框
 */
var GiftInfoDlg = {
    giftInfoData : {}
};

/**
 * 清除数据
 */
GiftInfoDlg.clearData = function() {
    this.giftInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GiftInfoDlg.set = function(key, val) {
    this.giftInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GiftInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GiftInfoDlg.close = function() {
    parent.layer.close(window.parent.Gift.layerIndex);
}

/**
 * 收集数据
 */
GiftInfoDlg.collectData = function() {
    this
    .set('id')
    .set('giftName')
    .set('imgUrl')
    .set('type')
    .set('price')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
GiftInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/gift/add", function(data){
        Feng.success("添加成功!");
        window.parent.Gift.table.refresh();
        GiftInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.giftInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
GiftInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/gift/update", function(data){
        Feng.success("修改成功!");
        window.parent.Gift.table.refresh();
        GiftInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.giftInfoData);
    ajax.start();
}

$(function() {

});
