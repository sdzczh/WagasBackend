/**
 * 初始化礼物赠送记录详情对话框
 */
var GiftRecordInfoDlg = {
    giftRecordInfoData : {}
};

/**
 * 清除数据
 */
GiftRecordInfoDlg.clearData = function() {
    this.giftRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GiftRecordInfoDlg.set = function(key, val) {
    this.giftRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GiftRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GiftRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.GiftRecord.layerIndex);
}

/**
 * 收集数据
 */
GiftRecordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('acceptId')
    .set('gId')
    .set('state')
    .set('message')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
GiftRecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/giftRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.GiftRecord.table.refresh();
        GiftRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.giftRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
GiftRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/giftRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.GiftRecord.table.refresh();
        GiftRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.giftRecordInfoData);
    ajax.start();
}

$(function() {

});
