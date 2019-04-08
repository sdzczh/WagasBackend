/**
 * 初始化账单管理详情对话框
 */
var BillInfoDlg = {
    billInfoData : {}
};

/**
 * 清除数据
 */
BillInfoDlg.clearData = function() {
    this.billInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BillInfoDlg.set = function(key, val) {
    this.billInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BillInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BillInfoDlg.close = function() {
    parent.layer.close(window.parent.Bill.layerIndex);
}

/**
 * 收集数据
 */
BillInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('type')
    .set('amount')
    .set('accAmount')
    .set('remark')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
BillInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bill/add", function(data){
        Feng.success("添加成功!");
        window.parent.Bill.table.refresh();
        BillInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.billInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BillInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bill/update", function(data){
        Feng.success("修改成功!");
        window.parent.Bill.table.refresh();
        BillInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.billInfoData);
    ajax.start();
}

$(function() {

});
